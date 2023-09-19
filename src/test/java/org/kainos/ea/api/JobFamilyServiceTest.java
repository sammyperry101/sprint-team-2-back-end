package org.kainos.ea.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.cli.JobFamily;
import org.kainos.ea.client.FailedToGetJobFamilyException;
import org.kainos.ea.client.JobFamilyNotFoundException;
import org.kainos.ea.db.JobFamilyDao;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
public class JobFamilyServiceTest {
    JobFamilyDao jobFamilyDaoMock = Mockito.mock(JobFamilyDao.class);

    JobFamilyService jobFamilyService = new JobFamilyService(jobFamilyDaoMock);

    @Test
    void getFamilyByCapability_shouldReturnFamily_whenDaoReturnsFamily() throws SQLException,
            FailedToGetJobFamilyException, JobFamilyNotFoundException {
        List<JobFamily> expectedResult = new ArrayList<>();
        expectedResult.add(new JobFamily(1,1,"name"));
        int capabilityID = 1;

        Mockito.when(jobFamilyDaoMock.getFamilyByCapability(anyInt())).thenReturn(expectedResult);

        List<JobFamily> result = jobFamilyService.getFamilyByCapability(capabilityID);

        assertIterableEquals(expectedResult, result);
    }

    @Test
    void getFamilyByCapability_shouldThrowJobFamilyNotFoundException_whenDaoReturnsNull() throws SQLException {
        List<JobFamily> emptyList = new ArrayList<>();
        int capabilityID = -1;

        Mockito.when(jobFamilyDaoMock.getFamilyByCapability(anyInt())).thenReturn(emptyList);

        assertThrows(JobFamilyNotFoundException.class, () -> jobFamilyService.getFamilyByCapability(capabilityID));
    }

    @Test
    void getFamilyByCapability_shouldThrowFailedToGetJobFamilyException_whenDaoReturnsSQLException() throws SQLException {
        int capabilityID = -1;

        Mockito.when(jobFamilyDaoMock.getFamilyByCapability(anyInt())).thenThrow(SQLException.class);

        assertThrows(FailedToGetJobFamilyException.class, () -> jobFamilyService.getFamilyByCapability(capabilityID));
    }
}
