package com.example.testing.repository;

import com.example.testing.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {
    @Query("SELECT s FROM Session s " +
            "JOIN s.player p " +
            "JOIN s.game g")
    List<Session> findAllSessionsByPlayerAndGame();

    @Query("SELECT s FROM Session s " +
            "JOIN s.player p " +
            "JOIN s.game g " +
            "WHERE p.playerId = :playerId")
    List<Session> findAllSessionsByPlayer(@Param("playerId") Long playerId);

    @Query("SELECT s FROM Session s " +
            "JOIN s.player p " +
            "JOIN s.game g " +
            "WHERE p.playerId = :playerId AND g.gameId = :gameId")
    Optional<Session> findSessionByPlayerIdAndGameId(@Param("playerId") Long playerId, @Param("gameId") Long gameId);

}
