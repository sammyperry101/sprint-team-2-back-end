package org.kainos.ea.db;

import org.junit.jupiter.api.Test;
import org.kainos.ea.cli.Band;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

public class BandDaoTest {
    BandDao bandDao = new BandDao();
    Connection connection = Mockito.mock(Connection.class);
    PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
    ResultSet resultSet = Mockito.mock(ResultSet.class);

    @Test
    void getBandById_shouldReturnBand_whenBandIdIsValid() throws SQLException {
        DatabaseConnector.setConn(connection);

        Band expectedResult =
                new Band(1, "Name", 1, "Training", "Competencies");

        Mockito.when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(true);
        Mockito.when(resultSet.getInt("BandId")).thenReturn(1);
        Mockito.when(resultSet.getString("Name")).thenReturn("Name");
        Mockito.when(resultSet.getInt("Level")).thenReturn(1);
        Mockito.when(resultSet.getString("Training_Available")).thenReturn("Training");
        Mockito.when(resultSet.getString("Competencies")).thenReturn("Competencies");

        Band actualResult = bandDao.getBandById(1);

        assertEquals(expectedResult.getBandID(), actualResult.getBandID());
        assertEquals(expectedResult.getName(), actualResult.getName());
        assertEquals(expectedResult.getCompetencies(), actualResult.getCompetencies());
        assertEquals(expectedResult.getLevel(), actualResult.getLevel());
        assertEquals(expectedResult.getTraining_available(), actualResult.getTraining_available());
    }

    @Test
    void getBandById_shouldReturnNull_whenBandDoesNotExist() throws SQLException {
        DatabaseConnector.setConn(connection);

        Mockito.when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(false);

        Band actualResult = bandDao.getBandById(1);

        assertNull(actualResult);
    }

    @Test
    void getBandById_shouldThrowSQLException_whenDatabaseCantConnect() throws SQLException {
        DatabaseConnector.setConn(connection);
        Mockito.when(connection.prepareStatement(anyString())).thenThrow(SQLException.class);

        assertThrows(SQLException.class, () -> bandDao.getBandById(1));
    }
}
