package com.example.testing.dto.gamedto;

import com.example.testing.model.enums.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class GameReqDTO {
    @NotBlank(message = "Game title is required")
    private String gameTitle;

    @NotNull(message = "Genre is required")
    private Genre gameGenre;

    public String getGameTitle() {
        return gameTitle;
    }

    public Genre getGameGenre() {
        return gameGenre;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public void setGenre(Genre genre) {
        this.gameGenre = genre;
    }
}
