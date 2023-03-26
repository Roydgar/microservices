package org.example.microservices;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.cucumber.core.backend.ObjectFactory;

public final class CustomObjectFactory implements ObjectFactory {

    private Injector injector;

    public CustomObjectFactory() {
        // Create an injector with our service module
        this.injector = Guice.createInjector(new TestContextModule());
    }

    @Override
    public boolean addClass( Class< ? > clazz ) {
        return true;
    }

    @Override
    public void start() {
    }

    @Override
    public void stop() {
    }

    @Override
    public < T > T getInstance(Class< T > clazz ) {
        return this.injector.getInstance( clazz );
    }
}