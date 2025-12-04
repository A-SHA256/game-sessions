package com.example.testing.controller;

import com.example.testing.dto.sessiondto.SessionReqDTO;
import com.example.testing.dto.sessiondto.SessionResDTO;
import com.example.testing.model.Player;
import com.example.testing.service.SessionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sessions")
@Validated
public class SessionController {
    private final SessionService service;
    public SessionController(SessionService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<SessionResDTO>> getAllSessions(@AuthenticationPrincipal Player player) {
        List<SessionResDTO> sessions = service.getAllSessions(player);
        return ResponseEntity.ok(sessions);
    }

    @GetMapping("/player/games/{gameId}")
    public ResponseEntity<SessionResDTO> getSessionByPlayerIdAngGameId(
            @AuthenticationPrincipal Player player,
            @PathVariable Long gameId) {
        SessionResDTO session = service.getSessionByPlayerIdAndGameId(player, gameId);
        return ResponseEntity.ok(session);
    }
    @GetMapping("/player/games")
    public ResponseEntity<List<SessionResDTO>> getSessionByPlayerId(
            @AuthenticationPrincipal Player player) {
        List<SessionResDTO> sessions = service.getSessionsByPlayerId(player);
        return ResponseEntity.ok(sessions);
    }

    @PostMapping("/player/games/{gameId}")
    public ResponseEntity<SessionResDTO> createNewSession(
            @AuthenticationPrincipal Player player,
            @PathVariable Long gameId,
            @Valid @RequestBody SessionReqDTO sessionReq) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createNewSession(player, gameId, sessionReq));
    }

    @PatchMapping("/player/games/{gameId}")
    public ResponseEntity<SessionResDTO> updateSession(
            @AuthenticationPrincipal Player player,
            @PathVariable Long gameId,
            @Valid @RequestBody SessionReqDTO sessionReq) {
        return ResponseEntity.ok(service.updateSession(player, gameId, sessionReq));
    }

    @DeleteMapping("/player/games/{gameId}")
    public ResponseEntity<String> deleteSession(
            @AuthenticationPrincipal Player player,
            @PathVariable Long gameId) {
        service.deleteSession(player, gameId);
        return ResponseEntity.ok("Session deleted");
    }
}
