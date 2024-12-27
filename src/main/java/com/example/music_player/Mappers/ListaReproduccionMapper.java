package com.example.music_player.Mappers;

import com.example.music_player.Dtos.ListaReproduccionDTO;
import com.example.music_player.Models.Cancion;
import com.example.music_player.Models.ListaReproduccion;

import java.util.List;
import java.util.stream.Collectors;

public class ListaReproduccionMapper {
    public static ListaReproduccionDTO listaReproduccionToDTO(ListaReproduccion lista){
        return new ListaReproduccionDTO(
                lista.getNombre(),
                lista.getUsuario().getId(),
                lista.getEsPrivada(),
                lista.getCanciones().stream()
                        .map(Cancion::getId
                        ).collect(Collectors.toSet())
        );
    }

    public static List<ListaReproduccionDTO> listasReproduccionToDTO(List<ListaReproduccion>listas){
        if(listas!=null) {
            return listas.stream()
                    .map(ListaReproduccionMapper::listaReproduccionToDTO)
                    .toList();
        }
        return null;
    }
}
