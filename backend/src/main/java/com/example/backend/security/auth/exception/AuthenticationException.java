package com.example.backend.security.auth.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AuthenticationException extends RuntimeException{

    public AuthenticationException(String message) {
        super(message);
    }

    public static UsernameNotFoundException notFound(String username) {
        return new UsernameNotFoundException("Username not found: %s".formatted(username));
    }

    public static PasswordMismatchException passwordMismatch() {
        return new PasswordMismatchException("Password mismatch");
    }

    public static UsernameConflictException usernameConflict(String username) {
        return new UsernameConflictException("Username already exists: %s".formatted(username));
    }

    public static class PasswordMismatchException extends RuntimeException {
        public PasswordMismatchException(String message) { super(message); }
    }

    public static class UsernameConflictException extends RuntimeException {
        public UsernameConflictException(String message) { super(message); }
    }
}
