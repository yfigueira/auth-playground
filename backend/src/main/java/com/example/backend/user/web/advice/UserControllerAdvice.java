package com.example.backend.user.web.advice;

import com.example.backend.common.exception.ErrorResponse;
import com.example.backend.common.exception.Exception;
import com.example.backend.user.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserControllerAdvice {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorResponse> handlePasswordMismatch(UserException exception) {
        return Exception.handle(HttpStatus.CONFLICT, exception.getMessage());
    }
}
