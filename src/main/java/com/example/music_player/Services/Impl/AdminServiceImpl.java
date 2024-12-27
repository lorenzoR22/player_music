package com.example.music_player.Services.Impl;

import com.example.music_player.Exceptions.NotFoundException;
import com.example.music_player.Models.Album;
import com.example.music_player.Models.Artista;
import com.example.music_player.Models.Cancion;
import com.example.music_player.Dtos.AlbumDTO;
import com.example.music_player.Dtos.ArtistaDTO;
import com.example.music_player.Dtos.CancionDTO;
import com.example.music_player.Repositories.AlbumRepository;
import com.example.music_player.Repositories.ArtistaRepository;
import com.example.music_player.Repositories.CancionRepository;
import com.example.music_player.Repositories.UsuarioRepository;
import com.example.music_player.Services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final CancionRepository cancionRepository;

    private final AlbumRepository albumRepository;

    private final ArtistaRepository artistaRepository;

    private final UsuarioRepository usuarioRepository;

    public void agregarArtista(ArtistaDTO artistaDTO){
        Artista newartista=new Artista(artistaDTO.getNombre(),
                artistaDTO.getOyentes());
        artistaRepository.save(newartista);
    }

    public void agregarAlbum(AlbumDTO albumDTO){
        Artista artista=artistaRepository.findById(albumDTO.getArtista_id())
                .orElseThrow(()->new NotFoundException("No se encontro al artista."));
        Album newalbum=new Album(albumDTO.getTitulo(),
                albumDTO.getAnio(),
                artista);
        albumRepository.save(newalbum);
    }

    public void agregarCancion(CancionDTO cancionDTO){
        Cancion cancion=new Cancion(cancionDTO.getNombre(),
                artistaRepository.findById(cancionDTO.getArtista_id())
                        .orElseThrow(()->new NotFoundException("No se encontro el artista")),
                cancionDTO.getRutaCancion(),
                cancionDTO.getDuracion(),
                cancionDTO.getReproducciones());

        if(cancionDTO.getAlbum_id()!=null){
            cancion.setAlbum(albumRepository.findById(cancionDTO.getAlbum_id())
                    .orElseThrow(()->new NotFoundException("No se encontro el album")));
        }
        cancionRepository.save(cancion);
    }

    public void borrarCancion(Long id){
        if(cancionRepository.existsById(id)){
            cancionRepository.deleteById(id);
        }else{
            throw new NotFoundException("No se encontro la cancion");
        }
    }

    public void editarCancion(Long cancion_id,CancionDTO cancionDTO){
        Cancion cancion=cancionRepository.findById(cancion_id)
                .orElseThrow(()->new NotFoundException(("No se encontro la cancion")));

        if(cancionDTO.getNombre()!=null){
            cancion.setNombre(cancionDTO.getNombre());
        }
        if(cancionDTO.getDuracion()!=null){
            cancion.setDuracion(cancionDTO.getDuracion());
        }
        if(cancionDTO.getReproducciones()!=null){
            cancion.setReproducciones(cancionDTO.getReproducciones());
        }
        if(cancionDTO.getRutaCancion()!=null){
            cancion.setRutaCancion(cancionDTO.getRutaCancion());
        }

        if(cancionDTO.getAlbum_id()!=null) {
            Album album=albumRepository.findById(cancionDTO.getAlbum_id())
                    .orElseThrow(()->new NotFoundException("No se encontro el album"));
            cancion.setAlbum(album);
        }
        if(cancionDTO.getArtista_id()!=null){
            Artista artista=artistaRepository.findById(cancionDTO.getArtista_id())
                    .orElseThrow(()->new NotFoundException("No se encontro el artista"));
            cancion.setArtista(artista);
        }

        cancionRepository.save(cancion);
    }

    public void borrarAlbum(Long id){
        if(albumRepository.existsById(id)){
            albumRepository.deleteById(id);
        }else{
            throw new NotFoundException("No se encontro el album");
        }
    }

    public void editarAlbum(Long id,AlbumDTO albumDTO){
        Album album=albumRepository.findById(id)
                .orElseThrow(()->new NotFoundException("No se encontro el album"));

        if(albumDTO.getTitulo()!=null){
            album.setTitulo(albumDTO.getTitulo());
        }
        if(albumDTO.getAnio()!=null){
            album.setAnio(albumDTO.getAnio());
        }

        if(albumDTO.getArtista_id()!=null && !albumDTO.getArtista_id().equals(album.getArtista().getId())){
            Artista artista=artistaRepository.findById(albumDTO.getArtista_id())
                    .orElseThrow(()->new NotFoundException("No se encontro el artista"));
            album.getArtista().getAlbums().remove(album);
            album.setArtista(artista);
            artista.getAlbums().add(album);
        }
        albumRepository.save(album);
    }

    public void agregarCancionesToAlbum(Long album_id,List<Long>cancionesDTO){
        Album album=albumRepository.findById(album_id)
                .orElseThrow(()->new NotFoundException("No se encontro el album"));
        if(cancionesDTO!=null){
            List<Cancion> canciones=new ArrayList<>();

            for(Long i:cancionesDTO){
                cancionRepository.findById(i).ifPresent(canciones::add);
            }
            album.setCanciones(canciones);
        }
        albumRepository.save(album);
    }

    public void borrarArtista(Long id){
        if(artistaRepository.existsById(id)){
            artistaRepository.deleteById(id);
        }else{
            throw new NotFoundException("No se encontro el artista");
        }
    }

    public void editarArtista(Long id,ArtistaDTO artistaDTO){
        Artista artista=artistaRepository.findById(id)
                .orElseThrow(()->new NotFoundException("No se encontro el artista"));

        if(artistaDTO.getNombre()!=null){
            artista.setNombre(artistaDTO.getNombre());
        }
        if(artistaDTO.getOyentes()!=null){
            artista.setOyentes(artista.getOyentes());
        }
        artistaRepository.save(artista);
    }

    public void agregarAlbumsToArtista(Long artista_id,List<Long>albums_id){
        Artista artista=artistaRepository.findById(artista_id)
                .orElseThrow(()->new NotFoundException("No se encontro el artista"));

        if(albums_id!=null){
            List<Album> albums=new ArrayList<>();

            for(Long i:albums_id){
                albumRepository.findById(i).ifPresent(albums::add);
            }
            artista.setAlbums(albums);
        }
        artistaRepository.save(artista);
    }

    public void borrarUsuario(Long id){
        if(usuarioRepository.existsById(id)){
            usuarioRepository.deleteById(id);
        }else{
            throw new NotFoundException("No se encontro el usuario");
        }
    }
}
