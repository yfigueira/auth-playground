package com.example.backend.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Slf4j
public class Exception {

    public static ResponseEntity<ErrorResponse> handle(HttpStatus status, String message) {
        var errorResponse = ErrorResponse.builder()
                .status(status)
                .causedBy(message)
                .timestamp(LocalDateTime.now())
                .build();

        log.error("%s [ %s ]: %s".formatted(errorResponse.timestamp(), errorResponse.status(), errorResponse.causedBy()));

        return new ResponseEntity<>(errorResponse, status);
    }
}
