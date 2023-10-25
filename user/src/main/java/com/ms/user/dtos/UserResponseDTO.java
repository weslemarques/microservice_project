package com.ms.user.dtos;

import com.ms.user.model.UserModel;

import java.io.Serializable;
import java.util.UUID;

public class UserResponseDTO implements Serializable {
    private UUID userId;
    private String name;
    private String email;

    public UserResponseDTO(UUID userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }
    public UserResponseDTO(UserModel model) {
        this.userId = model.getUserId();
        this.name = model.getName();
        this.email = model.getEmail();
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
