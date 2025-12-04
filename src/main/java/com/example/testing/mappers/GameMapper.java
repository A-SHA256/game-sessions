package com.example.testing.mappers;

import com.example.testing.dto.gamedto.GameReqDTO;
import com.example.testing.dto.gamedto.GameResDTO;
import com.example.testing.model.Game;
import com.example.testing.model.enums.Genre;
import org.springframework.stereotype.Component;

@Component
public class GameMapper {
    public GameMapper(){};
    public GameResDTO toGameDTO(Game g) {
        GameResDTO dto = new GameResDTO();
        dto.setGameId(g.getGameId());
        dto.setGameTitle(g.getGameTitle());
        dto.setGameGenre(g.getGameGenre());
        return dto;
    }

    public Game fromGameDTO(GameReqDTO dto) {
        Game g = new Game();
        g.setGameTitle(dto.getGameTitle());
        g.setGameGenre((Genre) dto.getGameGenre());
        return g;
    }
}
