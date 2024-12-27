package com.example.music_player.Services;

import com.example.music_player.Dtos.FacturaDTO;

import java.nio.file.AccessDeniedException;

public interface FacturaService {
    FacturaDTO getFactura(Long id,String username) throws AccessDeniedException;
}
