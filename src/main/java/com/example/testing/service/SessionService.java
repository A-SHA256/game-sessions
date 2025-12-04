package com.example.testing.service;

import com.example.testing.dto.sessiondto.SessionReqDTO;
import com.example.testing.dto.sessiondto.SessionResDTO;
import com.example.testing.exception.ObjectNotFoundEx;
import com.example.testing.mappers.SessionMapper;
import com.example.testing.model.Game;
import com.example.testing.model.Player;
import com.example.testing.model.enums.Role;
import com.example.testing.model.Session;
import com.example.testing.repository.GameRepository;
import com.example.testing.repository.PlayerRepository;
import com.example.testing.repository.SessionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class SessionService {
    private final SessionRepository sessionRepository;
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;
    private final SessionMapper sessionMapper;


    public SessionService(SessionRepository sessionRepository,
                          GameRepository gameRepository,
                          PlayerRepository playerRepository,
                          SessionMapper sessionMapper) {
        this.sessionRepository = sessionRepository;
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
        this.sessionMapper = sessionMapper;
    }

    //ADMIN METHODS
    public List<SessionResDTO> getAllSessions(Player player) {
        if (!player.getRoles().contains(Role.ADMIN)) throw new RuntimeException("Illegal access");
        List<Session> fetched = sessionRepository.findAllSessionsByPlayerAndGame();
        return fetched.stream().map(sessionMapper::toSessionDTO).toList();
    }

    //NON-TRANSACTIONAL PLAYER METHODS
    public List<SessionResDTO> getSessionsByPlayerId(Player player) {
        List<Session> fetched = sessionRepository.findAllSessionsByPlayer(player.getPlayerId());
        return fetched.stream().map(sessionMapper::toSessionDTO).toList();
    }

    public SessionResDTO getSessionByPlayerIdAndGameId(Player player, Long gameId) {
        Session fetched = sessionRepository.findSessionByPlayerIdAndGameId(player.getPlayerId(), gameId)
                .orElseThrow(() -> new ObjectNotFoundEx("Session not found"));
        return sessionMapper.toSessionDTO(fetched);
    }

    //TRANSACTIONAL PLAYER METHODS
    @Transactional
    public SessionResDTO createNewSession(Player player, Long gameId, SessionReqDTO req) {
        Game fetchGame = gameRepository.findById(gameId)
                .orElseThrow(() -> new ObjectNotFoundEx("Game not found"));;
        Player fetchPlayer = playerRepository.findById(player.getPlayerId())
                .orElseThrow(() -> new ObjectNotFoundEx("Player not found"));;
        Session created = sessionMapper.fromSessionDTO(fetchPlayer, fetchGame, req);
        Session saved = sessionRepository.save(created);
        return sessionMapper.toSessionDTO(saved);
    }

    @Transactional
    public SessionResDTO updateSession(Player player, Long gameId, SessionReqDTO req) {
        Session fetchSession = sessionRepository.findSessionByPlayerIdAndGameId(player.getPlayerId(), gameId)
                .orElseThrow(() -> new ObjectNotFoundEx("Session found"));
        Session updated = sessionMapper.fromSessionDTO(fetchSession, req);
        Session saved = sessionRepository.save(updated);
        return sessionMapper.toSessionDTO(saved);
    }

    @Transactional
    public void deleteSession(Player player, Long gameId) {
        Session fetchSession = sessionRepository.findSessionByPlayerIdAndGameId(player.getPlayerId(), gameId)
                .orElseThrow(() ->new ObjectNotFoundEx("Session not found"));
        Player fetchPlayer = playerRepository.findById(player.getPlayerId())
                .orElseThrow(() -> new ObjectNotFoundEx("Player not found"));
        fetchPlayer.deleteSession(fetchSession);
        sessionRepository.delete(fetchSession);
    }
}



