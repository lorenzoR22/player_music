package com.example.music_player.Repositories;

import com.example.music_player.Models.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CancionRepository extends JpaRepository<Cancion,Long>{

}
