package org.kainos.ea.db;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.cli.JobRole;
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
        String query = "DELETE FROM Job_Roles WHERE RoleID = ?";

        DatabaseConnector.setConn(connection);

        Mockito.when(connection.prepareStatement(query)).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeUpdate()).thenThrow(SQLException.class);

        assertThrows(SQLException.class, () -> jobRoleDao.deleteRole(id));
    }

    @Test
    void getJobRoleById_shouldReturnId_whenDatabaseReturnsJobRole() throws SQLException {
        int id = 1;
        String query = "SELECT RoleID, Name, Job_Spec, Responsibilities, Sharepoint_Link, " +
                "BandID, FamilyID FROM Job_Roles WHERE RoleID = " + id;
        JobRole expectedResult = new JobRole(1,
                "Name",
                "Job Spec",
                "responsibilities",
                "sharepoint link",
                1,
                1);

        DatabaseConnector.setConn(connection);

        Mockito.when(connection.createStatement()).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeQuery(query)).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(true);

        Mockito.when(resultSet.getInt("RoleID")).thenReturn(1);
        Mockito.when(resultSet.getString("Name")).thenReturn("Name");
        Mockito.when(resultSet.getString("Job_Spec")).thenReturn("Job Spec");
        Mockito.when(resultSet.getString("Responsibilities")).thenReturn("responsibilities");
        Mockito.when(resultSet.getString("Sharepoint_Link")).thenReturn("sharepoint link");
        Mockito.when(resultSet.getInt("BandID")).thenReturn(1);
        Mockito.when(resultSet.getInt("FamilyID")).thenReturn(1);

        JobRole result = jobRoleDao.getRoleById(id);

        assertEquals(expectedResult.getRoleID(), result.getRoleID());
        assertEquals(expectedResult.getName(), result.getName());
        assertEquals(expectedResult.getJobSpec(), result.getJobSpec());
        assertEquals(expectedResult.getResponsibilities(), result.getResponsibilities());
        assertEquals(expectedResult.getSharepointLink(), result.getSharepointLink());
        assertEquals(expectedResult.getBandID(), result.getBandID());
        assertEquals(expectedResult.getFamilyID(), result.getFamilyID());
    }

    @Test
    void getJobRoleById_shouldReturnNull_whenDatabaseReturnsNothing() throws SQLException {
        int id = 1;
        String query = "SELECT RoleID, Name, Job_Spec, Responsibilities, Sharepoint_Link, " +
                "BandID, FamilyID FROM Job_Roles WHERE RoleID = " + id;

        DatabaseConnector.setConn(connection);

        Mockito.when(connection.createStatement()).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeQuery(query)).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(false);

        JobRole result = jobRoleDao.getRoleById(id);

        assertNull(result);
    }

    @Test
    void getJobRoleById_shouldThrowSQLException_whenDatabaseThrowsSQLException() throws SQLException {
        int id = 1;
        String query = "SELECT RoleID, Name, Job_Spec, Responsibilities, Sharepoint_Link, " +
                "BandID, FamilyID FROM Job_Roles WHERE RoleID = " + id;

        DatabaseConnector.setConn(connection);

        Mockito.when(connection.createStatement()).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeQuery(query)).thenThrow(SQLException.class);

        assertThrows(SQLException.class, () -> jobRoleDao.getRoleById(id));
    }

    @Test
    void getJobRoles_ShouldReturnRoles_WhenDatabaseReturnsRoles() throws SQLException {
        int id = 1;
        String query = "SELECT RoleID, Name, Job_Spec, Responsibilities, Sharepoint_Link, " +
                "BandID, FamilyID FROM Job_Roles WHERE RoleID = " + id;

        JobRole expectedRole = new JobRole(1,
                "Name",
                "Job Spec",
                "responsibilities",
                "sharepoint link",
                1,
                1);

        DatabaseConnector.setConn(connection);

        Mockito.when(connection.createStatement()).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeQuery(query)).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(true);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(anyString())).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getInt("RoleID")).thenReturn(1);
        Mockito.when(resultSet.getString("Name")).thenReturn("Name");
        Mockito.when(resultSet.getString("Job_Spec")).thenReturn("Job Spec");
        Mockito.when(resultSet.getString("Responsibilities")).thenReturn("responsibilities");
        Mockito.when(resultSet.getString("Sharepoint_Link")).thenReturn("sharepoint link");
        Mockito.when(resultSet.getInt("BandID")).thenReturn(1);
        Mockito.when(resultSet.getInt("FamilyID")).thenReturn(1);

        List<JobRole> actualRoles = jobRoleDao.getJobRoles();

        List<JobRole> expectedRoles = new ArrayList<>();
        expectedRoles.add(expectedRole);

        assertEquals(expectedRoles.get(0).getRoleID(), actualRoles.get(0).getRoleID());
        assertEquals(expectedRoles.get(0).getName(), actualRoles.get(0).getName());
        assertEquals(expectedRoles.get(0).getJobSpec(), actualRoles.get(0).getJobSpec());
        assertEquals(expectedRoles.get(0).getResponsibilities(), actualRoles.get(0).getResponsibilities());
        assertEquals(expectedRoles.get(0).getSharepointLink(), actualRoles.get(0).getSharepointLink());
        assertEquals(expectedRoles.get(0).getBandID(), actualRoles.get(0).getBandID());
        assertEquals(expectedRoles.get(0).getFamilyID(), actualRoles.get(0).getFamilyID());
    }

    @Test
    void getJobRoles_ShouldThrowSQLException_WhenSQLExceptionOccurs() throws SQLException {

        String selectStatement = "SELECT RoleID, Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID FROM Job_Role";

        DatabaseConnector.setConn(connection);

        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(anyString())).thenThrow(SQLException.class);

        assertThrows(SQLException.class, () -> jobRoleDao.getJobRoles());
    }
}
