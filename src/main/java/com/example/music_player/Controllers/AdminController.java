package com.example.music_player.Controllers;

import com.example.music_player.Dtos.AlbumDTO;
import com.example.music_player.Dtos.ArtistaDTO;
import com.example.music_player.Dtos.CancionDTO;
import com.example.music_player.Dtos.FacturaDTO;
import com.example.music_player.Services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/agregarArtista")
    @ResponseStatus(HttpStatus.CREATED)
    public void agregarArtista(@RequestBody ArtistaDTO artistaDTO){
        adminService.agregarArtista(artistaDTO);
    }

    @PostMapping("/agregarCancion")
    @ResponseStatus(HttpStatus.CREATED)
    public void agregarCancion(@RequestBody CancionDTO cancionDTO){
        adminService.agregarCancion(cancionDTO);
    }

    @PostMapping("/agregarAlbum")
    @ResponseStatus(HttpStatus.CREATED)
    public void agregarAlbum(@RequestBody AlbumDTO albumDTO){
        adminService.agregarAlbum(albumDTO);
    }

    @PatchMapping("/editarCancion/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void editarCancion(@PathVariable Long id,@RequestBody CancionDTO cancionDTO){
        adminService.editarCancion(id,cancionDTO);
    }

    @PatchMapping("/editarAlbum/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void editarAlbum(@PathVariable Long id,@RequestBody AlbumDTO albumDTO){
        adminService.editarAlbum(id,albumDTO);
    }

    @PostMapping("/agregarCancionesToAlbum/{album_id}")
    @ResponseStatus(HttpStatus.OK)
    public void agregarCancionToAlbum(@PathVariable Long album_id,@RequestBody List<Long>canciones_id){
        adminService.agregarCancionesToAlbum(album_id,canciones_id);
    }

    @PatchMapping("/editarArtista/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void editarArtista(@PathVariable Long id,@RequestBody ArtistaDTO artistaDTO){
        adminService.editarArtista(id,artistaDTO);
    }

    @PostMapping("/agregarAlbumsToArtista/{artista_id}")
    @ResponseStatus(HttpStatus.OK)
    public void agregarAlbumsToArtista(@PathVariable Long artista_id,@RequestBody List<Long>albums_id){
        adminService.agregarAlbumsToArtista(artista_id,albums_id);
    }

    @DeleteMapping("/borrarCancion/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void borrarCancion(@PathVariable Long id){
        adminService.borrarCancion(id);
    }

    @DeleteMapping("/borrarAlbum/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void borrarAlbum(@PathVariable Long id){
        adminService.borrarAlbum(id);
    }

    @DeleteMapping("/borrarArtista/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void borrarArtista(@PathVariable Long id){
        adminService.borrarArtista(id);
    }

    @DeleteMapping("/borrarUsuario/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void borrarUsuario(@PathVariable Long id){
        adminService.borrarUsuario(id);
    }
}
