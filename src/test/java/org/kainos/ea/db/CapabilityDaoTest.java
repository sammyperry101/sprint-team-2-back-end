package org.kainos.ea.db;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.cli.CapabilityFamilyJoinRequest;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class CapabilityDaoTest {
    private DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);
    private Connection connection = Mockito.mock(Connection.class);
    private PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
    private Statement statement = Mockito.mock(Statement.class);
    private ResultSet resultSet = Mockito.mock(ResultSet.class);
    private CapabilityDao capabilityDao = new CapabilityDao(databaseConnector);

    @Test
    public void getCapabilitiesWithFamilyID_ShouldReturnCapabilitiesWithFamilyID() throws SQLException {
        CapabilityFamilyJoinRequest expectedCabaility = new CapabilityFamilyJoinRequest(
                1,
                1,
                "testname"
        );

        List<CapabilityFamilyJoinRequest> expectedCapabilities = new ArrayList<>();

        expectedCapabilities.add(expectedCabaility);

        DatabaseConnector.setConn(connection);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(anyString())).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getInt("FamilyID")).thenReturn(1);
        Mockito.when(resultSet.getInt("CapabilityID")).thenReturn(1);
        Mockito.when(resultSet.getString("Name")).thenReturn("testname");

        List<CapabilityFamilyJoinRequest> actualCapabilities = capabilityDao.getCapabilitiesWithFamilyID();

        assertEquals(expectedCapabilities.get(0).getFamilyID(), actualCapabilities.get(0).getFamilyID());
        assertEquals(expectedCapabilities.get(0).getCapabilityID(), actualCapabilities.get(0).getCapabilityID());
        assertEquals(expectedCapabilities.get(0).getName(), actualCapabilities.get(0).getName());
    }

    @Test
    public void getCapabilitiesWithFamilyID_ShouldThrowSQLException() throws SQLException {

        DatabaseConnector.setConn(connection);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(anyString())).thenThrow(SQLException.class);

        assertThrows(SQLException.class, () -> capabilityDao.getCapabilitiesWithFamilyID());
    }

}
