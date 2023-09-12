package org.kainos.ea.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.client.FailedToDeleteJobRoleException;
import org.kainos.ea.client.JobRoleDoesNotExistException;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.JobRoleDao;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class JobRoleServiceTest {

    JobRoleDao jobRoleDaoMock = Mockito.mock(JobRoleDao.class);

    JobRoleService jobRoleService = new JobRoleService(new JobRoleDao(new DatabaseConnector()));

    @Test
    void deleteJobRole_shouldReturn1_whenDaoDeletesJobRole() throws JobRoleDoesNotExistException,
            FailedToDeleteJobRoleException, SQLException {
        int id = 1;
        int expectedResult = 1;

        Mockito.when(jobRoleDaoMock.deleteRole(id)).thenReturn(expectedResult);

        int result = jobRoleService.deleteRole(id);

        assertEquals(result, expectedResult);
    }

    @Test
    void deleteJobRole_shouldThrowJobDoesNotExistException_whenDaoReturnsNull() throws SQLException {
        int id = -1;
        int expectedResult = 0;
        Mockito.when(jobRoleDaoMock.deleteRole(id)).thenReturn(expectedResult);

        assertThrows(JobRoleDoesNotExistException.class,
                () -> jobRoleService.deleteRole(id));
    }
}
