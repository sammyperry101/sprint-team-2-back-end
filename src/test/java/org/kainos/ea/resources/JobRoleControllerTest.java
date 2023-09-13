package org.kainos.ea.resources;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.DropwizardWebServiceApplication;
import org.kainos.ea.DropwizardWebServiceConfiguration;
import org.kainos.ea.api.JobRoleService;
import org.kainos.ea.cli.JobRole;
import org.kainos.ea.client.FailedToDeleteJobRoleException;
import org.kainos.ea.client.JobRoleDoesNotExistException;
import org.kainos.ea.db.JobRoleDao;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class JobRoleControllerTest {

    JobRoleService jobRoleServiceMock = Mockito.mock(JobRoleService.class);

    JobRoleController jobRoleController = new JobRoleController(jobRoleServiceMock);

    @Test
    void deleteJobRole_shouldReturn200Response_whenServiceDeletesJobRole() throws JobRoleDoesNotExistException,
            FailedToDeleteJobRoleException {
        int id = 1;
        int expectedResult = 1;
        JobRole jobRole = new JobRole(1,
                "Job Spec",
                "Responsibilities",
                "responsibilities",
                "sharepoint link",
                1,
                1);

        Mockito.when(jobRoleServiceMock.deleteRole(id)).thenReturn(expectedResult);

        Response response = jobRoleController.deleteRole(id);

        assertEquals(200, response.getStatus());
    }

//    @Test
//    void deleteJobRoleWithInvalidID_shouldReturn200Response() {
//        int id = 1;
//
//        Response response = APP.client().target("http://localhost:8080/api/job-roles/" + id)
//                .request()
//                .delete();
//
//        Assertions.assertEquals(200, response.getStatus());
//    }

}
