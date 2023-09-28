package org.kainos.ea.api;

import org.junit.jupiter.api.Test;
import org.kainos.ea.cli.JobBand;
import org.kainos.ea.cli.JobCapability;
import org.kainos.ea.client.FailedToGetJobBandException;
import org.kainos.ea.client.FailedToGetJobCapabilityException;
import org.kainos.ea.client.JobBandNotFoundException;
import org.kainos.ea.client.JobCapabilityNotFoundException;
import org.kainos.ea.db.JobBandDao;
import org.kainos.ea.db.JobCapabilityDao;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JobBandServiceTest {
    JobBandDao jobBandDaoMock = Mockito.mock(JobBandDao.class);

    JobBandService jobBandService = new JobBandService(jobBandDaoMock);

    @Test
    void getAllBands_shouldReturnBands_whenDaoReturnsBands() throws SQLException,
            JobBandNotFoundException, FailedToGetJobBandException {
        List<JobBand> expectedResult = new ArrayList<>();
        expectedResult.add(new JobBand(1,"name"));

        Mockito.when(jobBandDaoMock.getAllBands()).thenReturn(expectedResult);

        List<JobBand> result = jobBandService.getAllBands();

        assertIterableEquals(expectedResult, result);
    }

    @Test
    void getAllBands_shouldThrowBandNotFoundException_whenDaoReturnsNull() throws SQLException {
        List<JobBand> emptyList = new ArrayList<>();

        Mockito.when(jobBandDaoMock.getAllBands()).thenReturn(emptyList);

        assertThrows(JobBandNotFoundException.class, () -> jobBandService.getAllBands());
    }

    @Test
    void getAllBands_shouldThrowFailedToGetBandException_whenDaoReturnsSQLException() throws SQLException {
        Mockito.when(jobBandDaoMock.getAllBands()).thenThrow(SQLException.class);

        assertThrows(FailedToGetJobBandException.class, () -> jobBandService.getAllBands());
    }
}
