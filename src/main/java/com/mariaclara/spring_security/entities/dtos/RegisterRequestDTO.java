package com.mariaclara.spring_security.entities.dtos;

import com.mariaclara.spring_security.entities.UserRole;

public record RegisterRequestDTO(String username, String password, UserRole roles) {
}
