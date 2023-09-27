package org.kainos.ea.db;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.model.JobSpec;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class JobSpecDAOTest {
    Connection conn = Mockito.mock(Connection.class);
    PreparedStatement st = Mockito.mock(PreparedStatement.class);
    ResultSet rs = Mockito.mock(ResultSet.class);
    JobSpecDAO jobSpecDAO = new JobSpecDAO();
    @Test
    void getJobSpecById_shouldReturnJobSpec_whenValidRoleId() throws SQLException {
        JobSpec expectedResult = new JobSpec(
                "temp",
                "https://kainossoftwareltd.sharepoint.com",
                3,
                "temp",
                "responsible"
        );

        DatabaseConnector.setConn(conn);

        Mockito.when(conn.prepareStatement(anyString())).thenReturn(st);
        Mockito.when(st.executeQuery()).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(true);
        Mockito.when(rs.getString("Job_Spec")).thenReturn("temp");
        Mockito.when(rs.getString("Sharepoint_Link")).thenReturn("https://kainossoftwareltd.sharepoint.com");
        Mockito.when(rs.getInt("RoleID")).thenReturn(3);
        Mockito.when(rs.getString("Name")).thenReturn("temp");
        Mockito.when(rs.getString("Responsibilities")).thenReturn("responsible");

        JobSpec result = jobSpecDAO.getJobSpecByRoleId(3);

        assertEquals(expectedResult.getRoleId(), result.getRoleId());
        assertEquals(expectedResult.getJobSpec(), result.getJobSpec());
        assertEquals(expectedResult.getSharepointLink(), result.getSharepointLink());
        assertEquals(expectedResult.getName(), result.getName());
    }

    @Test
    void getJobSpecById_shouldReturnNull_whenJobRoleDoesNotExist() throws SQLException{
        DatabaseConnector.setConn(conn);
        Mockito.when(conn.prepareStatement(anyString())).thenReturn(st);
        Mockito.when(st.executeQuery()).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(false);

        JobSpec result = jobSpecDAO.getJobSpecByRoleId(-1);

        assertNull(result);
    }

    @Test
    void getJobSpecById_shouldThrowSQLException_databaseThrowsSQLException() throws SQLException{
        DatabaseConnector.setConn(conn);
        Mockito.when(conn.prepareStatement(anyString())).thenThrow(SQLException.class);


        assertThrows(SQLException.class, () -> jobSpecDAO.getJobSpecByRoleId(3));
    }
}
