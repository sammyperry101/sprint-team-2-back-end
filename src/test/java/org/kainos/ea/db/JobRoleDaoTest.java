package org.kainos.ea.db;

import io.dropwizard.testing.junit5.DAOTestExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.client.FailedToDeleteJobRoleException;
import org.kainos.ea.client.JobRoleDoesNotExistException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class JobRoleDaoTest {
    private DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);

    private Connection connection = Mockito.mock(Connection.class);

    private PreparedStatement statement = Mockito.mock(PreparedStatement.class);

    @InjectMocks
    private JobRoleDao jobRoleDao = new JobRoleDao(databaseConnector);

    @Test
    void deleteJobRole_shouldReturn1IfJobExists_whenDaoDeletesJob() throws SQLException {
        int id = 1;
        int expectedResult = 1;
        String preparedStatement = "DELETE FROM Job_Role WHERE RoleID = ?";

        Mockito.when(databaseConnector.getConnection()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(preparedStatement)).thenReturn(statement);
        Mockito.when(statement.executeUpdate()).thenReturn(expectedResult);

        int result = jobRoleDao.deleteRole(id);

        assertEquals(expectedResult, result);
    }

    @Test
    void deleteJobRole_shouldReturn0IfJobNotExist_whenDatabaseDeletesNothing() throws SQLException {
        int id = -1;
        int expectedResult = 0;
        String preparedStatement = "DELETE FROM Job_Role WHERE RoleID = ?";

        Mockito.when(databaseConnector.getConnection()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(preparedStatement)).thenReturn(statement);
        Mockito.when(statement.executeUpdate()).thenReturn(expectedResult);

        int result = jobRoleDao.deleteRole(id);

        assertEquals(expectedResult, result);
    }

    @Test
    void deleteJobRole_shouldThrowSQLException_whenDaoReturnsNull() throws SQLException {
        int id = -1;
        String preparedStatement = "DELETE FROM Job_Role WHERE RoleID = ?";

        Mockito.when(databaseConnector.getConnection()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(preparedStatement)).thenReturn(statement);
        Mockito.when(statement.executeUpdate()).thenThrow(SQLException.class);

        assertThrows(SQLException.class, () -> jobRoleDao.deleteRole(id));
    }
}
