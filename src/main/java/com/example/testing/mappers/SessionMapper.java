package com.example.testing.mappers;

import com.example.testing.dto.sessiondto.SessionReqDTO;
import com.example.testing.dto.sessiondto.SessionResDTO;
import com.example.testing.model.Game;
import com.example.testing.model.Player;
import com.example.testing.model.Session;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class SessionMapper {
    public SessionMapper(){}
    public SessionResDTO toSessionDTO(Session s) {
        SessionResDTO dto = new SessionResDTO();
        dto.setSessionId(s.getSessionId());
        dto.setPlayerName(s.getPlayer().getPlayerName());
        dto.setGameTitle(s.getGame().getGameTitle());
        dto.setHoursPlayed(s.getHoursPlayed());
        dto.setLastSession(s.getLastSession());
        return dto;
    }

    public Session fromSessionDTO(Player p, Game g, SessionReqDTO dto) {
        Session s = new Session();
        s.setHoursPlayed(dto.getHoursPlayed());
        s.setLastSession(LocalDateTime.now());
        p.addSession(s);
        g.addSession(s);
        return s;
    }
    public Session fromSessionDTO(Session s, SessionReqDTO dto) {
        s.addHoursPlayed(dto.getHoursPlayed());
        s.setLastSession(LocalDateTime.now());
        return s;
    }
}
