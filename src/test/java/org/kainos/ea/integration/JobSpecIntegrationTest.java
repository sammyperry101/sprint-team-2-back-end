package org.kainos.ea.integration;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.DropwizardWebServiceApplication;
import org.kainos.ea.DropwizardWebServiceConfiguration;
import org.kainos.ea.model.JobSpec;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(DropwizardExtensionsSupport.class)

public class JobSpecIntegrationTest {
    static final DropwizardAppExtension<DropwizardWebServiceConfiguration> APP = new DropwizardAppExtension<>(
            DropwizardWebServiceApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    void getJobSpecification_shouldReturnJobSpecification_ifIdIsValid() {
        JobSpec jobSpec = APP.client().target("http://localhost:8080/api/job-specification/3")
                .request().get(JobSpec.class);

        assertNotNull(jobSpec);
        assertEquals(3, jobSpec.getRoleId());
    }

    @Test
    void getJobSpecification_shouldReturnBadRequest_idIdIsInvalid(){
        Response response = APP.client().target("http://localhost:8080/api/job-specification/0")
                .request().get();

        assertEquals(400, response.getStatus());
    }
}
