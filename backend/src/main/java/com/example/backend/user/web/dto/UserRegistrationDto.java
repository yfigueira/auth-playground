package com.example.backend.user.web.dto;

public record UserRegistrationDto(
        String firstName,
        String lastName,
        String email,
        String password
) {
}
