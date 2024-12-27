package com.example.music_player.Services.Impl;

import com.example.music_player.Mappers.CancionMapper;
import com.example.music_player.Exceptions.NotFoundException;
import com.example.music_player.Models.Cancion;
import com.example.music_player.Dtos.CancionDTO;
import com.example.music_player.Repositories.CancionRepository;
import com.example.music_player.Services.CancionService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CancionServiceImpl implements CancionService {

    private final CancionRepository cancionRepository;

    public CancionDTO getCancion(Long id) {
        Cancion cancion = cancionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontro la cancion"));
        return CancionMapper.cancionToDTO(cancion);
    }

    public Resource reproducirCancion(Long id) throws FileNotFoundException, MalformedURLException {
        Cancion cancion = cancionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Canción no encontrada"));

        Path path = Paths.get(cancion.getRutaCancion());

        if (!Files.exists(path)) {
            throw new FileNotFoundException("El archivo de la canción no existe en la ruta especificada.");
        }
        return new UrlResource(path.toUri());
    }

    public List<CancionDTO> getAll() {
        List<Cancion> canciones = cancionRepository.findAll();
        return canciones.stream()
                .map(CancionMapper::cancionToDTO2)
                .toList();
    }
}
