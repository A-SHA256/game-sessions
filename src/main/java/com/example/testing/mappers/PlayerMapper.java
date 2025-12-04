package com.example.testing.mappers;

import com.example.testing.dto.logindto.LoginReqDTO;
import com.example.testing.dto.playerdto.PlayerResDto;
import com.example.testing.model.Player;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {
    public PlayerMapper(){}
    public PlayerResDto toPlayerDTO(Player p) {
        PlayerResDto dto = new PlayerResDto();
        dto.setPlayerId(p.getPlayerId());
        dto.setPlayerName(p.getPlayerName());
        dto.setRoles(p.getRoles());
        return dto;
    }
    public Player fromPlayerDTO(LoginReqDTO dto) {
        return new Player(dto.getPlayerName());
    }
}
