package org.kainos.ea.integration;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.DropwizardWebServiceApplication;
import org.kainos.ea.DropwizardWebServiceConfiguration;
import org.kainos.ea.cli.JobRole;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
public class JobRoleIntegrationTest {

    static final DropwizardAppExtension<DropwizardWebServiceConfiguration> APP = new DropwizardAppExtension<>(
            DropwizardWebServiceApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    void viewRoles_ShouldReturnListofJobRoles(){
        Response response = APP.client().target("http://localhost:8080/api/job-roles")
                .request().get();

        List<JobRole> responseBody = APP.client().target("http://localhost:8080/api/job-roles")
                .request()
                .get(List.class);

        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertFalse(responseBody.isEmpty());
    }
}