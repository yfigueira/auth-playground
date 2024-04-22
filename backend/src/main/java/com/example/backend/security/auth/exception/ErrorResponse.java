package com.example.backend.security.auth.exception;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
public record ErrorResponse(
        HttpStatus status,
        String causedBy,
        LocalDateTime timestamp
) {
}
