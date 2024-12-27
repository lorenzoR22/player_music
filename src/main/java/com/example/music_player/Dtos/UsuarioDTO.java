package com.example.music_player.Dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UsuarioDTO {
    @NotBlank
    @Size(max = 30,message = "El username no puede tener mas de 30 caracteres.")
    private String username;

    @NotBlank
    private String password;

    @Email(message = "Email invalido.")
    private String email;

    @NotNull
    private Set<String> roles;

    private List<ListaReproduccionDTO> listas;

    public UsuarioDTO(String username, String password, String email, Set<String> roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
        this.listas = new ArrayList<>();
    }
}
