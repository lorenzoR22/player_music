package com.example.music_player.Services.Impl;

import com.example.music_player.Config.JwtUtils;
import com.example.music_player.Dtos.LoginDTO;
import com.example.music_player.Mappers.UsuarioMapper;
import com.example.music_player.Exceptions.NotFoundException;
import com.example.music_player.Exceptions.AlreadyExistsException;
import com.example.music_player.Models.*;
import com.example.music_player.Dtos.UsuarioDTO;
import com.example.music_player.Repositories.FacturaRepository;
import com.example.music_player.Repositories.RoleRepository;
import com.example.music_player.Repositories.UsuarioRepository;
import com.example.music_player.Services.UsuarioService;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.preference.Preference;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final RoleRepository roleRepository;
    private final FacturaRepository facturaRepository;

    @Value("${mercadopago.accessToken}")
    private String mercadoPagoAccessToken;

    public UsuarioDTO getUsuario(Long id){
        Usuario usuario=usuarioRepository.findById(id)
                .orElseThrow(()->new NotFoundException("No se encontro el usuario"));
        return usuarioMapper.usuarioToDTO(usuario);
    }

    public Boolean saveUser(UsuarioDTO usuario) throws AlreadyExistsException {
        if(!usuarioRepository.existsByUsername(usuario.getUsername())){
            if (!usuarioRepository.existsByEmail(usuario.getEmail())) {
                usuarioRepository.save(usuarioMapper.DTOtoUsuario(usuario));
                return true;
            }
            throw new AlreadyExistsException("Email ya en uso");
        }
        throw new AlreadyExistsException("Username ya en uso");
    }

    public String loginUsuario(LoginDTO loginRequest){
        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtUtils.generateToken(loginRequest.getUsername());
    }
    public void editarUsuario(String username,UsuarioDTO usuarioDTO){
        Usuario usuario=usuarioRepository.findByUsername(username)
                .orElseThrow(()->new NotFoundException("No se encontro el usuario"));
        if(usuarioDTO.getEmail()!=null){
            usuario.setEmail(usuarioDTO.getEmail());
        }
    }
    public String comprarPremium(String username){
        Usuario usuario=usuarioRepository.findByUsername(username)
                .orElseThrow(()->new NotFoundException("No encontro el usuario "));
        if(usuario.getRoles().stream()
                .anyMatch(role->role.getRole()==Erole.FREE)){
           return getLinkCompra(username);
        }else{
            throw new AlreadyExistsException("Ya tienes la suscripcion premium");
        }
    }

    private String getLinkCompra(String username){
        try {
            MercadoPagoConfig.setAccessToken(mercadoPagoAccessToken);

                PreferenceItemRequest itemRequest =
                        PreferenceItemRequest.builder()
                                .title(Erole.PREMIUM.toString())
                                .quantity(1)
                                .currencyId("ARS")
                                .unitPrice(BigDecimal.valueOf(2499.99))
                                .build();

            PreferenceBackUrlsRequest backUrls =  PreferenceBackUrlsRequest
                    .builder().success("https://www.youtube.com/success")
                    .failure("https://www.youtube.com/failure")
                    .pending("https://www.youtube.com/pending")
                    .build();

            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .items(List.of(itemRequest))
                    .backUrls(backUrls)
                    .notificationUrl("https://7a42-201-179-207-154.ngrok-free.app/usuarios/checkPago/"+username)
                    .build();

            PreferenceClient client = new PreferenceClient();
            Preference preference = client.create(preferenceRequest);

            return preference.getInitPoint();

        }catch (MPException | MPApiException e){
            return e.toString();
        }
    }
    @Transactional
    public ResponseEntity<String> handleNotification(Map<String, Object> payload, String username) {
        if (!"payment".equals(payload.get("type"))) {
            return ResponseEntity.badRequest().body("Tipo de notificación no soportado.");
        }

        Map<String, Object> data = (Map<String, Object>) payload.get("data");
        if (data == null || !data.containsKey("id")) {
            return ResponseEntity.badRequest().body("Notificación inválida: falta el ID del pago.");
        }

        String paymentId = (String) data.get("id");

        try {
            MercadoPagoConfig.setAccessToken(mercadoPagoAccessToken);

            PaymentClient paymentClient = new PaymentClient();
            Payment payment = paymentClient.get(Long.valueOf(paymentId));

            if ("approved".equals(payment.getStatus())) {
                Usuario usuario = usuarioRepository.findByUsername(username)
                        .orElseThrow(() -> new NotFoundException("No se encontró el usuario"));

                usuario.getRoles().removeIf(role -> role.getRole() == Erole.FREE);

                Role premium=roleRepository.findByRole(Erole.PREMIUM)
                        .orElseThrow(() -> new NotFoundException("No se encontró el rol PREMIUM"));
                usuario.getRoles().add(premium);
                usuarioRepository.save(usuario);

                Factura factura=new Factura(usuario,premium,payment.getNetAmount().doubleValue());
                facturaRepository.save(factura);

                return ResponseEntity.ok("Notificación procesada con éxito.");
            } else {
                return ResponseEntity.badRequest().body("Pago no aprobado.");
            }
        } catch (MPException | MPApiException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al verificar el pago.");
        }
    }

}
