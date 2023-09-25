package org.kainos.ea.resources;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.api.JobFamilyService;
import org.kainos.ea.cli.JobFamily;
import org.kainos.ea.client.FailedToGetJobFamilyException;
import org.kainos.ea.client.JobFamilyNotFoundException;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class JobFamilyControllerTest {
    JobFamilyService jobFamilyServiceMock = Mockito.mock(JobFamilyService.class);

    JobFamilyController jobFamilyController = new JobFamilyController(jobFamilyServiceMock);

    @Test
    void getFamilyByCapability_shouldReturnResponse200_whenServerReturnsResults() throws
            FailedToGetJobFamilyException, JobFamilyNotFoundException {
        List<JobFamily> expectedFamilies = new ArrayList<>();
        int capabilityID  = 1;

        Mockito.when(jobFamilyServiceMock.getFamilyByCapability(capabilityID)).thenReturn(expectedFamilies);

        Response response = jobFamilyController.getFamilyByCapability(capabilityID);

        assertEquals(200, response.getStatus());
    }

    @Test
    void getFamilyByCapability_shouldReturnResponse400_whenServerThrowsJobFamilyNotFoundException() throws
            FailedToGetJobFamilyException, JobFamilyNotFoundException {
        int capabilityID  = -1;

        Mockito.when(jobFamilyServiceMock.getFamilyByCapability(capabilityID))
                .thenThrow(JobFamilyNotFoundException.class);

        Response response = jobFamilyController.getFamilyByCapability(capabilityID);

        assertEquals(400, response.getStatus());
    }

    @Test
    void getFamilyByCapability_shouldReturnResponse500_whenServerThrowsFailedToGetJobFamilyException() throws
            FailedToGetJobFamilyException, JobFamilyNotFoundException {
        int capabilityID = -1;

        Mockito.when(jobFamilyServiceMock.getFamilyByCapability(capabilityID))
                .thenThrow(FailedToGetJobFamilyException.class);

        Response response = jobFamilyController.getFamilyByCapability(capabilityID);

        assertEquals(500, response.getStatus());
    }
}
