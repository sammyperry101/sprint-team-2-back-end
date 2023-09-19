package org.kainos.ea.db;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.cli.Family;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class FamilyDaoTest {
    private DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);
    private Connection connection = Mockito.mock(Connection.class);
    private Statement statement = Mockito.mock(Statement.class);
    private ResultSet resultSet = Mockito.mock(ResultSet.class);
    private FamilyDao familyDao = new FamilyDao(databaseConnector);
    @Test
    public void getFamilyByID_ShouldReturnFamily_WhenDatabaseRetrievalSuccessful() throws SQLException {
        int testID = 1;

        Family expectedFamily = new Family(
                1,
                "testfamily",
                1
        );

        DatabaseConnector.setConn(connection);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(anyString())).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getInt("FamilyID")).thenReturn(1);
        Mockito.when(resultSet.getString("Name")).thenReturn("testfamily");
        Mockito.when(resultSet.getInt("CapabilityID")).thenReturn(1);

        Family actualFamily = familyDao.getFamilyByID(testID);

        assertEquals(expectedFamily.getFamilyID(), actualFamily.getFamilyID());
        assertEquals(expectedFamily.getName(), actualFamily.getName());
        assertEquals(expectedFamily.getCapabilityId(), actualFamily.getCapabilityId());
    }

    @Test
    public void getFamilyByID_ShouldThrowSQLException_WhenDatabaseRetrievalFails() throws SQLException {
        int testID = 1;

        String selectStatement = "SELECT FamilyID, Name, CapabilityID FROM Families WHERE FamilyID = " + testID;

        DatabaseConnector.setConn(connection);

        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(anyString())).thenThrow(SQLException.class);

        assertThrows(SQLException.class, () -> familyDao.getFamilyByID(testID));
    }
}
