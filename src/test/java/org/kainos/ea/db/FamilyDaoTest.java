package org.kainos.ea.db;

import org.junit.jupiter.api.Test;
import org.kainos.ea.cli.Family;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class FamilyDaoTest
{
    FamilyDao familyDao = new FamilyDao();
    Connection connection = Mockito.mock(Connection.class);
    PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
    ResultSet resultSet = Mockito.mock(ResultSet.class);

    @Test
    void getFamilyById_shouldReturnId_whenValidIdProvided()
            throws SQLException
    {
        String selectStatement = "SELECT * FROM `Families` WHERE FamilyID = ?";

        Family expected = new Family(1, "Name", 3);

        DatabaseConnector.setConn(connection);

        Mockito.when(connection.prepareStatement(selectStatement)).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(true);
        Mockito.when(resultSet.getInt("FamilyID")).thenReturn(1);
        Mockito.when(resultSet.getString("Name")).thenReturn("Name");
        Mockito.when(resultSet.getInt("CapabilityID")).thenReturn(3);

        Family result = familyDao.getFamilyById(1);

        assertEquals(expected.getFamilyId(), result.getFamilyId());
        assertEquals(expected.getName(), result.getName());
        assertEquals(expected.getCapability(), result.getCapability());
    }

    @Test
    void getFamilyById_shouldThrowSQLException_whenDatabaseCantConnect()
            throws SQLException
    {
        String selectStatement = "SELECT * FROM `Families` WHERE FamilyID = ?";

        DatabaseConnector.setConn(connection);

        Mockito.when(connection.prepareStatement(selectStatement)).thenThrow(SQLException.class);

        assertThrows(SQLException.class, () -> familyDao.getFamilyById(1));
    }

    @Test
    void getFamilyById_shouldReturnNull_whenFamilyDoesNotExist()
            throws SQLException
    {
        String selectStatement = "SELECT * FROM `Families` WHERE FamilyID = ?";

        Family expected = new Family(1, "Name", 3);

        DatabaseConnector.setConn(connection);

        Mockito.when(connection.prepareStatement(selectStatement)).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(false);

        Family result = familyDao.getFamilyById(-1);

        assertNull(result);
    }
}
