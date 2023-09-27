package org.kainos.ea.db;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.cli.JobRole;
import org.kainos.ea.cli.JobRoleRequest;
import org.kainos.ea.cli.JobRoleEditRequest;
import org.kainos.ea.client.JobRoleDoesNotExistException;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class JobRoleDaoTest {
    private DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);
    private Connection connection = Mockito.mock(Connection.class);
    private PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
    private Statement statement = Mockito.mock(Statement.class);
    private ResultSet resultSet = Mockito.mock(ResultSet.class);
    private JobRoleDao jobRoleDao = new JobRoleDao(databaseConnector);

    @Test
    void deleteJobRole_shouldReturn1IfJobExists_whenDaoDeletesJob() throws SQLException {
        int id = 1;
        int expectedResult = 1;
        String query = "DELETE FROM Job_Roles WHERE RoleID = ?";

        DatabaseConnector.setConn(connection);

        Mockito.when(connection.prepareStatement(query)).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(expectedResult);

        int result = jobRoleDao.deleteRole(id);

        assertEquals(expectedResult, result);
    }

    @Test
    void deleteJobRole_shouldReturn0IfJobNotExist_whenDatabaseDeletesNothing() throws SQLException {
        int id = -1;
        int expectedResult = 0;
        String query = "DELETE FROM Job_Roles WHERE RoleID = ?";

        DatabaseConnector.setConn(connection);

        Mockito.when(connection.prepareStatement(query)).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(expectedResult);

        int result = jobRoleDao.deleteRole(id);

        assertEquals(expectedResult, result);
    }

    @Test
    void deleteJobRole_shouldThrowSQLException_whenDatabaseThrowsSQLException() throws SQLException {
        int id = -1;

        DatabaseConnector.setConn(connection);

        Mockito.when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeUpdate()).thenThrow(SQLException.class);

        assertThrows(SQLException.class, () -> jobRoleDao.deleteRole(id));
    }

    @Test
    void getJobRoleById_shouldReturnId_whenDatabaseReturnsJobRole() throws SQLException {
        int id = 1;
        JobRoleRequest expectedResult = new JobRoleRequest(1,
                "Name",
                "sharepoint link",
                "band name",
                "capability name"
                );

        DatabaseConnector.setConn(connection);

        Mockito.when(connection.createStatement()).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeQuery(anyString())).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(true);

        Mockito.when(resultSet.getInt("RoleID")).thenReturn(1);
        Mockito.when(resultSet.getString("Name")).thenReturn("Name");
        Mockito.when(resultSet.getString("Sharepoint_Link")).thenReturn("sharepoint link");
        Mockito.when(resultSet.getString("bandName")).thenReturn("band name");
        Mockito.when(resultSet.getString("capabilityName")).thenReturn("capability name");

        JobRoleRequest result = jobRoleDao.getRoleById(id);

        assertEquals(expectedResult.getRoleID(), result.getRoleID());
        assertEquals(expectedResult.getRoleName(), result.getRoleName());
        assertEquals(expectedResult.getSharepointLink(), result.getSharepointLink());
        assertEquals(expectedResult.getBandName(), result.getBandName());
        assertEquals(expectedResult.getCapabilityName(), result.getCapabilityName());
    }

    @Test
    void getJobRoleById_shouldReturnNull_whenDatabaseReturnsNothing() throws SQLException {
        int id = 1;

        DatabaseConnector.setConn(connection);

        Mockito.when(connection.createStatement()).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeQuery(anyString())).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(false);

        JobRoleRequest result = jobRoleDao.getRoleById(id);

        assertNull(result);
    }

    @Test
    void getJobRoleById_shouldThrowSQLException_whenDatabaseThrowsSQLException() throws SQLException {
        int id = 1;

        DatabaseConnector.setConn(connection);

        Mockito.when(connection.createStatement()).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeQuery(anyString())).thenThrow(SQLException.class);

        assertThrows(SQLException.class, () -> jobRoleDao.getRoleById(id));
    }

    @Test
    void getJobRoles_ShouldReturnRoles_WhenDatabaseReturnsRoles() throws SQLException {
        JobRoleRequest expectedRole = new JobRoleRequest(1,
                "testname",
                "testlink",
                "testname",
                "testname");

        DatabaseConnector.setConn(connection);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(anyString())).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getInt("RoleID")).thenReturn(1);
        Mockito.when(resultSet.getString("Name")).thenReturn("testname");
        Mockito.when(resultSet.getString("Sharepoint_Link")).thenReturn("testlink");
        Mockito.when(resultSet.getString("bandName")).thenReturn("testname");
        Mockito.when(resultSet.getString("capabilityName")).thenReturn("testname");

        List<JobRoleRequest> actualRoles = jobRoleDao.getJobRoles();

        List<JobRoleRequest> expectedRoles = new ArrayList<>();
        expectedRoles.add(expectedRole);

        assertEquals(expectedRoles.get(0).getRoleID(), actualRoles.get(0).getRoleID());
        assertEquals(expectedRoles.get(0).getRoleName(), actualRoles.get(0).getRoleName());
        assertEquals(expectedRoles.get(0).getSharepointLink(), actualRoles.get(0).getSharepointLink());
        assertEquals(expectedRoles.get(0).getBandName(), actualRoles.get(0).getBandName());
        assertEquals(expectedRoles.get(0).getCapabilityName(), actualRoles.get(0).getCapabilityName());
    }

    @Test
    void getJobRoles_ShouldThrowSQLException_WhenSQLExceptionOccurs() throws SQLException {

        String selectStatement = "SELECT j.RoleId, j.Name, j.Sharepoint_Link, b.Name as bandName, c.Name as capabilityName" +
                " FROM Job_Roles AS j INNER JOIN" +
                " Bands AS b ON j.BandID=b.BandID" +
                " INNER JOIN Families AS f ON j.FamilyID=f.FamilyID" +
                " INNER JOIN Capabilities AS c ON f.capabilityID=c.CapabilityID;";

        DatabaseConnector.setConn(connection);

        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(anyString())).thenThrow(SQLException.class);

        assertThrows(SQLException.class, () -> jobRoleDao.getJobRoles());
    }

    @Test
    void editRole_ShouldUpdateJobRole_WhenSuccessful() throws SQLException {
        int id = 5;
        JobRoleEditRequest jobRoleEditRequest = new JobRoleEditRequest("NewName", "NewSpec", "NewResponsibilities", "NewLink", 2, 2);

        DatabaseConnector.setConn(connection);

        PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
        Mockito.when(connection.prepareStatement(Mockito.anyString())).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1); // Simulate a successful update (1 row affected).

        jobRoleDao.editJobRole(id, jobRoleEditRequest);

        // Verify that the PreparedStatement was created with the correct SQL statement and parameters
        Mockito.verify(connection).prepareStatement("UPDATE Job_Roles SET Name = ?, Job_Spec = ?, Responsibilities = ?," +
                " Sharepoint_Link = ?, BandID = ?, FamilyID = ? WHERE RoleID = ?");
        Mockito.verify(preparedStatement).setString(1, "NewName");
        Mockito.verify(preparedStatement).setString(2, "NewSpec");
        Mockito.verify(preparedStatement).setString(3, "NewResponsibilities");
        Mockito.verify(preparedStatement).setString(4, "NewLink");
        Mockito.verify(preparedStatement).setInt(5, 2);
        Mockito.verify(preparedStatement).setInt(6, 2);
        Mockito.verify(preparedStatement).setInt(7, id);

        // Verify that executeUpdate() was called
        Mockito.verify(preparedStatement).executeUpdate();
    }

    @Test
    void editRole_ShouldThrowSQLException_WhenSQLExceptionOccurs() throws SQLException {
        int id = 5;
        JobRoleEditRequest jobRoleEditRequest = new JobRoleEditRequest("string", "string", "string", "string", 1, 1);

        DatabaseConnector.setConn(connection);

        PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
        Mockito.when(connection.prepareStatement(Mockito.anyString())).thenReturn(preparedStatement);
        Mockito.doThrow(new SQLException()).when(preparedStatement).executeUpdate();

        assertThrows(SQLException.class, () -> jobRoleDao.editJobRole(id, jobRoleEditRequest));
    }

    @Test
    void getJobRoleById_ShouldReturnJobRole_WhenIdExists() throws SQLException {
        int id = 5;

        // Mock the PreparedStatement to return the ResultSet
        DatabaseConnector.setConn(connection);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(anyString())).thenReturn(resultSet);

        Mockito.when(resultSet.next()).thenReturn(true); // Simulate that a row exists
        Mockito.when(resultSet.getInt("RoleID")).thenReturn(id);
        Mockito.when(resultSet.getString("Name")).thenReturn("SampleName");
        Mockito.when(resultSet.getString("Job_Spec")).thenReturn("SampleSpec");
        Mockito.when(resultSet.getString("Responsibilities")).thenReturn("SampleResponsibilities");
        Mockito.when(resultSet.getString("Sharepoint_Link")).thenReturn("SampleLink");
        Mockito.when(resultSet.getInt("BandID")).thenReturn(1);
        Mockito.when(resultSet.getInt("FamilyID")).thenReturn(1);

        // Call the getJobRoleById method
        JobRole jobRole = jobRoleDao.getJobRoleById(id);

        // Verify that the returned JobRole matches the expected values
        assertEquals(id, jobRole.getRoleID());
    }

    @Test
    void getJobRoleById_ShouldThrowJobRoleNotFoundException_WhenIdDoesNotExist() throws SQLException {
        int id = 999;
        String query = "SELECT RoleID, Name, Job_Spec, Responsibilities, Sharepoint_Link, " +
                "BandID, FamilyID FROM Job_Roles WHERE RoleID =" + id;

        // Define a mock ResultSet to simulate an empty result (no rows found)
        DatabaseConnector.setConn(connection);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        //Mockito.when(statement.executeQuery(query)).thenThrow(JobRoleDoesNotExistException.class);
        Mockito.doThrow(new JobRoleDoesNotExistException()).when(statement).executeQuery(query);

        // Call the getJobRoleById method and assert that it throws JobRoleDoesNotExistException
        assertThrows(JobRoleDoesNotExistException.class, () -> jobRoleDao.getRoleById(id));
    }

}
