package com.example.music_player.Controllers;

import com.example.music_player.Dtos.ListaReproduccionDTO;
import com.example.music_player.Services.ListaReproduccionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/listas")
@RequiredArgsConstructor
public class ListaReproduccionController {

    private final ListaReproduccionService listaReproduccionService;

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ListaReproduccionDTO getLista(@PathVariable Long id){
        return listaReproduccionService.getLista(id);
    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public void crearLista(@RequestBody ListaReproduccionDTO listaReproduccionDTO){
        listaReproduccionService.crearLista(listaReproduccionDTO);
    }

    @PostMapping("/agregarCancion/{cancion_id}/{lista_id}")
    @ResponseStatus(HttpStatus.OK)
    public void agregarCancion(@PathVariable Long cancion_id,
                               @PathVariable Long lista_id,
                               @AuthenticationPrincipal UserDetails userDetails) throws AccessDeniedException {
        listaReproduccionService.agregarCancion(lista_id,cancion_id, userDetails.getUsername());
    }

    @PostMapping("/quitarCancion/{lista_id}/{cancion_id}")
    @ResponseStatus(HttpStatus.OK)
    public void quitarCancion(@PathVariable Long lista_id,
                              @PathVariable Long cancion_id,
                              @AuthenticationPrincipal UserDetails userDetails) throws AccessDeniedException {
        listaReproduccionService.quitarCancion(lista_id,cancion_id, userDetails.getUsername());
    }

    @DeleteMapping("/borrar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void eliminarLista(@PathVariable Long id,@AuthenticationPrincipal UserDetails userDetails) throws AccessDeniedException {
        listaReproduccionService.eliminarLista(id, userDetails.getUsername());
    }
}
