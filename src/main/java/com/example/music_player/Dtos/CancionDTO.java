package com.example.music_player.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CancionDTO {

    @NotNull
    private Long id;

    @NotBlank
    private String nombre_artista;

    @NotBlank
    private String nombre;

    @NotNull
    private Long artista_id;

    private Long album_id;

    @NotBlank
    private String rutaCancion;

    @NotBlank
    private String duracion;

    @NotNull
    @Positive
    private Long reproducciones;

    public CancionDTO(String nombre, Long artista_id, Long album_id, String rutaCancion, String duracion, Long reproducciones) {
        this.nombre = nombre;
        this.artista_id = artista_id;
        this.album_id = album_id;
        this.rutaCancion = rutaCancion;
        this.duracion = duracion;
        this.reproducciones = reproducciones;
    }

    public CancionDTO(String nombre, Long artista_id, String rutaCancion, String duracion, Long reproducciones) {
        this.nombre = nombre;
        this.artista_id = artista_id;
        this.album_id = null;
        this.rutaCancion = rutaCancion;
        this.duracion = duracion;
        this.reproducciones = reproducciones;
    }

    public CancionDTO(Long id, String nombre_artista, String nombre, Long artista_id, String rutaCancion, String duracion, Long reproducciones) {
        this.id = id;
        this.nombre_artista = nombre_artista;
        this.nombre = nombre;
        this.artista_id = artista_id;
        this.album_id = null;
        this.rutaCancion = rutaCancion;
        this.duracion = duracion;
        this.reproducciones = reproducciones;
    }
}
