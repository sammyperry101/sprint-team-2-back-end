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

@ExtendWith(DropwizardExtensionsSupport.class)
public class JobBandIntegrationTest {
    static final DropwizardAppExtension<DropwizardWebServiceConfiguration> APP =
            new DropwizardAppExtension<>(
                    DropwizardWebServiceApplication.class,
                    null,
                    new ResourceConfigurationSourceProvider()
            );

    @Test
    void getAllBands_shouldReturnListOfBands_whenServerReturnsBandList() {
        List responseBody = APP.client()
                .target("http://localhost:8080/api/band/")
                .request()
                .get(List.class);

        assertFalse(responseBody.isEmpty());
    }
}
