package com.example.backend.security.auth.domain;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserRegistration(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String password
) {
}
