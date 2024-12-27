package com.example.music_player.Services;

import com.example.music_player.Dtos.AlbumDTO;

public interface AlbumService {
    AlbumDTO getAlbum(Long album_id);
}
