package com.online.voting.result.handler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handleNotFound(
            ResourceNotFoundException ex) {

        Map<String, Object> response = new HashMap<>();

        response.put("timestamp",
                LocalDateTime.now());

        response.put("status", 404);

        response.put("error",
                "Not Found");

        response.put("message",
                ex.getMessage());

        return response;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleGeneric(
            Exception ex) {

        Map<String, Object> response = new HashMap<>();

        response.put("timestamp",
                LocalDateTime.now());

        response.put("status", 500);

        response.put("error",
                "Internal Server Error");

        response.put("message",
                ex.getMessage());

        return response;
    }
}
