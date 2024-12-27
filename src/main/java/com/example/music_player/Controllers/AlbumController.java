package com.example.music_player.Controllers;

import com.example.music_player.Dtos.AlbumDTO;
import com.example.music_player.Services.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/albums")
@RequiredArgsConstructor
public class AlbumController {
    private final AlbumService albumService;

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AlbumDTO getAlbum(@PathVariable Long id){
        return albumService.getAlbum(id);
    }
}
