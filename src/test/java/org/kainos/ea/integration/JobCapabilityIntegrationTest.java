package org.kainos.ea.integration;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.DropwizardWebServiceApplication;
import org.kainos.ea.DropwizardWebServiceConfiguration;
import org.kainos.ea.cli.CapabilityRequest;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    void getCapabilityById_shouldReturn400Response_whenInvalidCapabilityIdUsed() {
        int capabilityID = -1;

        Response response = APP.client()
                .target("http://localhost:8080/api/capability/" + capabilityID)
                .request()
                .get();

        assertEquals(400, response.getStatus());
    }

    @Test
    void addCapability_shouldReturnCapabilityId_whenValidCapabilityAdded() {
        CapabilityRequest capabilityRequest = new CapabilityRequest("test");

        Integer id = APP.client()
                .target("http://localhost:8080/api/capability/")
                .request()
                .post(Entity.entity(capabilityRequest, MediaType.APPLICATION_JSON_TYPE))
                .readEntity(Integer.class);

        assertNotNull(id);
    }

    @Test
    void addCapability_shouldReturn400Response_whenCapabilityNameTooLong() {
        CapabilityRequest capabilityRequest =
                new CapabilityRequest("toolongtestnametoolongtestnametoolongtestnametoolongtestnametoolongtestnametoolongtest");

        Response response= APP.client()
                .target("http://localhost:8080/api/capability/")
                .request()
                .post(Entity.entity(capabilityRequest, MediaType.APPLICATION_JSON_TYPE));

        assertEquals(400, response.getStatus());
    }
}
