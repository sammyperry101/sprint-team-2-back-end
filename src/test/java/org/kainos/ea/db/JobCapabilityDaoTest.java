package org.kainos.ea.db;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.cli.JobCapability;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class JobCapabilityDaoTest {
    private DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);
    private Connection connnection = Mockito.mock(Connection.class);
    private Statement statement = Mockito.mock(Statement.class);
    private ResultSet resultSet = Mockito.mock(ResultSet.class);

    private JobCapabilityDao jobCapabilityDao = new JobCapabilityDao(databaseConnector);

    @Test
    void getAllCapabilities_shouldReturnCapabilities_whenDatabaseReturnsCapabilities() throws SQLException {
        JobCapability expectedCapability = new JobCapability(1, "name");

        DatabaseConnector.setConn(connnection);

        Mockito.when(connnection.createStatement()).thenReturn(statement);

        Mockito.when(statement.executeQuery(anyString())).thenReturn(resultSet);

        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getInt("CapabilityID")).thenReturn(1);
        Mockito.when(resultSet.getString("Name")).thenReturn("name");

        List<JobCapability> jobCapabilities = jobCapabilityDao.getAllCapabilities();

        assertEquals(expectedCapability.getCapabilityID(), jobCapabilities.get(0).getCapabilityID());
        assertEquals(expectedCapability.getName(), jobCapabilities.get(0).getName());
    }

    @Test
    void getAllCapabilities_shouldReturnEmptyList_whenDatabaseReturnsNull() throws SQLException {
        DatabaseConnector.setConn(connnection);

        Mockito.when(connnection.createStatement()).thenReturn(statement);

        Mockito.when(statement.executeQuery(anyString())).thenReturn(resultSet);

        Mockito.when(resultSet.next()).thenReturn(false);

        List<JobCapability> jobCapabilities = jobCapabilityDao.getAllCapabilities();

        assertTrue(jobCapabilities.isEmpty());
    }

    @Test
    void getAllCapabilities_shouldThrowSQLException_whenDatabaseThrowsSQLException() throws SQLException {
        DatabaseConnector.setConn(connnection);

        Mockito.when(connnection.createStatement()).thenReturn(statement);

        Mockito.when(statement.executeQuery(anyString())).thenThrow(SQLException.class);

        assertThrows(SQLException.class, () -> jobCapabilityDao.getAllCapabilities());
    }

    @Test
    void getCapabilityById_shouldReturnCapabilities_whenDatabaseReturnsCapabilities() throws SQLException {
        JobCapability expectedCapability = new JobCapability(1, "name");
        int capabilityID = 1;

        DatabaseConnector.setConn(connnection);

        Mockito.when(connnection.createStatement()).thenReturn(statement);

        Mockito.when(statement.executeQuery(anyString())).thenReturn(resultSet);

        Mockito.when(resultSet.next()).thenReturn(true);
        Mockito.when(resultSet.getInt("CapabilityID")).thenReturn(1);
        Mockito.when(resultSet.getString("Name")).thenReturn("name");

        JobCapability jobCapability = jobCapabilityDao.getCapabilityById(capabilityID);

        assertEquals(expectedCapability.getCapabilityID(), jobCapability.getCapabilityID());
        assertEquals(expectedCapability.getName(), jobCapability.getName());
    }

    @Test
    void getCapabilityById_shouldReturnNull_whenDatabaseReturnsNull() throws SQLException {
        int capabilityID = -1;

        DatabaseConnector.setConn(connnection);

        Mockito.when(connnection.createStatement()).thenReturn(statement);

        Mockito.when(statement.executeQuery(anyString())).thenReturn(resultSet);

        Mockito.when(resultSet.next()).thenReturn(false);

        JobCapability jobCapability = jobCapabilityDao.getCapabilityById(capabilityID);

        assertNull(jobCapability);
    }

    @Test
    void getCapabilityById_shouldThrowSQLException_whenDatabaseThrowsSQLException() throws SQLException {
        int capabilityID = -1;

        DatabaseConnector.setConn(connnection);

        Mockito.when(connnection.createStatement()).thenReturn(statement);

        Mockito.when(statement.executeQuery(anyString())).thenThrow(SQLException.class);

        assertThrows(SQLException.class, () -> jobCapabilityDao.getCapabilityById(capabilityID));
    }
}
