package com.example.testing.service;

import com.example.testing.dto.gamedto.GameReqDTO;
import com.example.testing.dto.gamedto.GameResDTO;
import com.example.testing.exception.ObjectNotFoundEx;
import com.example.testing.mappers.GameMapper;
import com.example.testing.model.Game;
import com.example.testing.model.Player;
import com.example.testing.repository.GameRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final GameMapper gameMapper;
    public GameService(GameRepository gameRepository, GameMapper gameMapper) {
        this.gameRepository = gameRepository;
        this.gameMapper = gameMapper;
    }

    //PUBLIC SERVICE
    public List<GameResDTO> getAllGames() {
        List<GameResDTO> dtoList = new ArrayList<>();
        List<Game> games = gameRepository.findAll();
        if (!games.isEmpty()) {
            games.forEach(game -> dtoList.add(gameMapper.toGameDTO(game)));
        }
        return dtoList;
    }

    public GameResDTO getGameById(Long gameId) {
        Game g = gameRepository.findById(gameId).orElseThrow(
                () -> new ObjectNotFoundEx("Game not found")
        );
        return gameMapper.toGameDTO(g);
    }

    //PRIVATE SERVICE
    @Transactional
    public GameResDTO createNewGame(Player player, GameReqDTO req) {
        Game addedGame = gameRepository.save(gameMapper.fromGameDTO(req));
        return gameMapper.toGameDTO(addedGame);
    }

    @Transactional
    public void deleteGame(Player player, Long gameId) {
        gameRepository.delete(gameRepository.findById(gameId).orElseThrow(
                () -> new ObjectNotFoundEx("Game not found")
        ));
    }
}
