package org.kainos.ea.api;

import org.junit.jupiter.api.Test;
import org.kainos.ea.cli.JobCapability;
import org.kainos.ea.cli.JobFamily;
import org.kainos.ea.client.FailedToGetJobCapabilityException;
import org.kainos.ea.client.JobCapabilityNotFoundException;
import org.kainos.ea.client.JobFamilyNotFoundException;
import org.kainos.ea.db.JobCapabilityDao;
import org.kainos.ea.db.JobFamilyDao;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;

public class JobCapabilityServiceTest {
    JobCapabilityDao jobCapabilityDaoMock = Mockito.mock(JobCapabilityDao.class);

    JobCapabilityService jobCapabilityService = new JobCapabilityService(jobCapabilityDaoMock);

    @Test
    void getAllCapabilities_shouldReturnCapabilities_whenDaoReturnsCapabilities() throws SQLException,
            JobCapabilityNotFoundException, FailedToGetJobCapabilityException {
        List<JobCapability> expectedResult = new ArrayList<>();
        expectedResult.add(new JobCapability(1,"name"));

        Mockito.when(jobCapabilityDaoMock.getAllCapabilities()).thenReturn(expectedResult);

        List<JobCapability> result = jobCapabilityService.getAllCapabilities();

        assertIterableEquals(expectedResult, result);
    }

    @Test
    void getAllCapabilities_shouldThrowCapabilityNotFoundException_whenDaoReturnsNull() throws SQLException {
        List<JobCapability> emptyList = new ArrayList<>();

        Mockito.when(jobCapabilityDaoMock.getAllCapabilities()).thenReturn(emptyList);

        assertThrows(JobCapabilityNotFoundException.class, () -> jobCapabilityService.getAllCapabilities());
    }

    @Test
    void getAllCapabilities_shouldThrowFailedToGetCapabilityException_whenDaoReturnsSQLException() throws SQLException {
        Mockito.when(jobCapabilityDaoMock.getAllCapabilities()).thenThrow(SQLException.class);

        assertThrows(FailedToGetJobCapabilityException.class, () -> jobCapabilityService.getAllCapabilities());
    }

    @Test
    void getCapabilityById_shouldReturnCapability_whenDaoReturnsCapability() throws SQLException,
            JobCapabilityNotFoundException, FailedToGetJobCapabilityException {
        int capabilityID = 1;
        JobCapability expectedResult = new JobCapability(1,"name");

        Mockito.when(jobCapabilityDaoMock.getCapabilityById(anyInt())).thenReturn(expectedResult);

        JobCapability result = jobCapabilityService.getCapabilityById(capabilityID);

        assertEquals(expectedResult, result);
    }

    @Test
    void getCapabilityById_shouldThrowCapabilityNotFoundException_whenDaoReturnsNull() throws SQLException {
        int capabilityID = -1;

        Mockito.when(jobCapabilityDaoMock.getCapabilityById(anyInt())).thenReturn(null);

        assertThrows(JobCapabilityNotFoundException.class, () -> jobCapabilityService.getCapabilityById(capabilityID));
    }

    @Test
    void getCapabilityById_shouldThrowFailedToGetCapabilityException_whenDaoReturnsSQLException() throws SQLException {
        int capabilityID = -1;

        Mockito.when(jobCapabilityDaoMock.getCapabilityById(capabilityID)).thenThrow(SQLException.class);

        assertThrows(FailedToGetJobCapabilityException.class, () -> jobCapabilityService.getCapabilityById(capabilityID));
    }
}
