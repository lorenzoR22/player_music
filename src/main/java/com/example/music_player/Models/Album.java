package com.example.music_player.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "album")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private Integer anio;

    @ManyToOne
    @JoinColumn(name = "artista_id")
    private Artista artista;

    @OneToMany(mappedBy = "album",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cancion> canciones=new ArrayList<>();

    public Album(String titulo, Integer anio, Artista artista) {
        this.titulo = titulo;
        this.anio = anio;
        this.artista = artista;
    }

    public Album(String titulo, Integer anio, Artista artista, List<Cancion> canciones) {
        this.titulo = titulo;
        this.anio = anio;
        this.artista = artista;
        this.canciones = canciones;
    }
}
