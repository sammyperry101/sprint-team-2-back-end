package org.kainos.ea.integration;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.DropwizardWebServiceApplication;
import org.kainos.ea.DropwizardWebServiceConfiguration;
import org.kainos.ea.cli.Family;
import org.kainos.ea.cli.FamilyRequest;

import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
public class FamilyIntegrationTest {

    static final DropwizardAppExtension<DropwizardWebServiceConfiguration> APP = new DropwizardAppExtension<>(
            DropwizardWebServiceApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );
    @Test
    void getFamilyByID_ShouldReturnFamily(){
        int id = 10;

        FamilyRequest responseBody = APP.client().target("http://localhost:8080/api/families/" + id)
                .request().get(FamilyRequest.class);

        Assertions.assertNotNull(responseBody.getName());
    }
}
