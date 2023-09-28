package org.kainos.ea.db;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.cli.JobBand;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class JobBandDaoTest {
    private DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);
    private Connection connnection = Mockito.mock(Connection.class);
    private Statement statement = Mockito.mock(Statement.class);
    private ResultSet resultSet = Mockito.mock(ResultSet.class);
    private PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);

    private JobBandDao jobBandDao = new JobBandDao(databaseConnector);

    @Test
    void getAllBands_shouldReturnBands_whenDatabaseReturnsBands() throws SQLException {
        JobBand expectedBand = new JobBand(1, "name");

        DatabaseConnector.setConn(connnection);

        Mockito.when(connnection.createStatement()).thenReturn(statement);

        Mockito.when(statement.executeQuery(anyString())).thenReturn(resultSet);

        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getInt("BandID")).thenReturn(1);
        Mockito.when(resultSet.getString("Name")).thenReturn("name");

        List<JobBand> jobBands = jobBandDao.getAllBands();

        assertEquals(expectedBand.getBandID(), jobBands.get(0).getBandID());
        assertEquals(expectedBand.getBandName(), jobBands.get(0).getBandName());
    }

    @Test
    void getAllBands_shouldReturnEmptyList_whenDatabaseReturnsNull() throws SQLException {
        DatabaseConnector.setConn(connnection);

        Mockito.when(connnection.createStatement()).thenReturn(statement);

        Mockito.when(statement.executeQuery(anyString())).thenReturn(resultSet);

        Mockito.when(resultSet.next()).thenReturn(false);

        List<JobBand> jobBands = jobBandDao.getAllBands();

        assertTrue(jobBands.isEmpty());
    }

    @Test
    void getAllBands_shouldThrowSQLException_whenDatabaseThrowsSQLException() throws SQLException {
        DatabaseConnector.setConn(connnection);

        Mockito.when(connnection.createStatement()).thenReturn(statement);

        Mockito.when(statement.executeQuery(anyString())).thenThrow(SQLException.class);

        assertThrows(SQLException.class, () -> jobBandDao.getAllBands());
    }
}
