package com.example.music_player.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArtistaDTO {
    @NotBlank
    private String nombre;

    @Positive
    @NotNull
    private Long oyentes;

    private List<Long> albums_id=new ArrayList<>();

}
