package com.example.music_player.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "cancion")
public class Cancion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "artista_id")
    private Artista artista;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;

    private String rutaCancion;

    private String duracion;

    private Long reproducciones;

    public Cancion(String nombre, Artista artista, Album album, String rutaCancion, String duracion, Long reproducciones) {
        this.nombre = nombre;
        this.artista = artista;
        this.album = album;
        this.rutaCancion = rutaCancion;
        this.duracion = duracion;
        this.reproducciones = reproducciones;
    }
    public Cancion(String nombre, Artista artista, String rutaCancion, String duracion, Long reproducciones) {
        this.nombre = nombre;
        this.artista = artista;
        this.album = new Album();
        this.rutaCancion = rutaCancion;
        this.duracion = duracion;
        this.reproducciones = reproducciones;
    }
}
