package com.example.music_player.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListaReproduccionDTO {
    @NotBlank
    private String nombre;

    @NotNull
    private Long usuario_id;

    private Boolean esPrivada=false;

    private Set<Long> canciones_id=new HashSet<>();
}
