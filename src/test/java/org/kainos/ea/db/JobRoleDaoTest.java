package org.kainos.ea.db;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.cli.JobRoleFilter;
import org.kainos.ea.cli.JobRoleRequest;
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
    private JobRoleFilter filter = Mockito.mock(JobRoleFilter.class);
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
                "job spec",
                "responsibilities",
                "sharepoint link",
                "bandname",
                "capability"
                );

        DatabaseConnector.setConn(connection);

        Mockito.when(connection.createStatement()).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeQuery(anyString())).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(true);

        Mockito.when(resultSet.getInt("RoleID")).thenReturn(1);
        Mockito.when(resultSet.getString("Name")).thenReturn("Name");
        Mockito.when(resultSet.getString("Responsibilities")).thenReturn("responsibilities");
        Mockito.when(resultSet.getString("Sharepoint_Link")).thenReturn("sharepoint link");
        Mockito.when(resultSet.getString("bandName")).thenReturn("bandname");
        Mockito.when(resultSet.getString("capabilityName")).thenReturn("capability");

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
                "testname",
                "testname",
                "testname");

        DatabaseConnector.setConn(connection);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(anyString())).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getInt("RoleID")).thenReturn(1);
        Mockito.when(resultSet.getString("Name")).thenReturn("testname");
        Mockito.when(resultSet.getString("Sharepoint_Link")).thenReturn("testname");
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
        JobRoleRequest jobRoleRequest = new JobRoleRequest(1,"NewName", "NewSpec",
                "NewResponsibilities", "NewLink", "bandName", "capabilityName");

        DatabaseConnector.setConn(connection);

        PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
        Mockito.when(connection.prepareStatement(Mockito.anyString())).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1); // Simulate a successful update (1 row affected).

        jobRoleDao.editJobRole(id, jobRoleRequest);

        String statement = "UPDATE Job_Roles AS j" +
                " INNER JOIN Bands AS b ON j.BandID = b.BandID" +
                " INNER JOIN Families AS f ON j.FamilyID = f.FamilyID" +
                " INNER JOIN Capabilities AS c ON f.capabilityID = c.CapabilityID" +
                " SET j.Name = ?, j.Job_Spec = ?, j.Responsibilities = ?, j.Sharepoint_Link = ?, b.Name = ?, c.Name = ?" +
                " WHERE j.RoleID = ?;";

        // Verify that the PreparedStatement was created with the correct SQL statement and parameters

        Mockito.verify(preparedStatement).setString(1, jobRoleRequest.getRoleName());
        Mockito.verify(preparedStatement).setString(2, jobRoleRequest.getJobSpec());
        Mockito.verify(preparedStatement).setString(3, jobRoleRequest.getResponsibilities());
        Mockito.verify(preparedStatement).setString(4, jobRoleRequest.getSharepointLink());
        Mockito.verify(preparedStatement).setString(5, jobRoleRequest.getBandName());
        Mockito.verify(preparedStatement).setString(6, jobRoleRequest.getCapabilityName());
        Mockito.verify(preparedStatement).setInt(7, id);

        // Verify that executeUpdate() was called
        Mockito.verify(preparedStatement).executeUpdate();
    }

    @Test
    void editRole_ShouldThrowSQLException_WhenSQLExceptionOccurs() throws SQLException {
        int id = 5;
        JobRoleRequest jobRoleRequest = new JobRoleRequest(1, "string", "string", "string", "string", "bandName", "capabilityName");

        DatabaseConnector.setConn(connection);

        PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
        Mockito.when(connection.prepareStatement(Mockito.anyString())).thenReturn(preparedStatement);
        Mockito.doThrow(new SQLException()).when(preparedStatement).executeUpdate();

        assertThrows(SQLException.class, () -> jobRoleDao.editJobRole(id, jobRoleRequest));
    }
    @Test
    void getJobRolesWithFilter_ShouldReturnJobRoles_WhenDatabaseReturnsRoles() throws SQLException{
        JobRoleRequest expectedRole = new JobRoleRequest(1,
                "testname",
                "testlink",
                "testband",
                "testlink",
                "Principal",
                "Workday");

        DatabaseConnector.setConn(connection);

        Mockito.when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);

        Mockito.when(filter.getRoleNameFilter()).thenReturn("testname");
        Mockito.when(filter.getBandID()).thenReturn(1);
        Mockito.when(filter.getCapabilityID()).thenReturn(1);

        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getInt("RoleID")).thenReturn(1);
        Mockito.when(resultSet.getString("Name")).thenReturn("testname");
        Mockito.when(resultSet.getString("Sharepoint_Link")).thenReturn("testlink");
        Mockito.when(resultSet.getString("bandName")).thenReturn("Principal");
        Mockito.when(resultSet.getString("capabilityName")).thenReturn("Workday");

        List<JobRoleRequest> actualRoles = jobRoleDao.getJobRolesWithFilter(filter);

        List<JobRoleRequest> expectedRoles = new ArrayList<>();
        expectedRoles.add(expectedRole);

        assertEquals(expectedRoles.get(0).getRoleID(), actualRoles.get(0).getRoleID());
        assertEquals(expectedRoles.get(0).getRoleName(), actualRoles.get(0).getRoleName());
        assertEquals(expectedRoles.get(0).getSharepointLink(), actualRoles.get(0).getSharepointLink());
        assertEquals(expectedRoles.get(0).getBandName(), actualRoles.get(0).getBandName());
        assertEquals(expectedRoles.get(0).getCapabilityName(), actualRoles.get(0).getCapabilityName());
    }

    @Test
    void getJobRolesWithFilter_ShouldThrowSQLException_WhenSQLExceptionOccurs() throws SQLException {
        DatabaseConnector.setConn(connection);

        Mockito.when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);

        Mockito.when(filter.getRoleNameFilter()).thenReturn("testname");
        Mockito.when(filter.getBandID()).thenReturn(1);
        Mockito.when(filter.getCapabilityID()).thenReturn(1);

        Mockito.when(preparedStatement.executeQuery()).thenThrow(SQLException.class);

        assertThrows(SQLException.class, () -> jobRoleDao.getJobRolesWithFilter(filter));
    }
}
