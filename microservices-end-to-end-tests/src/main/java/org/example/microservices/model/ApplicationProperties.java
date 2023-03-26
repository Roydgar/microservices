package org.example.microservices.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApplicationProperties {
    private final String customerServiceApiUrl;
    private final String orderServiceServiceApiUrl;
}
