package com.example.backend.user.exception;

public class UserException extends RuntimeException {

    public UserException(String message) {
        super(message);
    }

    public static UserException passwordMismatch() {
        return new UserException("Password mismatch");
    }
}
