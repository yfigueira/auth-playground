package com.example.backend.security.auth.web.dto;

public record LoginRequest(
        String username,
        String password
) {
}
