package com.example.testing.controller;

import com.example.testing.dto.logindto.LoginReqDTO;
import com.example.testing.dto.logindto.LoginResDTO;
import com.example.testing.dto.playerdto.PlayerReqDTO;
import com.example.testing.dto.playerdto.PlayerResDto;
import com.example.testing.model.Player;
import com.example.testing.service.PlayerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
@Validated
public class PlayerController {
    private final PlayerService service;

    public PlayerController(PlayerService service) {
        this.service = service;
    }

    //ADMIN CONTROLLER

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<PlayerResDto>> getAllPlayers(@AuthenticationPrincipal Player player) {
        List<PlayerResDto> players = service.getAllPlayers(player);
        return ResponseEntity.ok(players);
    }
    @GetMapping("/{playerId}")
    @PreAuthorize("hasRole('ADMIN') or #pid == authentication.principal.playerId")
    public ResponseEntity<PlayerResDto> getPlayerById(
            @AuthenticationPrincipal Player player,
            @P("pid") @PathVariable Long playerId) {
        PlayerResDto res = service.getPlayerById(playerId);
        return ResponseEntity.ok(res);
    }

    //PUBLIC CONTROLLER
    @PostMapping("/register")
    public ResponseEntity<PlayerResDto> createNewPlayer(@Valid @RequestBody LoginReqDTO playerReq) {
        PlayerResDto res = service.registerNewPlayer(playerReq);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResDTO> authPlayer(@Valid @RequestBody LoginReqDTO playerReq) {
        LoginResDTO res = service.authPlayer(playerReq);
        return ResponseEntity.ok(res);
    }

    // USER CONTROLLER
    @PatchMapping("/{playerId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PlayerResDto> updatePlayerRoles(
            @AuthenticationPrincipal Player player,
            @PathVariable Long playerId,
            @Valid @RequestBody PlayerReqDTO rolesToSet) {
        PlayerResDto updated = service.updatePlayerRoles(player, rolesToSet, playerId);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{playerId}")
    @PreAuthorize("hasRole('ADMIN') or #pid == authentication.principal.playerId")
    public ResponseEntity<String> deletePlayer(
            @AuthenticationPrincipal Player player, @P("pid") @PathVariable Long playerId) {
        service.deletePlayer(playerId);
        return ResponseEntity.ok("Player " + playerId + " deleted");
    }
}
