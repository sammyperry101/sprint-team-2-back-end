package org.kainos.ea.integration;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.DropwizardWebServiceApplication;
import org.kainos.ea.DropwizardWebServiceConfiguration;
import org.kainos.ea.cli.JobRoleEditRequest;
import org.kainos.ea.cli.JobRoleRequest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(DropwizardExtensionsSupport.class)
public class JobRoleIntegrationTest {

    static final DropwizardAppExtension<DropwizardWebServiceConfiguration> APP = new DropwizardAppExtension<>(
            DropwizardWebServiceApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    void viewRoles_ShouldReturnListofJobRoles() {

        List responseBody = APP.client().target("http://localhost:8080/api/job-roles")
                .request()
                .get(List.class);

        Assertions.assertTrue(responseBody.size() > 0);
    }

    @Test
    void editRole_ShouldEditJobRoleSuccessfully() {
        Client client = ClientBuilder.newClient();

        // Define the Job Role data to update
        JobRoleRequest updatedJobRole = new JobRoleRequest(2,"NewName", "NewSpec",
                "NewResponsibilities", "https://kainossoftwareltd.sharepoint.com/SitePages/Home.aspx", "", "");

        // Make an HTTP PUT request to edit the Job Role (assuming a specific Job Role ID)
        Response response = client.target("http://localhost:8080/api/job-roles/50")
                .request()
                .put(Entity.entity(updatedJobRole, MediaType.APPLICATION_JSON_TYPE));

        try {
            // Verify the HTTP response status code (200 OK for a successful update)
            assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        } finally {
            // Close the client and release resources
            response.close();
            client.close();
        }
    }
    @Test
    public void getRoleById_ShouldReturnJobRole(){

        int id = 12;

        Object responseObject = APP.client()
                .target("http://localhost:8080/api/job-roles/" + id)
                .request()
                .get(Object.class);


        assertNotNull(responseObject);
    }
}
