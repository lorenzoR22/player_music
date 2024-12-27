package com.example.music_player.Services.Impl;

import com.example.music_player.Dtos.FacturaDTO;
import com.example.music_player.Exceptions.NotFoundException;
import com.example.music_player.Mappers.FacturaMapper;
import com.example.music_player.Models.Erole;
import com.example.music_player.Models.Factura;
import com.example.music_player.Models.Usuario;
import com.example.music_player.Repositories.FacturaRepository;
import com.example.music_player.Repositories.UsuarioRepository;
import com.example.music_player.Services.FacturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;


@Service
@RequiredArgsConstructor
public class FacturaServiceImpl implements FacturaService {
    private final FacturaRepository facturaRepository;
    private UsuarioRepository usuarioRepository;

    public FacturaDTO getFactura(Long id,String username) throws AccessDeniedException {

        Usuario usuario=usuarioRepository.findByUsername(username)
                .orElseThrow(()->new NotFoundException("No se encontro el username"));

        Factura factura=facturaRepository.findById(id)
                .orElseThrow(()->new NotFoundException("No se encontro la factura"));

        if(factura.getUsuario().getUsername().equals(username)||
                usuario.getRoles().stream().anyMatch(role->role.getRole()== Erole.ADMIN)){
            return FacturaMapper.facturaToDTO(factura);
        }else{
            throw new AccessDeniedException("No puedes acceder a esta factura");
        }
    }
}
