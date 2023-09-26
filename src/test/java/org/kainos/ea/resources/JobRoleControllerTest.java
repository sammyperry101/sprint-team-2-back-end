package org.kainos.ea.resources;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.api.JobRoleService;
import org.kainos.ea.cli.JobRoleEditRequest;
import org.kainos.ea.client.FailedToGetJobRole;
import org.kainos.ea.cli.JobRoleRequest;
import org.kainos.ea.client.FailedToGetJobRoles;
import org.kainos.ea.client.JobRoleDoesNotExistException;
import org.kainos.ea.client.JobRolesNotFoundException;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class JobRoleControllerTest {

    JobRoleService jobRoleServiceMock = Mockito.mock(JobRoleService.class);

    JobRoleController jobRoleController = new JobRoleController(jobRoleServiceMock);
    @Test
    void viewRoles_ShouldReturnResponse200_WhenJobRolesRetrieved() throws JobRolesNotFoundException, FailedToGetJobRoles {
        List<JobRoleRequest> expectedRoles = new ArrayList<>();

        Mockito.when(jobRoleServiceMock.viewRoles()).thenReturn(expectedRoles);

        Response response = jobRoleController.viewRoles();

        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void viewRoles_ShouldReturnResponse500_WhenServiceThrowsJobRolesNotFound()
            throws JobRolesNotFoundException, FailedToGetJobRoles {

        Mockito.when(jobRoleServiceMock.viewRoles()).thenThrow(JobRolesNotFoundException.class);

        Response response = jobRoleController.viewRoles();

        Assertions.assertEquals(500, response.getStatus());
    }

    @Test
    void viewRoles_ShouldReturnResponse500_WhenServiceThrowsFailedToGetJobRoles()
            throws JobRolesNotFoundException, FailedToGetJobRoles {

        Mockito.when(jobRoleServiceMock.viewRoles()).thenThrow(FailedToGetJobRoles.class);

        Response response = jobRoleController.viewRoles();

        Assertions.assertEquals(500, response.getStatus());
    }

    @Test
    void editRole_ShouldReturnResponse200_WhenJobRoleHasBeenEdited()
            throws JobRoleDoesNotExistException, FailedToGetJobRole{
        int id = 5;
        JobRoleEditRequest jobRole = new JobRoleEditRequest("string", "string", "string", "string", 1, 1);

        Mockito.when(jobRoleServiceMock.editJobRole(id, jobRole)).thenReturn(5);

        Response response = jobRoleController.editJobRole(id, jobRole);

        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void editRole_ShouldReturnResponse500_WhenServiceThrowsFailedToGetJobRole()
            throws JobRoleDoesNotExistException, FailedToGetJobRole {
        int id = 5;
        JobRoleEditRequest jobRole = new JobRoleEditRequest("string", "string", "string", "string", 1, 1);
        Mockito.when(jobRoleServiceMock.editJobRole(id, jobRole)).thenThrow(FailedToGetJobRole.class);

        Response response = jobRoleController.editJobRole(id, jobRole);

        Assertions.assertEquals(500, response.getStatus());
    }

    @Test
    void editRole_ShouldReturnResponse400_WhenServiceThrowsJobRoleDoesNotExist()
            throws JobRoleDoesNotExistException, FailedToGetJobRole {
        int id = 5;
        JobRoleEditRequest jobRole = new JobRoleEditRequest("string", "string", "string", "string", 1, 1);
        Mockito.when(jobRoleServiceMock.editJobRole(id, jobRole)).thenThrow(JobRoleDoesNotExistException.class);

        Response response = jobRoleController.editJobRole(id, jobRole);

        Assertions.assertEquals(400, response.getStatus());
    }
}