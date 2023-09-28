package org.kainos.ea.integration;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.DropwizardWebServiceApplication;
import org.kainos.ea.DropwizardWebServiceConfiguration;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(DropwizardExtensionsSupport.class)
public class FamilyIntegrationTest {
    static final DropwizardAppExtension<DropwizardWebServiceConfiguration> APP = new DropwizardAppExtension<>(
            DropwizardWebServiceApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    void getFamily_shouldReturn200(){
        Response response = APP.client().target("http://localhost:8080/api/family/1")
                .request().get(Response.class);

        assertEquals(200, response.getStatus());
        assertTrue(response.hasEntity());
    }
    @Test
    void getFamily_shouldReturn400_whenIdInvalid(){
        Response response = APP.client().target("http://localhost:8080/api/family/1000")
                .request().get(Response.class);

        assertEquals(400, response.getStatus());
        assertTrue(response.hasEntity());
    }
}
