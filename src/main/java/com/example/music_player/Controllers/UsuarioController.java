package com.example.music_player.Controllers;

import com.example.music_player.Exceptions.AlreadyExistsException;
import com.example.music_player.Dtos.LoginDTO;
import com.example.music_player.Dtos.UsuarioDTO;
import com.example.music_player.Services.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Boolean registerUser(@RequestBody @Valid UsuarioDTO usuario) throws AlreadyExistsException {
        return usuarioService.saveUser(usuario);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public String AuthenticateUser(@RequestBody @Valid LoginDTO loginRequest){
        return usuarioService.loginUsuario(loginRequest);
    }

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioDTO getUsuario(@PathVariable Long id){
        return usuarioService.getUsuario(id);
    }

    @PatchMapping("/editarUsuario")
    @ResponseStatus(HttpStatus.OK)
    public void editarUsuario(@AuthenticationPrincipal UserDetails userDetails,
                              @RequestBody UsuarioDTO usuarioDTO){
        usuarioService.editarUsuario(userDetails.getUsername(),usuarioDTO);
    }

    @GetMapping("/comprarPremium")
    @ResponseStatus(HttpStatus.OK)
    public String comprarPremium(@AuthenticationPrincipal UserDetails userDetails){
        return usuarioService.comprarPremium(userDetails.getUsername());
    }

    @PostMapping("/checkPago/{username}")
    public void checkPago(@PathVariable String username,@RequestBody Map<String, Object> payload){
        usuarioService.handleNotification(payload,username);
    }
}
