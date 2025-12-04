package com.example.testing.dto.logindto;

import java.util.Map;

public class LoginResDTO {
    private String token;
    private Map<String, Object> claims;

    public LoginResDTO(String token, Map<String, Object> claims) {
        this.token = token;
        this.claims = claims;
    }

    public String getToken() {
        return token;
    }

    public Map<String, Object> getClaims() {
        return claims;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setClaims(Map<String, Object> claims) {
        this.claims = claims;
    }
}
