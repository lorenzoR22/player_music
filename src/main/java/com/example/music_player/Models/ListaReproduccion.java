package com.example.music_player.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "listaReproduccion")
public class ListaReproduccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private Boolean esPrivada=false;

    @ManyToMany
    @JoinTable(name = "lista_reproduccion_cancion",
            joinColumns = @JoinColumn(name = "lista_id"),
            inverseJoinColumns = @JoinColumn(name = "cancion_id"))
    private Set<Cancion> canciones=new HashSet<>();

    public ListaReproduccion(String nombre, Usuario usuario, Boolean esPrivada, Set<Cancion> canciones) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.esPrivada = esPrivada;
        this.canciones = canciones;
    }

    public ListaReproduccion(String nombre, Usuario usuario, Boolean esPrivada) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.esPrivada = esPrivada;
    }

    public ListaReproduccion(String nombre, Usuario usuario) {
        this.nombre = nombre;
        this.usuario = usuario;
    }

    public ListaReproduccion(String nombre, Usuario usuario, Set<Cancion> canciones) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.canciones = canciones;
    }
}
