package com.example.music_player.Mappers;

import com.example.music_player.Dtos.FacturaDTO;
import com.example.music_player.Models.Factura;

public class FacturaMapper {
    public static FacturaDTO facturaToDTO(Factura factura){
        return new FacturaDTO(factura.getUsuario().getId(),
                factura.getSuscripcion().getId(), factura.getPrecio());
    }
}
