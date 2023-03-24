package com.example.microservices.order.controller.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

import static java.util.stream.Collectors.toList;

@ControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationConstraintResponse> handleValidationExceptions(MethodArgumentNotValidException exception) {
        List<FieldConstraintErrorEntry> validationErrorEntries = exception.getBindingResult().getAllErrors().stream()
                .map(error -> FieldConstraintErrorEntry.of(((FieldError)error).getField(), error.getDefaultMessage()))
                .collect(toList());

        ValidationConstraintResponse responseBody = ValidationConstraintResponse.of(validationErrorEntries);

        return ResponseEntity.badRequest().body(responseBody);
    }
}
