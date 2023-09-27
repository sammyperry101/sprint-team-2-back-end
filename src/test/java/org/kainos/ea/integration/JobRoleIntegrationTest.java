package org.kainos.ea.integration;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.DropwizardWebServiceApplication;
import org.kainos.ea.DropwizardWebServiceConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(DropwizardExtensionsSupport.class)
public class JobRoleIntegrationTest {

    static final DropwizardAppExtension<DropwizardWebServiceConfiguration> APP = new DropwizardAppExtension<>(
            DropwizardWebServiceApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    void viewRoles_ShouldReturnListofJobRoles(){

        List responseBody = APP.client().target("http://localhost:8080/api/job-roles")
                .request()
                .get(List.class);

        Assertions.assertTrue(responseBody.size() > 0);
    }

    @Test
    void getRoleById_ShouldReturnJobRole(){

        int id = 12;

        Object responseObject = APP.client()
                .target("http://localhost:8080/api/job-roles/" + id)
                .request()
                .get(Object.class);


        assertNotNull(responseObject);
    }
}
