package com.example.music_player.Services;

import com.example.music_player.Dtos.ListaReproduccionDTO;

import java.nio.file.AccessDeniedException;

public interface ListaReproduccionService {
    ListaReproduccionDTO getLista(Long id);
    void crearLista(ListaReproduccionDTO listaReproduccionDTO);
    void agregarCancion(Long lista_id,Long cancion_id,String username) throws AccessDeniedException;
    void quitarCancion(Long lista_id,Long cancion_id,String username) throws AccessDeniedException;
    void eliminarLista(Long lista_id,String username) throws AccessDeniedException;

}
