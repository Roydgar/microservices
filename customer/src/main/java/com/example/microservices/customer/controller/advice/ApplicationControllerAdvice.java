package com.example.microservices.customer.controller.advice;

import com.example.microservices.customer.exception.CustomerAlreadyExistsException;
import com.example.microservices.customer.exception.CustomerNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleConflict(CustomerNotFoundException exception) {
        ErrorResponse responseBody = ErrorResponse.of(exception.getMessage());

        return ResponseEntity.status(NOT_FOUND).body(responseBody);
    }

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleConflict(CustomerAlreadyExistsException exception) {
        ErrorResponse responseBody = ErrorResponse.of(exception.getMessage());

        return ResponseEntity.badRequest().body(responseBody);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationConstraintResponse> handleValidationExceptions(MethodArgumentNotValidException exception) {
        List<FieldConstraintErrorEntry> validationErrorEntries = exception.getBindingResult().getAllErrors().stream()
                .map(error -> FieldConstraintErrorEntry.of(((FieldError)error).getField(), error.getDefaultMessage()))
                .collect(toList());

        ValidationConstraintResponse responseBody = ValidationConstraintResponse.of(validationErrorEntries);

        return ResponseEntity.badRequest().body(responseBody);
    }
}
