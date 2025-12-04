package com.example.testing.repository;

import com.example.testing.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Player findByPlayerName(String playerName);
    boolean existsByPlayerName(String playerName);
}
