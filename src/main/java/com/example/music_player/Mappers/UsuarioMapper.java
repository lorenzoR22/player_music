package com.example.music_player.Mappers;

import com.example.music_player.Dtos.UsuarioDTO;
import com.example.music_player.Exceptions.NotFoundException;
import com.example.music_player.Models.Erole;
import com.example.music_player.Models.Role;
import com.example.music_player.Models.Usuario;
import com.example.music_player.Repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UsuarioMapper {

    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public Usuario DTOtoUsuario(UsuarioDTO usuarioDTO){
        return new Usuario(usuarioDTO.getUsername(),
                passwordEncoder.encode(usuarioDTO.getPassword()),
                usuarioDTO.getEmail(),
                usuarioDTO.getRoles().stream()
                        .map(this::checkRole
                        ).collect(Collectors.toSet())
        );
    }
    private Role checkRole(String role){
        return roleRepository.findByRole(Erole.valueOf(role))
                .orElseThrow(()->new NotFoundException("No se encontro el rol"));
    }
    public UsuarioDTO usuarioToDTO(Usuario usuario){
        return new UsuarioDTO(
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getEmail(),
                usuario.getRoles().stream()
                        .map(role->role.getRole().toString())
                        .collect(Collectors.toSet()),
                ListaReproduccionMapper.listasReproduccionToDTO(usuario.getListas())
        );
    }
}
