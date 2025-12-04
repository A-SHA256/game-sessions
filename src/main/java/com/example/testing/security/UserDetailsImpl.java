package com.example.testing.security;

import com.example.testing.repository.PlayerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsImpl implements UserDetailsService {
    private final PlayerRepository playerRepository;
    public UserDetailsImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return playerRepository.findByPlayerName(username);
    }
}
