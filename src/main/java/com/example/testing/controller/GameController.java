package com.example.testing.controller;

import com.example.testing.dto.gamedto.GameReqDTO;
import com.example.testing.dto.gamedto.GameResDTO;
import com.example.testing.model.Player;
import com.example.testing.service.GameService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
@Validated
public class GameController {
    private final GameService service;
    public GameController(GameService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<GameResDTO>> getAllGames() {
        List<GameResDTO> games = service.getAllGames();
        return ResponseEntity.ok(games);
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<GameResDTO> getGameById(@PathVariable Long gameId) {
        GameResDTO gameRes = service.getGameById(gameId);
        return ResponseEntity.ok(gameRes);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GameResDTO> createNewGame(
            @AuthenticationPrincipal Player player,
            @Valid @RequestBody GameReqDTO gameReq) {
        return ResponseEntity.ok(service.createNewGame(player, gameReq));
    }

    @DeleteMapping("/{gameId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteGame(
            @AuthenticationPrincipal Player player,
            @PathVariable Long gameId) {
        service.deleteGame(player, gameId);
        return ResponseEntity.ok("Game " + gameId + " deleted");
    }
}
