package com.example.music_player.Services;

import com.example.music_player.Dtos.LoginDTO;
import com.example.music_player.Dtos.UsuarioDTO;
import com.example.music_player.Exceptions.AlreadyExistsException;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UsuarioService {
    UsuarioDTO getUsuario(Long id);
    Boolean saveUser(UsuarioDTO usuario) throws AlreadyExistsException;
    String loginUsuario(LoginDTO loginRequest);
    void editarUsuario(String username,UsuarioDTO usuarioDTO);
    String comprarPremium(String username);
    ResponseEntity<String> handleNotification(Map<String, Object> payload, String username);
}
