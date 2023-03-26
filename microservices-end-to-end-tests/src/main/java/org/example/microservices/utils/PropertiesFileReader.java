package org.example.microservices.utils;

import org.apache.commons.lang3.StringUtils;
import org.example.microservices.exception.InvalidConfigurationException;
import org.example.microservices.model.ApplicationProperties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;

public class PropertiesFileReader {
    private static final String PROPERTIES_FILE_TEMPLATE = "application-%s.properties";
    private static final String CUSTOMER_SERVICE_URL_KEY = "service.customer.api.url";
    private static final String ORDER_SERVICE_URL_KEY = "service.order.api.url";

    public ApplicationProperties readProperties(String profile) {
        String propertiesFileName = format(PROPERTIES_FILE_TEMPLATE, profile);
        Properties properties = new Properties();

        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        try(InputStream resourceStream = loader.getResourceAsStream(propertiesFileName)) {
            properties.load(resourceStream);
        } catch (IOException e) {
            throw new InvalidConfigurationException("Cannot read properties file " + propertiesFileName, e);
        }

        return ApplicationProperties.builder()
                .customerServiceApiUrl(readProperty(properties, CUSTOMER_SERVICE_URL_KEY))
                .orderServiceServiceApiUrl(readProperty(properties, ORDER_SERVICE_URL_KEY))
                .build();
    }

    private String readProperty(Properties properties, String propertyKey) {
        return ofNullable(properties.getProperty(propertyKey))
                .filter(StringUtils::isNotBlank)
                .orElseThrow(() -> new InvalidConfigurationException("Property " + ORDER_SERVICE_URL_KEY + " is not defined"));
    }
}
