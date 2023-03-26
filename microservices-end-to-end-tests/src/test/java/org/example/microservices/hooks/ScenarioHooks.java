package org.example.microservices.hooks;

import io.cucumber.java.After;
import org.example.microservices.context.ScenarioContext;

public class ScenarioHooks {

    @After
    public void cleanUpScenarioContext() {
        ScenarioContext.INSTANCE.cleanUp();
    }
}
