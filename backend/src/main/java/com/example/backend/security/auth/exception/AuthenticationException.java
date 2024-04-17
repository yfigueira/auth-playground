package com.example.backend.security.auth.exception;

public class AuthenticationException extends RuntimeException{

    public AuthenticationException(String message) {
        super(message);
    }

    public static AuthenticationException notFound(String username) {
        return new AuthenticationException("%s not found".formatted(username));
    }
}
