package com.example.testing.service;

import com.example.testing.dto.logindto.LoginReqDTO;
import com.example.testing.dto.logindto.LoginResDTO;
import com.example.testing.dto.playerdto.PlayerReqDTO;
import com.example.testing.dto.playerdto.PlayerResDto;
import com.example.testing.exception.ObjectNotFoundEx;
import com.example.testing.mappers.PlayerMapper;
import com.example.testing.model.Player;
import com.example.testing.repository.PlayerRepository;
import com.example.testing.security.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authManager;
    private final PlayerMapper playerMapper;

    public PlayerService(PlayerRepository playerRepository,
                         PasswordEncoder passwordEncoder,
                         JwtUtils jwtUtils,
                         AuthenticationManager authManager,
                         PlayerMapper playerMapper) {
        this.playerRepository = playerRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.authManager = authManager;
        this.playerMapper = playerMapper;
    }

    // PUBLIC SERVICE
    public PlayerResDto registerNewPlayer(LoginReqDTO req) {
        if(playerRepository.existsByPlayerName(req.getPlayerName())) {
            throw new IllegalArgumentException("Player with this name exists");
        }
        Player registerPlayer = playerMapper.fromPlayerDTO(req);
        registerPlayer.setHashedPassword(passwordEncoder.encode(req.getPlayerPassword()));
        Player saved = playerRepository.save(registerPlayer);
        return playerMapper.toPlayerDTO(saved);
    }

    public LoginResDTO authPlayer(LoginReqDTO req) {
        LoginResDTO res;
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getPlayerName(),
                        req.getPlayerPassword()
                )
        );

        Player loggedPlayer = (Player) auth.getPrincipal();
        Map<String, Object> claims = Map.of(
                "playerId", loggedPlayer.getPlayerId(),
                "scope", loggedPlayer.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).toList()
        );

        String token = jwtUtils.generateToken(loggedPlayer.getUsername(), claims);
        res = new LoginResDTO(token, claims);
        return res;
    }

    //PRIVATE SERVICE
    public List<PlayerResDto> getAllPlayers(Player player) {
        List<Player> players = playerRepository.findAll();
        return players.stream().map(playerMapper::toPlayerDTO).toList();
    }

    public PlayerResDto getPlayerById(Long playerId) {
        Player p = playerRepository.findById(playerId)
                .orElseThrow(() -> new ObjectNotFoundEx("Player not found"));
        if (p.getRoles() == null || p.getRoles().isEmpty()) throw new ObjectNotFoundEx("No roles fetched");
        return playerMapper.toPlayerDTO(p);
    }

    @Transactional
    public PlayerResDto updatePlayerRoles(Player player, PlayerReqDTO rolesToSet, Long playerId) {
        Player p = playerRepository.findById(playerId)
                .orElseThrow(() -> new ObjectNotFoundEx("Player not found"));
        p.setRoles(rolesToSet.getRoles());
        p = playerRepository.save(p);
        return playerMapper.toPlayerDTO(p);
    }

    @Transactional
    public void deletePlayer(Long playerId) {
        Player toDelete = playerRepository.findById(playerId)
                .orElseThrow(() -> new ObjectNotFoundEx("Player not found"));
        playerRepository.delete(toDelete);
    }
}
