package com.example.testing.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final UserDetailsImpl userDetailsService;
    public JwtAuthenticationFilter(JwtUtils jwtUtils,
                                   UserDetailsImpl userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = header.substring(7);
        try{
            if(jwtUtils.isTokenValid(token)) {
                String username = jwtUtils.getUsername(token);
                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails player = userDetailsService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authUser = new UsernamePasswordAuthenticationToken(
                            player,
                            null,
                            player.getAuthorities()
                    );
                    SecurityContextHolder.getContext().setAuthentication(authUser);
                }
            }
        } catch (UsernameNotFoundException ignored) {
        }
        filterChain.doFilter(request, response);
    }
}
