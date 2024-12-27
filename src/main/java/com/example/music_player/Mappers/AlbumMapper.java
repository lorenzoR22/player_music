package com.example.music_player.Mappers;

import com.example.music_player.Dtos.AlbumDTO;
import com.example.music_player.Models.Album;
import com.example.music_player.Models.Cancion;

public class AlbumMapper {
    public static AlbumDTO albumToDTO(Album album){
        return new AlbumDTO(album.getTitulo(),
                album.getAnio(),
                album.getArtista().getId(),
                album.getCanciones().stream()
                        .map(Cancion::getId)
                        .toList());
    }
}
