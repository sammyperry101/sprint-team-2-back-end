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
public class JobFamilyIntegrationTest {

    static final DropwizardAppExtension<DropwizardWebServiceConfiguration> APP =
            new DropwizardAppExtension<>(
                    DropwizardWebServiceApplication.class,
                    null,
                    new ResourceConfigurationSourceProvider()
            );

    @Test
    void getFamilyByCapability_shouldReturnListOfFamilies_whenValidCapabilityIdUsed() {
        int capabilityID = 1;

        List responseBody = APP.client()
                .target("http://localhost:8080/api/view-families-by-capability/" + capabilityID)
                .request()
                .get(List.class);

        assertFalse(responseBody.isEmpty());
    }
}
