package com.example.testing.dto.gamedto;

import com.example.testing.model.enums.Genre;

public class GameResDTO {
    private Long gameId;
    private String gameTitle;
    private Genre gameGenre;

    public Long getGameId() {
        return gameId;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public Genre getGameGenre() {
        return gameGenre;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public void setGameGenre(Genre gameGenre) {
        this.gameGenre = gameGenre;
    }
}
