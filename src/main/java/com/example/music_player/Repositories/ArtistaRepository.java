package com.example.music_player.Repositories;

import com.example.music_player.Models.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista,Long> {
}
