package com.example.microservices.customer.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        MethodValidationPostProcessor mvProcessor = new MethodValidationPostProcessor();
        mvProcessor.setProxyTargetClass(true);
        return mvProcessor;
    }
}
