package com.example.testing.model;

import com.example.testing.model.enums.Genre;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long gameId;


    @NotBlank
    @Column(name = "title", nullable = false, unique = true)
    private String gameTitle;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "genre")
    private Genre gameGenre;

    @OneToMany(mappedBy = "game")
    private Set<Session> sessions = new HashSet<>();

    public Game(){}
    public Game(String gameTitle, Genre gameGenre) {
        this.gameTitle = gameTitle;
        this.gameGenre = gameGenre;
    }

    public Long getGameId() {
        return gameId;
    }
    public String getGameTitle() {
        return gameTitle;
    }
    public Genre getGameGenre() {
        return gameGenre;
    }
    public Set<Session> getSessions() {
        return sessions;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }
    public void setGameGenre(Genre gameGenre) {
        this.gameGenre = gameGenre;
    }
    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }
    public void setSessions(Set<Session> sessions) {
        this.sessions = sessions;
    }

    public void addSession(Session s) {
        sessions.add(s);
        s.setGame(this);
    }
}
