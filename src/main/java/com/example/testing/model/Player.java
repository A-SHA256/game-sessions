package com.example.testing.model;

import com.example.testing.model.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "players")
public class Player implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long playerId;

    @NotBlank
    @Column(name = "name", unique = true)
    private String playerName;

    @NotBlank
    @Column(name = "password")
    private String hashedPassword;

    @NotNull
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "player_roles", joinColumns = @JoinColumn(name = "player_id"))
    @Column(name = "role")
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Session> sessions = new HashSet<>();

    public Player(){}
    public Player(String playerName, String hashedPassword) {
        this.playerName = playerName;
        this.hashedPassword = hashedPassword;
        this.roles.add(Role.USER);
    }
    public Player(String playerName, String hashedPassword, Set<Role> roles) {
        this.playerName = playerName;
        this.hashedPassword = hashedPassword;
        this.roles = roles;
    }
    public Player(String playerName) {
        this.playerName = playerName;
        this.roles.add(Role.USER);
    }

    public Long getPlayerId() {
        return playerId;
    }
    public String getPlayerName() {
        return playerName;
    }
    public String getHashedPassword() {return hashedPassword;}
    public Set<Role> getRoles() {
        return roles;
    }
    public Set<Session> getSessions() {
        return sessions;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    public void setSessions(Set<Session> sessions) {
        this.sessions = sessions;
    }

    public void addSession(Session s) {
        sessions.add(s);
        s.setPlayer(this);
    }

    public void deleteSession(Session s) {
        sessions.remove(s);
        s.setPlayer(null);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role)).toList();
    }

    @Override
    public String getPassword() {
        return this.hashedPassword;
    }

    @Override
    public String getUsername() {
        return this.playerName;
    }
}
