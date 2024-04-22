package com.example.backend.security.auth.web.advice;

import com.example.backend.security.auth.exception.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class AuthenticationControllerAdvice {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentials(BadCredentialsException exception) {
        return handle(HttpStatus.UNAUTHORIZED, exception.getMessage());
    }

    private ResponseEntity<ErrorResponse> handle(HttpStatus status, String message) {
        var errorResponse = ErrorResponse.builder()
                .status(status)
                .causedBy(message)
                .timestamp(LocalDateTime.now())
                .build();

        log.error("%s [ %s ]: %s".formatted(errorResponse.timestamp(), errorResponse.status(), errorResponse.causedBy()));

        return new ResponseEntity<>(errorResponse, status);
    }
}
