package com.ecostack.backend.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ClassNotFoundException.class)
    public ResponseEntity<?> handleClassNotFoundException(ClassNotFoundException e) {
        return ResponseEntity.notFound()
                .build();
    }
}
