package com.example.music_player.Repositories;

import com.example.music_player.Models.Erole;
import com.example.music_player.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByRole(Erole role);
    Boolean existsByRole(Erole role);
}
