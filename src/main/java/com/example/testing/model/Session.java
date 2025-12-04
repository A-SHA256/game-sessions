package com.example.testing.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"player_id", "game_id"})
}, name = "sessions")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long sessionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @Column(name = "hours_played", nullable = false)
    private Integer hoursPlayed;

    @Column(name = "last_session")
    private LocalDateTime lastSession;
    public Session(){}

    public Long getSessionId() {
        return sessionId;
    }
    public Player getPlayer() {
        return player;
    }
    public Game getGame() {
        return game;
    }
    public Integer getHoursPlayed() {
        return hoursPlayed;
    }
    public LocalDateTime getLastSession() {
        return lastSession;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public void setGame(Game game) {
        this.game = game;
    }
    public void setHoursPlayed(Integer hoursPlayed) {
        this.hoursPlayed = hoursPlayed;
    }
    public void setLastSession(LocalDateTime lastSession) {
        this.lastSession = lastSession;
    }

    public void addHoursPlayed(Integer adding) {
        this.hoursPlayed += adding;
    }
}
