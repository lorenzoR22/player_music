package com.example.music_player.Services.Impl;

import com.example.music_player.Dtos.AlbumDTO;
import com.example.music_player.Mappers.AlbumMapper;
import com.example.music_player.Exceptions.NotFoundException;
import com.example.music_player.Models.Album;
import com.example.music_player.Repositories.AlbumRepository;
import com.example.music_player.Services.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;

    public AlbumDTO getAlbum(Long album_id){
         Album album=albumRepository.findById(album_id)
                .orElseThrow(()->new NotFoundException("No se encontro el album"));
         return AlbumMapper.albumToDTO(album);
    }

}
