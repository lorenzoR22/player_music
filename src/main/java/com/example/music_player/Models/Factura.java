package com.example.music_player.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "factura")
public class Factura{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "suscripcion_id")
    private Role suscripcion;

    private Double precio;

    private Double total=0.0;

    public Factura(Usuario usuario, Role suscripcion, Double precio) {
        this.usuario = usuario;
        this.suscripcion = suscripcion;
        this.precio = precio;
        this.total=precio;
    }
}
