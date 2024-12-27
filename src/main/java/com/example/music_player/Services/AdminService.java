package com.example.music_player.Services;

import com.example.music_player.Dtos.AlbumDTO;
import com.example.music_player.Dtos.ArtistaDTO;
import com.example.music_player.Dtos.CancionDTO;

import java.util.List;

public interface AdminService {
    void agregarArtista(ArtistaDTO artistaDTO);
    void agregarAlbum(AlbumDTO albumDTO);
    void agregarCancion(CancionDTO cancionDTO);
    void borrarCancion(Long id);
    void editarCancion(Long cancion_id,CancionDTO cancionDTO);
    void borrarAlbum(Long id);
    void editarAlbum(Long id,AlbumDTO albumDTO);
    void agregarCancionesToAlbum(Long album_id, List<Long> cancionesDTO);
    void borrarArtista(Long id);
    void editarArtista(Long id,ArtistaDTO artistaDTO);
    void agregarAlbumsToArtista(Long artista_id,List<Long>albums_id);
    void borrarUsuario(Long id);
}
