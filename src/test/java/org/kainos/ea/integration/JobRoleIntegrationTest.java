package org.kainos.ea.integration;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.DropwizardWebServiceApplication;
import org.kainos.ea.DropwizardWebServiceConfiguration;
import org.kainos.ea.cli.JobRoleRequest;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    void createJobRole_shouldReturnIdOfCreatedJobRole(){
        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Name",
                "Spec",
                "Responsibilities",
                "Sharepoint",
                1,
                1
        );

        Response response = APP.client().target("http://localhost:8080/api/job-roles")
                .request().post(Entity.entity(jobRoleRequest, MediaType.APPLICATION_JSON_TYPE));

        assertEquals(201, response.getStatus());
        assertTrue(response.readEntity(Integer.class) > 1);
    }

    @Test
    void createJobRole_shouldReturn400_whenInvalidJobRole(){
        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Name",
                "Spec",
                "Responsibilities",
                "Sharepoint",
                -1,
                -1
        );

        Response response = APP.client().target("http://localhost:8080/api/job-roles")
                .request().post(Entity.entity(jobRoleRequest, MediaType.APPLICATION_JSON_TYPE));

        assertEquals(400, response.getStatus());
    }
}
