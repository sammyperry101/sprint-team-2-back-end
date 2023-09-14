package org.kainos.ea.resources;

import org.eclipse.jetty.server.Authentication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.api.JobRoleService;
//import org.kainos.ea.client.FailedToDeleteJobRoleException;
//import org.kainos.ea.client.JobRoleDoesNotExistException;
import org.kainos.ea.cli.JobRole;
import org.kainos.ea.client.FailedToGetJobRoles;
import org.kainos.ea.client.JobRolesNotFoundException;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class JobRoleControllerTest {

    JobRoleService jobRoleServiceMock = Mockito.mock(JobRoleService.class);

    JobRoleController jobRoleController = new JobRoleController(jobRoleServiceMock);

    /*@Test
    void deleteJobRole_shouldReturn200Response_whenServiceDeletesJobRole() throws JobRoleDoesNotExistException,
            FailedToDeleteJobRoleException {
        int id = 1;
        int expectedResult = 1;

        Mockito.when(jobRoleServiceMock.deleteRole(id)).thenReturn(expectedResult);

        Response response = jobRoleController.deleteRole(id);

        assertEquals(200, response.getStatus());
    }

    @Test
    void deleteJobRole_shouldReturn400Response_whenServiceThrowsJobRoleDoesNotExistException()
            throws JobRoleDoesNotExistException, FailedToDeleteJobRoleException {
        int id = -1;

        Mockito.when(jobRoleServiceMock.deleteRole(id)).thenThrow(JobRoleDoesNotExistException.class);

        Response response = jobRoleController.deleteRole(id);

        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    void deleteJobRole_shouldReturn500Response_whenServiceThrowsFailedToDeleteRoleException()
            throws JobRoleDoesNotExistException, FailedToDeleteJobRoleException {
        int id = -1;

        Mockito.when(jobRoleServiceMock.deleteRole(id)).thenThrow(FailedToDeleteJobRoleException.class);

        Response response = jobRoleController.deleteRole(id);

        Assertions.assertEquals(500, response.getStatus());
    }*/

    @Test
    void viewRoles_ShouldReturnResponse200_WhenJobRolesRetrieved() throws JobRolesNotFoundException, FailedToGetJobRoles {
        List<JobRole> expectedRoles = new ArrayList<>();

        Mockito.when(jobRoleServiceMock.viewRoles()).thenReturn(expectedRoles);

        Response response = jobRoleController.viewRoles();

        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void viewRoles_ShouldReturnResponse400_WhenServiceThrowsJobRolesNotFoundException() throws JobRolesNotFoundException, FailedToGetJobRoles {
        Mockito.when(jobRoleServiceMock.viewRoles()).thenThrow(JobRolesNotFoundException.class);

        Response response = jobRoleController.viewRoles();

        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    void viewRoles_ShouldReturnResponse500_WhenServiceThrowsFailedToGetJobRolesException() throws JobRolesNotFoundException, FailedToGetJobRoles {
        Mockito.when(jobRoleServiceMock.viewRoles()).thenThrow(FailedToGetJobRoles.class);

        Response response = jobRoleController.viewRoles();

        Assertions.assertEquals(500, response.getStatus());
    }

}