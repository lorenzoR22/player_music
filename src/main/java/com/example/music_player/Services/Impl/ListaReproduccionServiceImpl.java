package com.example.music_player.Services.Impl;

import com.example.music_player.Mappers.ListaReproduccionMapper;
import com.example.music_player.Exceptions.NotFoundException;
import com.example.music_player.Models.Cancion;
import com.example.music_player.Dtos.ListaReproduccionDTO;
import com.example.music_player.Models.ListaReproduccion;
import com.example.music_player.Repositories.CancionRepository;
import com.example.music_player.Repositories.ListaReproduccionRepository;
import com.example.music_player.Repositories.UsuarioRepository;
import com.example.music_player.Services.ListaReproduccionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;

@Service
@RequiredArgsConstructor
public class ListaReproduccionServiceImpl implements ListaReproduccionService {

    private final ListaReproduccionRepository listaReproduccionRepository;

    private final CancionRepository cancionRepository;

    private final UsuarioRepository usuarioRepository;

    public ListaReproduccionDTO getLista(Long id){
        ListaReproduccion lista=listaReproduccionRepository.findById(id)
                .orElseThrow(()->new NotFoundException("No se encontro la lista"));
        return ListaReproduccionMapper.listaReproduccionToDTO(lista);
    }

    public void crearLista(ListaReproduccionDTO listaReproduccionDTO){
        ListaReproduccion listaReproduccion=new ListaReproduccion(
                listaReproduccionDTO.getNombre(),
                usuarioRepository.findById(listaReproduccionDTO.getUsuario_id())
                                .orElseThrow(()->new NotFoundException("no se encontro el usuario")),
                listaReproduccionDTO.getEsPrivada());
        listaReproduccionRepository.save(listaReproduccion);
    }

    public void agregarCancion(Long lista_id,Long cancion_id,String username) throws AccessDeniedException {
        ListaReproduccion listaReproduccion=listaReproduccionRepository.findById(lista_id)
                .orElseThrow(()->new NotFoundException("No se encontro la lista de reproduccion"));

        if(!listaReproduccion.getUsuario().getUsername().equals(username)){
            throw new AccessDeniedException("No tienes permiso para modificar esta lista de reproduccion.");
        }

        Cancion cancion=cancionRepository.findById(cancion_id)
                .orElseThrow(()->new NotFoundException("No se encontro la cancion"));

        listaReproduccion.getCanciones().add(cancion);
        listaReproduccionRepository.save(listaReproduccion);
    }

    public void quitarCancion(Long lista_id,Long cancion_id,String username) throws AccessDeniedException {
        ListaReproduccion listaReproduccion=listaReproduccionRepository.findById(lista_id)
                .orElseThrow(()->new NotFoundException("No se encontro la lista de reproduccion"));

        if(!listaReproduccion.getUsuario().getUsername().equals(username)){
            throw new AccessDeniedException("No tienes permiso para modificar esta lista de reproduccion.");
        }
        Cancion cancion=cancionRepository.findById(cancion_id)
                .orElseThrow(()->new NotFoundException("No se encontro la cancion"));

        listaReproduccion.getCanciones().remove(cancion);

        listaReproduccionRepository.save(listaReproduccion);
    }

    public void eliminarLista(Long lista_id,String username) throws AccessDeniedException {
        ListaReproduccion listaReproduccion=listaReproduccionRepository.findById(lista_id)
                .orElseThrow(()->new NotFoundException("No se encontro la lista de reproduccion"));

        if(!listaReproduccion.getUsuario().getUsername().equals(username)){
            throw new AccessDeniedException("No tienes permiso para modificar esta lista de reproduccion.");
        }
        listaReproduccionRepository.deleteById(lista_id);
    }

}
