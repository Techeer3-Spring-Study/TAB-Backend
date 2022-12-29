package com.techeeresc.tab.global.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BadRequestBodyException.class)
    public ResponseEntity<?> badRequestHandler(BadRequestBodyException exception) {

    }
}
