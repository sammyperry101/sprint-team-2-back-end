package org.kainos.ea.resources;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.api.JobCapabilityService;
import org.kainos.ea.cli.CapabilityRequest;
import org.kainos.ea.cli.JobCapability;
import org.kainos.ea.cli.JobFamily;
import org.kainos.ea.client.*;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
public class JobCapabilityControllerTest {
    JobCapabilityService jobCapabilityServiceMock = Mockito.mock(JobCapabilityService.class);

    JobCapabilityController jobCapabilityController = new JobCapabilityController(jobCapabilityServiceMock);

    @Test
    void getAllCapabilities_shouldReturnResponse200_whenServerReturnsResults() throws
            JobCapabilityNotFoundException, FailedToGetJobCapabilityException {
        List<JobCapability> expectedCapabilites = new ArrayList<>();

        Mockito.when(jobCapabilityServiceMock.getAllCapabilities()).thenReturn(expectedCapabilites);

        Response response = jobCapabilityController.getAllCapabilities();

        assertEquals(200, response.getStatus());
    }

    @Test
    void getAllCapabilities_shouldReturnResponse400_whenServerThrowsCapabilityNotFoundException() throws
            JobCapabilityNotFoundException, FailedToGetJobCapabilityException {
        Mockito.when(jobCapabilityServiceMock.getAllCapabilities()).thenThrow(JobCapabilityNotFoundException.class);

        Response response = jobCapabilityController.getAllCapabilities();

        assertEquals(400, response.getStatus());
    }

    @Test
    void getAllCapabilities_shouldReturnResponse500_whenServerThrowsFailedToGetCapabilityException() throws
            JobCapabilityNotFoundException, FailedToGetJobCapabilityException {
        Mockito.when(jobCapabilityServiceMock.getAllCapabilities()).thenThrow(FailedToGetJobCapabilityException.class);

        Response response = jobCapabilityController.getAllCapabilities();

        assertEquals(500, response.getStatus());
    }

    @Test
    void getCapabilityByID_shouldReturnResponse200_whenServerReturnsCapability() throws
            JobCapabilityNotFoundException, FailedToGetJobCapabilityException {
        int capabilityID = 1;
        JobCapability expectedCapability = new JobCapability(1, "name");

        Mockito.when(jobCapabilityServiceMock.getCapabilityById(anyInt())).thenReturn(expectedCapability);

        Response response = jobCapabilityController.getCapabilityById(capabilityID);

        assertEquals(200, response.getStatus());
    }

    @Test
    void getCapabilityById_shouldReturnResponse400_whenServerThrowsCapabilityNotFoundException() throws
            JobCapabilityNotFoundException, FailedToGetJobCapabilityException {
        int capabilityID = -1;
        Mockito.when(jobCapabilityServiceMock.getCapabilityById(anyInt())).thenThrow(JobCapabilityNotFoundException.class);

        Response response = jobCapabilityController.getCapabilityById(capabilityID);

        assertEquals(400, response.getStatus());
    }

    @Test
    void getCapabilityById_shouldReturnResponse500_whenServerThrowsFailedToGetCapabilityException() throws
            JobCapabilityNotFoundException, FailedToGetJobCapabilityException {
        int capabilityID = -1;
        Mockito.when(jobCapabilityServiceMock.getCapabilityById(anyInt())).thenThrow(FailedToGetJobCapabilityException.class);

        Response response = jobCapabilityController.getCapabilityById(capabilityID);

        assertEquals(500, response.getStatus());
    }

    @Test
    void addCapability_shouldReturnResponse200_whenCapabilityAdded() throws
            JobCapabilityNotAddedException, FailedToAddJobCapabilityException {
        CapabilityRequest capabilityRequest = new CapabilityRequest("test");
        int expectedResult = 1;

        Mockito.when(jobCapabilityServiceMock.addCapability(capabilityRequest)).thenReturn(expectedResult);

        try (Response response = jobCapabilityController.addCapability(capabilityRequest)) {

            assertEquals(200, response.getStatus());
        }
    }

    @Test
    void addCapability_shouldReturnResponse400_whenServerThrowsJobCapabilityNotAddedException() throws
            JobCapabilityNotAddedException, FailedToAddJobCapabilityException {
        CapabilityRequest capabilityRequest = new CapabilityRequest("invalid");

        Mockito.when(jobCapabilityServiceMock.addCapability(capabilityRequest))
                .thenThrow(JobCapabilityNotAddedException.class);

        try (Response response = jobCapabilityController.addCapability(capabilityRequest)) {

            assertEquals(400, response.getStatus());
        }
    }

    @Test
    void addCapability_shouldReturnResponse500_whenServerThrowsFailedToAddJobCapabilityException() throws
            JobCapabilityNotAddedException, FailedToAddJobCapabilityException {
        CapabilityRequest capabilityRequest = new CapabilityRequest("invalid");

        Mockito.when(jobCapabilityServiceMock.addCapability(capabilityRequest))
                .thenThrow(FailedToAddJobCapabilityException.class);

        try (Response response = jobCapabilityController.addCapability(capabilityRequest)) {

            assertEquals(500, response.getStatus());
        }
    }
}