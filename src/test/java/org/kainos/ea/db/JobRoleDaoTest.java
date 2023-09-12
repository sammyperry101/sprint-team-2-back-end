package org.kainos.ea.db;

import org.junit.jupiter.api.Test;
import org.kainos.ea.client.FailedToDeleteJobRoleException;
import org.kainos.ea.client.JobRoleDoesNotExistException;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JobRoleDaoTest {
    JobRoleDao jobRoleDaoMock = Mockito.mock(JobRoleDao.class);

    JobRoleDao jobRoleDao = new JobRoleDao(new DatabaseConnector());

    @Test
    void deleteJobRole_shouldReturn1IfJobExists_whenDaoDeletesJob() throws SQLException {
        int id = 1;
        int expectedResult = 1;

        Mockito.when(jobRoleDaoMock.deleteRole(id)).thenReturn(expectedResult);

        int result = jobRoleDao.deleteRole(id);

        assertEquals(result, expectedResult);
    }

    @Test
    void deleteJobRole_shouldReturn0IfJobNotExist_whenDaoReturnsNull() throws SQLException {
        int id = -1;
        int expectedResult = 0;
        Mockito.when(jobRoleDaoMock.deleteRole(id)).thenReturn(expectedResult);

        int result = jobRoleDao.deleteRole(id);

        assertEquals(expectedResult, result);
    }
}
