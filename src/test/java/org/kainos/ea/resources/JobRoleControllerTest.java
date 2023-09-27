package org.kainos.ea.resources;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.api.JobRoleService;
import org.kainos.ea.cli.JobRoleFilter;
import org.kainos.ea.client.FailedToGetJobRolesException;
import org.kainos.ea.cli.JobRoleRequest;

import org.kainos.ea.client.JobRolesNotFoundException;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
@ExtendWith(MockitoExtension.class)
public class JobRoleControllerTest {

    JobRoleService jobRoleServiceMock = Mockito.mock(JobRoleService.class);

    JobRoleFilter filter = Mockito.mock(JobRoleFilter.class);

    JobRoleController jobRoleController = new JobRoleController(jobRoleServiceMock);
    @Test
    void viewRoles_ShouldReturnResponse200_WhenJobRolesRetrieved() throws JobRolesNotFoundException, FailedToGetJobRolesException {
        List<JobRoleRequest> expectedRoles = new ArrayList<>();

        Mockito.when(jobRoleServiceMock.viewRoles()).thenReturn(expectedRoles);

        Response response = jobRoleController.viewRoles();

        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void viewRoles_ShouldReturnResponse500_WhenServiceThrowsJobRolesNotFound()
            throws JobRolesNotFoundException, FailedToGetJobRolesException {

        Mockito.when(jobRoleServiceMock.viewRoles()).thenThrow(JobRolesNotFoundException.class);

        Response response = jobRoleController.viewRoles();

        Assertions.assertEquals(500, response.getStatus());
    }

    @Test
    void viewRoles_ShouldReturnResponse500_WhenServiceThrowsFailedToGetJobRoles()
            throws JobRolesNotFoundException, FailedToGetJobRolesException {

        Mockito.when(jobRoleServiceMock.viewRoles()).thenThrow(FailedToGetJobRolesException.class);

        Response response = jobRoleController.viewRoles();

        Assertions.assertEquals(500, response.getStatus());
    }

    @Test
    void viewRolesWithFilter_ShouldReturnResponse200_WhenJobRolesRetrieved()
            throws JobRolesNotFoundException, FailedToGetJobRolesException {
        List<JobRoleRequest> expectedRoles = new ArrayList<>();

        Mockito.when(jobRoleServiceMock.viewRolesWithFilter(filter)).thenReturn(expectedRoles);

        Response response = jobRoleController.viewRolesWithFilter(filter);

        Assertions.assertEquals(200, response.getStatus());
    }
    @Test
    void viewRolesWithFilter_ShouldReturnResponse500_WhenServiceThrowsFailedToGetJobRoles()
            throws JobRolesNotFoundException, FailedToGetJobRolesException {

        Mockito.when(jobRoleServiceMock.viewRolesWithFilter(filter)).thenThrow(FailedToGetJobRolesException.class);

        Response response = jobRoleController.viewRolesWithFilter(filter);

        Assertions.assertEquals(500, response.getStatus());
    }

}