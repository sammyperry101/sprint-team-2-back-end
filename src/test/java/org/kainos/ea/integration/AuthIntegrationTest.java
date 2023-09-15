package org.kainos.ea.integration;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.DropwizardWebServiceApplication;
import org.kainos.ea.DropwizardWebServiceConfiguration;
import org.kainos.ea.cli.LoginRequest;
import org.kainos.ea.cli.Role;
import org.kainos.ea.cli.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@ExtendWith(DropwizardExtensionsSupport.class)
public class AuthIntegrationTest {
    static final DropwizardAppExtension<DropwizardWebServiceConfiguration> APP = new DropwizardAppExtension<>(
            DropwizardWebServiceApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    void login_ShouldReturnCode200() {
        LoginRequest loginRequest = new LoginRequest(
                "mateenparkar4@gmail.com",
                "password"
        );
        Response response = APP.client().target("http://localhost:8080/api/login").request().post(Entity.entity(loginRequest, MediaType.APPLICATION_JSON_TYPE));

        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void login_ShouldReturnCode401() {
        LoginRequest loginRequest = new LoginRequest(
                "mateenparkar21@gmail.com",
                "password"
        );
        Response response = APP.client().target("http://localhost:8080/api/login").request().post(Entity.entity(loginRequest, MediaType.APPLICATION_JSON_TYPE));

        Assertions.assertEquals(401, response.getStatus());
    }
}
