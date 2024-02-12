package com.example.backend.model;

import com.example.backend.persistence.UserStatus;

public class UserDTO {

    private String username;
    private String password;
    private String complete_name;
    private UserStatus status;

    // Constructors, getters, and setters

    public UserDTO() {
    }

    public UserDTO(String username, String password, String complete_name, UserStatus status) {
        this.username = username;
        this.password = password;
        this.complete_name = complete_name;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setComplete_name(String complete_name) {
        this.complete_name = complete_name;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getComplete_name() {
        return complete_name;
    }
}