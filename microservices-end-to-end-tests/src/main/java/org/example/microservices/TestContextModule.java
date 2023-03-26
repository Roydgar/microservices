package org.example.microservices;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import org.example.microservices.client.CustomerClient;
import org.example.microservices.client.OrderClient;
import org.example.microservices.model.ApplicationProperties;
import org.example.microservices.utils.PropertiesFileReader;

public class TestContextModule extends AbstractModule {
    private static final String PROFILE_SYSTEM_PROPERTY_KEY = "profile";
    private static final String DEFAULT_PROFILE = "local";


    @Provides
    public ApplicationProperties applicationProperties(@Named("profile") String profile) {
        return new PropertiesFileReader().readProperties(profile);
    }

    @Provides
    @Named("profile")
    public String profile() {
        return System.getProperty(PROFILE_SYSTEM_PROPERTY_KEY, DEFAULT_PROFILE);
    }

    @Provides
    public CustomerClient customerClient(ApplicationProperties properties) {
        return createFeignClient(CustomerClient.class, properties.getCustomerServiceApiUrl());
    }

    @Provides
    public OrderClient orderClient(ApplicationProperties properties) {
        return createFeignClient(OrderClient.class, properties.getOrderServiceServiceApiUrl());
    }

    private <T> T createFeignClient(Class<T> targetClientClass, String url) {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(targetClientClass, url);
    }
}
