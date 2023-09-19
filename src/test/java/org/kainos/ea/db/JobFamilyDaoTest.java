package org.kainos.ea.db;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.cli.JobFamily;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class JobFamilyDaoTest {
    private DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);
    private Connection connnection = Mockito.mock(Connection.class);
    private Statement statement = Mockito.mock(Statement.class);
    private ResultSet resultSet = Mockito.mock(ResultSet.class);

    private JobFamilyDao jobFamilyDao = new JobFamilyDao(databaseConnector);

    @Test
    void getFamilyByCapability_shouldReturnFamilies_whenDatabaseReturnsFamilies() throws SQLException {
        JobFamily expectedFamily = new JobFamily(1,1,"name");
        int capabilityID = 1;

        DatabaseConnector.setConn(connnection);

        Mockito.when(connnection.createStatement()).thenReturn(statement);

        Mockito.when(statement.executeQuery(anyString())).thenReturn(resultSet);

        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getInt("FamilyID")).thenReturn(1);
        Mockito.when(resultSet.getInt("CapabilityID")).thenReturn(1);
        Mockito.when(resultSet.getString("Name")).thenReturn("name");

        List<JobFamily> jobFamilies = jobFamilyDao.getFamilyByCapability(capabilityID);

        assertEquals(expectedFamily.getFamilyID(), jobFamilies.get(0).getFamilyID());
        assertEquals(expectedFamily.getCapabilityID(), jobFamilies.get(0).getCapabilityID());
        assertEquals(expectedFamily.getName(), jobFamilies.get(0).getName());
    }

    @Test
    void getFamilyByCapability_shouldReturnEmptyList_whenDatabaseReturnsNull() throws SQLException {
        int capabilityID = -1;

        DatabaseConnector.setConn(connnection);

        Mockito.when(connnection.createStatement()).thenReturn(statement);

        Mockito.when(statement.executeQuery(anyString())).thenReturn(resultSet);

        Mockito.when(resultSet.next()).thenReturn(false);

        List<JobFamily> jobFamilies = jobFamilyDao.getFamilyByCapability(capabilityID);

        assertTrue(jobFamilies.isEmpty());
    }

    @Test
    void getFamilyByCapability_shouldThrowSQLException_whenDatabaseThrowsSQLException() throws SQLException {
        int capabilityID = -1;

        DatabaseConnector.setConn(connnection);

        Mockito.when(connnection.createStatement()).thenReturn(statement);

        Mockito.when(statement.executeQuery(anyString())).thenThrow(SQLException.class);

        assertThrows(SQLException.class, () -> jobFamilyDao.getFamilyByCapability(capabilityID));
    }
}
