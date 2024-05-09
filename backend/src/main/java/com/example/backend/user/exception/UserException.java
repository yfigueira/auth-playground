package com.example.backend.user.exception;

import java.util.UUID;

public class UserException extends RuntimeException {

    public UserException(String message) {
        super(message);
    }

    public static NotFoundException notFound(Class<?> clazz, UUID id) {
        return new NotFoundException("%s with id %s not found".formatted(clazz.getSimpleName(), id));
    }

    public static class NotFoundException extends RuntimeException {
        public NotFoundException(String message) {
            super(message);
        }
    }
}
