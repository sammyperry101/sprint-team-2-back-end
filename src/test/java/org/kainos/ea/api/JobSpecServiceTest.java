package org.kainos.ea.api;

import org.junit.jupiter.api.Test;
import org.kainos.ea.client.FailedToGetJobSpecException;
import org.kainos.ea.client.JobRoleDoesNotExistException;
import org.kainos.ea.client.JobSpec;
import org.kainos.ea.db.JobSpecDAO;
import org.mockito.Mockito;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JobSpecServiceTest {
    JobSpecDAO jobSpecDAO = Mockito.mock(JobSpecDAO.class);
    JobSpecService jobSpecService = new JobSpecService(jobSpecDAO);
    int roleId = 3;

    @Test
    void getJobSpecByRoleId_shouldReturnJobSpec_whenDaoReturnsJobSpec() throws SQLException, JobRoleDoesNotExistException, FailedToGetJobSpecException {
        JobSpec expectedResult = new JobSpec("Temp",
                "https://kainossoftwareltd.sharepoint.com/");

        Mockito.when(jobSpecDAO.getJobSpecByRoleId(roleId)).thenReturn(expectedResult);
        JobSpec result = jobSpecService.getJobSpecByRoleId(roleId);

        assertEquals(expectedResult, result);
    }

    @Test
    void getJobSpecByRoleId_shouldThrowFailedToGetJobException_whenDaoThrowsSQLException() throws SQLException {
        Mockito.when(jobSpecDAO.getJobSpecByRoleId(roleId)).thenThrow(SQLException.class);

        assertThrows(FailedToGetJobSpecException.class, () -> jobSpecService.getJobSpecByRoleId(roleId));
    }

    @Test
    void getJobSpecByRoleId_shouldThrowJobRoleDoesNotExistException_whenDaoReturnsNull() throws SQLException{
        Mockito.when(jobSpecDAO.getJobSpecByRoleId(0)).thenReturn(null);

        assertThrows(JobRoleDoesNotExistException.class, () -> jobSpecService.getJobSpecByRoleId(0));
    }
}
