package org.kainos.ea.resources;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.api.JobSpecService;
import org.kainos.ea.client.FailedToGetJobSpecException;
import org.kainos.ea.client.JobSpecDoesNotExist;
import org.kainos.ea.model.JobSpec;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class JobSpecControllerTest {
    JobSpecService jobSpecService = Mockito.mock(JobSpecService.class);
    JobSpecController jobSpecController = new JobSpecController(jobSpecService);

    @Test
    void getJobSpecByRoleId_shouldReturn200_whenServiceReturnsJobSpec()
            throws JobSpecDoesNotExist, FailedToGetJobSpecException {
        int roleId = 3;
        JobSpec expectedJobSpec = new JobSpec("temp",
                "https://kainossoftwareltd.sharepoint.com/",
            roleId);

        Mockito.when(jobSpecService.getJobSpecByRoleId(roleId)).thenReturn(expectedJobSpec);

        Response res = jobSpecController.getJobSpecByRoleId(roleId);

        JobSpec resultJobSpec = (JobSpec) res.getEntity();

        assertEquals(200, res.getStatus());
        assertEquals(expectedJobSpec.getRoleId(), resultJobSpec.getRoleId());
        assertEquals(expectedJobSpec.getJobSpec(), resultJobSpec.getJobSpec());
        assertEquals(expectedJobSpec.getSharepointLink(), resultJobSpec.getSharepointLink());
    }

    @Test
    void getJobSpecByRoleId_shouldReturn500_whenServiceThrowsFailedToGetJobSpecException()
            throws JobSpecDoesNotExist, FailedToGetJobSpecException {
        int roleId = 3;

        Mockito.when(jobSpecService.getJobSpecByRoleId(roleId)).thenThrow(FailedToGetJobSpecException.class);

        Response res = jobSpecController.getJobSpecByRoleId(roleId);

        assertEquals(500, res.getStatus());
        assertFalse(res.hasEntity());
    }

    @Test
    void getJobSpecByRoleId_shouldReturn400_whenServiceThrowsJobRoleDoesNotExistException()
            throws JobSpecDoesNotExist, FailedToGetJobSpecException {
        int roleId = 3;
        Mockito.when(jobSpecService.getJobSpecByRoleId(roleId)).thenThrow(JobSpecDoesNotExist.class);

        Response res = jobSpecController.getJobSpecByRoleId(roleId);

        assertEquals(400, res.getStatus());
        assertFalse(res.hasEntity());
    }
}
