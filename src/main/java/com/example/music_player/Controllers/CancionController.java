package com.example.music_player.Controllers;

import com.example.music_player.Dtos.CancionDTO;
import com.example.music_player.Services.CancionService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

@RestController
@RequestMapping("/canciones")
@RequiredArgsConstructor
public class CancionController {

    private final CancionService cancionService;

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CancionDTO getCancion(@PathVariable Long id){
        return cancionService.getCancion(id);
    }

    @GetMapping(value = "/reproducir/{id}", produces = "audio/mpeg")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Resource> reproducirCancion(@PathVariable Long id) throws FileNotFoundException, MalformedURLException {
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("audio/mpeg"))
                .body(cancionService.reproducirCancion(id));
    }

}
