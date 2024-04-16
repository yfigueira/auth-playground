package com.example.backend.user.domain;

import lombok.Builder;

@Builder
public record UserRegistration(
        String firstName,
        String lastName,
        String email,
        String password,
        String repeatedPassword
) {
}
