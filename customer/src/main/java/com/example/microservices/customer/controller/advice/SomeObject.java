package com.example.microservices.customer.controller.advice;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public final class SomeObject {

    private final String value;

    public SomeObject concat(String value2) {
        String newString = this.value + value2;
        return new SomeObject(newString);
    }
}
