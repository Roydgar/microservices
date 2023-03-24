package com.example.microservices.order.controller.advice;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "of")
class ErrorResponse {
    private final String errorMessage;
}
