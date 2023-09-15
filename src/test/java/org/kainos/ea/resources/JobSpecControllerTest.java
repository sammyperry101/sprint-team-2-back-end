package org.kainos.ea.resources;

import org.junit.jupiter.api.Test;
import org.kainos.ea.api.JobSpecService;
import org.kainos.ea.client.FailedToGetJobSpecException;
import org.kainos.ea.client.JobRoleDoesNotExistException;
import org.kainos.ea.client.JobSpec;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;

public class JobSpecControllerTest {
    JobSpecService jobSpecService = Mockito.mock(JobSpecService.class);
    JobSpecController jobSpecController = new JobSpecController(jobSpecService);

    @Test
    void getJobSpecByRoleId_shouldReturn200_whenServiceReturnsJobSpec()
            throws JobRoleDoesNotExistException, FailedToGetJobSpecException {
        int roleId = 3;
        JobSpec expectedJobSpec = new JobSpec("temp",
                "https://kainossoftwareltd.sharepoint.com/");

        Mockito.when(jobSpecService.getJobSpecByRoleId(roleId)).thenReturn(expectedJobSpec);

        Response res = jobSpecController.getJobSpecByRoleId(roleId);

        assertEquals(200, res.getStatus());
        assertTrue(res.hasEntity());
    }

    @Test
    void getJobSpecByRoleId_shouldReturn500_whenServiceThrowsFailedToGetJobSpecException()
            throws JobRoleDoesNotExistException, FailedToGetJobSpecException {
        int roleId = 3;

        Mockito.when(jobSpecService.getJobSpecByRoleId(roleId)).thenThrow(FailedToGetJobSpecException.class);

        Response res = jobSpecController.getJobSpecByRoleId(roleId);

        assertEquals(500, res.getStatus());
        assertFalse(res.hasEntity());
    }

    @Test
    void getJobSpecByRoleId_shouldReturn400_whenServiceThrowsJobRoleDoesNotExistException()
            throws JobRoleDoesNotExistException, FailedToGetJobSpecException {
        int roleId = 3;
        Mockito.when(jobSpecService.getJobSpecByRoleId(roleId)).thenThrow(JobRoleDoesNotExistException.class);

        Response res = jobSpecController.getJobSpecByRoleId(roleId);

        assertEquals(400, res.getStatus());
        assertFalse(res.hasEntity());
    }
}
