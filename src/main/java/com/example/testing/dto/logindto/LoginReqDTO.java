package com.example.testing.dto.logindto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginReqDTO {
    @NotBlank(message = "Player name is required and must be between 3 and 20 characters")
    @Size(min = 3, max = 20)
    private String playerName;

    @NotBlank(message = "Player password is required and must be between 8 and 36 characters")
    @Size(min = 8, max = 36)
    private String playerPassword;

    public String getPlayerName() {
        return playerName;
    }
    public String getPlayerPassword() {
        return playerPassword;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    public void setPlayerPassword(String playerPassword) {
        this.playerPassword = playerPassword;
    }
}
