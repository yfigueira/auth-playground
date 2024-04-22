package com.example.backend.security.auth.web.advice;

import com.example.backend.common.exception.ErrorResponse;
import com.example.backend.common.exception.Exception;
import com.example.backend.security.auth.exception.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthenticationControllerAdvice {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentials(BadCredentialsException exception) {
        return Exception.handle(HttpStatus.UNAUTHORIZED, exception.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handlePasswordMismatch(AuthenticationException exception) {
        return Exception.handle(HttpStatus.CONFLICT, exception.getMessage());
    }
}
