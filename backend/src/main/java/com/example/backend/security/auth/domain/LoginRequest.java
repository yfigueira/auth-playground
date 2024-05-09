package com.example.backend.security.auth.domain;

public record LoginRequest(
        String username,
        String password
) {
}
