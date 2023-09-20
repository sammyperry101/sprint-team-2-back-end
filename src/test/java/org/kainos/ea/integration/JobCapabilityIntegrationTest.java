package org.kainos.ea.integration;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.DropwizardWebServiceApplication;
import org.kainos.ea.DropwizardWebServiceConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(DropwizardExtensionsSupport.class)
public class JobCapabilityIntegrationTest {
    static final DropwizardAppExtension<DropwizardWebServiceConfiguration> APP =
            new DropwizardAppExtension<>(
                    DropwizardWebServiceApplication.class,
                    null,
                    new ResourceConfigurationSourceProvider()
            );

    @Test
    void getAllCapabilities_shouldReturnListOfCapabilities_whenServerReturnsCapabilityList() {
        List responseBody = APP.client()
                .target("http://localhost:8080/api/capability/")
                .request()
                .get(List.class);

        assertFalse(responseBody.isEmpty());
    }

    @Test
    void getCapabilityById_shouldReturnCapability_whenValidCapabilityIdUsed() {
        int capabilityID = 1;

        Object responseObject = APP.client()
                .target("http://localhost:8080/api/capability/" + capabilityID)
                .request()
                .get(Object.class);

        assertNotNull(responseObject);
    }
}
