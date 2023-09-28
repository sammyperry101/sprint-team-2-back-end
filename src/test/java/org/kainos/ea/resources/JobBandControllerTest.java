package org.kainos.ea.resources;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.api.JobBandService;
import org.kainos.ea.cli.JobBand;
import org.kainos.ea.client.FailedToGetJobBandException;
import org.kainos.ea.client.JobBandNotFoundException;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class JobBandControllerTest {
    JobBandService jobBandServiceMock = Mockito.mock(JobBandService.class);

    JobBandController jobBandController = new JobBandController(jobBandServiceMock);

    @Test
    void getAllBands_shouldReturnResponse200_whenServerReturnsResults() throws
            JobBandNotFoundException, FailedToGetJobBandException {
        List<JobBand> expectedCapabilites = new ArrayList<>();

        Mockito.when(jobBandServiceMock.getAllBands()).thenReturn(expectedCapabilites);

        Response response = jobBandController.getAllBands();

        assertEquals(200, response.getStatus());
    }

    @Test
    void getAllBands_shouldReturnResponse400_whenServerThrowsBandNotFoundException() throws
            JobBandNotFoundException, FailedToGetJobBandException {
        Mockito.when(jobBandServiceMock.getAllBands()).thenThrow(JobBandNotFoundException.class);

        Response response = jobBandController.getAllBands();

        assertEquals(400, response.getStatus());
    }

    @Test
    void getAllBands_shouldReturnResponse500_whenServerThrowsFailedToGetBandException() throws
            JobBandNotFoundException, FailedToGetJobBandException {
        Mockito.when(jobBandServiceMock.getAllBands()).thenThrow(FailedToGetJobBandException.class);

        Response response = jobBandController.getAllBands();

        assertEquals(500, response.getStatus());
    }
}
