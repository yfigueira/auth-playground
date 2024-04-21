package com.example.backend.security.auth.exception;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErrorResponse(
        String status,
        String causedBy,
        LocalDateTime timestamp
) {
}
