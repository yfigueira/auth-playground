package com.example.backend.security.auth.domain;

import java.util.UUID;

public record LoginResponse(
        UUID id,
        String token
) {
}
