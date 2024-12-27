package com.example.music_player.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "artista")
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private Long oyentes;

    @OneToMany(mappedBy = "artista",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Album> albums=new ArrayList<>();

    public Artista(String nombre, Long oyentes) {
        this.nombre = nombre;
        this.oyentes = oyentes;
    }

    public Artista(String nombre, Long oyentes, List<Album> albums) {
        this.nombre = nombre;
        this.oyentes = oyentes;
        this.albums = albums;
    }
}
