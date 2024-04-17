package com.example.backend.security.auth.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AuthenticationException extends RuntimeException{

    public AuthenticationException(String message) {
        super(message);
    }

    public static UsernameNotFoundException notFound(String username) {
        return new UsernameNotFoundException("%s not found".formatted(username));
    }
}
