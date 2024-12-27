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

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AlbumDTO {
    @NotBlank
    private String titulo;

    @Positive
    @NotNull
    private Integer anio;

    @NotNull
    private Long artista_id;

    private List<Long> canciones_id=new ArrayList<>();

}
