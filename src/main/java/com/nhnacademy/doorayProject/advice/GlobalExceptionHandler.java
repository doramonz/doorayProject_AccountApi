package com.nhnacademy.doorayProject.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {ResponseStatusException.class})
    public ResponseEntity<String> exceptionHandler(ResponseStatusException e){

        return ResponseEntity.badRequest()
                .body(e.getMessage());
    }
}
