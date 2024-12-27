package com.example.music_player.Repositories;

import com.example.music_player.Models.Artista;
import com.example.music_player.Models.ListaReproduccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListaReproduccionRepository extends JpaRepository<ListaReproduccion,Long>{
}
