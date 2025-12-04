package com.example.testing.dto.sessiondto;

import java.time.LocalDateTime;

public class SessionResDTO {
    private Long sessionId;
    private String playerName;
    private String gameTitle;
    private Integer hoursPlayed;
    private LocalDateTime lastSession;
    public SessionResDTO(){}
    public SessionResDTO(Long sessionId, String playerName, String gameTitle, Integer hoursPlayed, LocalDateTime lastSession) {
        this.sessionId = sessionId;
        this.playerName = playerName;
        this.gameTitle = gameTitle;
        this.hoursPlayed = hoursPlayed;
        this.lastSession = lastSession;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getGameTitle() {
        return gameTitle;
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

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public void setHoursPlayed(Integer hoursPlayed) {
        this.hoursPlayed = hoursPlayed;
    }

    public void setLastSession(LocalDateTime lastSession) {
        this.lastSession = lastSession;
    }
}
