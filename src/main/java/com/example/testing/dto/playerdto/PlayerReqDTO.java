package com.example.testing.dto.playerdto;

import com.example.testing.model.enums.Role;

import java.util.Set;

public class PlayerReqDTO {
    private Set<Role> roles;

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
