package com.example.music_player.Mappers;

import com.example.music_player.Dtos.CancionDTO;
import com.example.music_player.Models.Cancion;

public class CancionMapper {
    public static CancionDTO cancionToDTO(Cancion cancion){
        CancionDTO cancionDTO= new CancionDTO(cancion.getNombre(),
                cancion.getArtista().getId(),
                cancion.getRutaCancion(),
                cancion.getDuracion(),
                cancion.getReproducciones());
        if(cancion.getAlbum()!=null){
            cancionDTO.setAlbum_id(cancion.getAlbum().getId());
        }
        return cancionDTO;
    }
    public static CancionDTO cancionToDTO2(Cancion cancion){
        CancionDTO cancionDTO= new CancionDTO(
                cancion.getId(),
                cancion.getArtista().getNombre(),
                cancion.getNombre(),
                cancion.getArtista().getId(),
                cancion.getRutaCancion(),
                cancion.getDuracion(),
                cancion.getReproducciones());
        if(cancion.getAlbum()!=null){
            cancionDTO.setAlbum_id(cancion.getAlbum().getId());
        }
        return cancionDTO;
    }
}
