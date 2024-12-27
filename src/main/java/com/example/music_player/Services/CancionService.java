package com.example.music_player.Services;

import com.example.music_player.Dtos.CancionDTO;
import org.springframework.core.io.Resource;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.List;

public interface CancionService {
    CancionDTO getCancion(Long id);
    Resource reproducirCancion(Long id)throws FileNotFoundException, MalformedURLException;
    List<CancionDTO> getAll();
}
