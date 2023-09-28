package org.kainos.ea.resources;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.api.JobRoleService;
import org.kainos.ea.cli.JobRole;
import org.kainos.ea.cli.JobRolePostRequest;
import org.kainos.ea.client.FailedToCreateJobRoleException;
import org.kainos.ea.client.FailedToGetJobRoles;
import org.kainos.ea.client.InvalidJobRoleException;
import org.kainos.ea.client.JobRolesNotFoundException;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class JobRoleControllerTest {

    JobRoleService jobRoleServiceMock = Mockito.mock(JobRoleService.class);

    JobRoleController jobRoleController = new JobRoleController(jobRoleServiceMock);
    JobRolePostRequest jobRolePostRequestMock = Mockito.mock(JobRolePostRequest.class);
    @Test
    void viewRoles_ShouldReturnResponse200_WhenJobRolesRetrieved()
            throws JobRolesNotFoundException, FailedToGetJobRoles
    {
        List<JobRole> expectedRoles = new ArrayList<>();

        Mockito.when(jobRoleServiceMock.viewRoles()).thenReturn(expectedRoles);

        Response response = jobRoleController.viewRoles();

        assertEquals(200, response.getStatus());
    }

    @Test
    void viewRoles_ShouldReturnResponse500_WhenServiceThrowsJobRolesNotFound()
            throws JobRolesNotFoundException, FailedToGetJobRoles {

        Mockito.when(jobRoleServiceMock.viewRoles()).thenThrow(JobRolesNotFoundException.class);

        Response response = jobRoleController.viewRoles();

        assertEquals(500, response.getStatus());
    }

    @Test
    void viewRoles_ShouldReturnResponse500_WhenServiceThrowsFailedToGetJobRoles()
            throws JobRolesNotFoundException, FailedToGetJobRoles {

        Mockito.when(jobRoleServiceMock.viewRoles()).thenThrow(FailedToGetJobRoles.class);

        Response response = jobRoleController.viewRoles();

        assertEquals(500, response.getStatus());
    }

    @Test
    void createJobRole_shouldReturnResponse201_whenJobRoleCreated()
            throws InvalidJobRoleException, FailedToCreateJobRoleException
    {
        Mockito.when(jobRoleServiceMock.createJobRole(jobRolePostRequestMock)).thenReturn(1);

        Response response = jobRoleController.createJobRole(jobRolePostRequestMock);

        assertEquals(201, response.getStatus());
        assertEquals(1, response.getEntity());
    }

    @Test
    void createJobRole_shouldReturnResponse400_whenServiceThrowsInvalidJobRoleException()
            throws InvalidJobRoleException, FailedToCreateJobRoleException
    {
        Mockito.when(jobRoleServiceMock.createJobRole(jobRolePostRequestMock)).thenThrow(InvalidJobRoleException.class);

        Response response = jobRoleController.createJobRole(jobRolePostRequestMock);

        assertEquals(400, response.getStatus());
    }

    @Test
    void createJobRole_shouldReturnResponse500_whenServiceThrowsFailedToGetJobRoles()
            throws InvalidJobRoleException, FailedToCreateJobRoleException
    {
        Mockito.when(jobRoleServiceMock.createJobRole(jobRolePostRequestMock))
                .thenThrow(FailedToCreateJobRoleException.class);

        Response response = jobRoleController.createJobRole(jobRolePostRequestMock);

        assertEquals(500, response.getStatus());
    }
}