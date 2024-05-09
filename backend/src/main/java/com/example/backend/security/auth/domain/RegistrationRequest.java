package com.example.backend.security.auth.domain;

import lombok.Builder;

@Builder
public record RegistrationRequest(

        String firstName,
        String lastName,
        String email,
        String password,
        String repeatedPassword
) {
}
