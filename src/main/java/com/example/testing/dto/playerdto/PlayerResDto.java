package com.example.testing.dto.playerdto;

import com.example.testing.model.enums.Role;

import java.util.Set;

public class PlayerResDto {
    private Long playerId;
    private String playerName;
    private Set<Role> roles;

    public Long getPlayerId() {
        return playerId;
    }
    public String getPlayerName() {
        return playerName;
    }
    public Set<Role> getRoles() { return roles; }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
