package com.example.microservices.order.controller.advice;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class FieldConstraintErrorEntry {
    private final String fieldName;
    private final String validationMessage;
}
