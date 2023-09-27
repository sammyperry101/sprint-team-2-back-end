package org.kainos.ea.resources;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.api.JobRoleService;
import org.kainos.ea.cli.JobRoleEditRequest;
import org.kainos.ea.client.FailedToGetJobRole;
import org.kainos.ea.cli.JobRoleRequest;
import org.kainos.ea.client.JobRoleDoesNotExistException;
import org.kainos.ea.client.FailedToGetJobRolesException;
import org.kainos.ea.client.FailedToDeleteJobRoleException;

import org.kainos.ea.client.JobRolesNotFoundException;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.ws.rs.core.Response;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class JobRoleControllerTest {

    JobRoleService jobRoleServiceMock = Mockito.mock(JobRoleService.class);

    JobRoleController jobRoleController = new JobRoleController(jobRoleServiceMock);

    @Test
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
    }

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
    @Test
    public void getJobRoleById_shouldReturn200Response_whenServiceReturnsJobRole() throws JobRoleDoesNotExistException,
            FailedToGetJobRole {
        int id = 1;
        JobRoleRequest expectedResult = new JobRoleRequest(1,
                "testname",
                "testlink",
                "testname",
                "testname");

        Mockito.when(jobRoleServiceMock.getRoleById(anyInt())).thenReturn(expectedResult);

        Response response = jobRoleController.getRoleById(id);

        assertEquals(200, response.getStatus());
    }

    @Test
    void getJobRoleById_shouldReturn400Response_whenServiceThrowsJobRoleDoesNotExist()
            throws JobRoleDoesNotExistException, FailedToGetJobRole {
        int id = -1;

        Mockito.when(jobRoleServiceMock.getRoleById(id)).thenThrow(JobRoleDoesNotExistException.class);

        Response response = jobRoleController.getRoleById(id);

        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    void getJobRoleById_shouldReturn500Response_whenServiceThrowsFailedToGetJobRole ()
            throws JobRoleDoesNotExistException, FailedToGetJobRole {
        int id = -1;

        Mockito.when(jobRoleServiceMock.getRoleById(id)).thenThrow(FailedToGetJobRole.class);

        Response response = jobRoleController.getRoleById(id);

        Assertions.assertEquals(500, response.getStatus());
    }
}