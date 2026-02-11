package com.apda.apda_backend.dto;

import com.apda.apda_backend.entity.Role;

public class UserDTO {

    private Long userId;
    private String username;
    private Role role;

    public UserDTO() {}

    public UserDTO(Long userId, String username, Role role) {
        this.userId = userId;
        this.username = username;
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
