package com.example.music_player.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FacturaDTO {
    private Long usuario_id;
    private Long suscripcion_id;
    private Double precio;
}
