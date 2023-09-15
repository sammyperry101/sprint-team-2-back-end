package org.kainos.ea.db;

import org.junit.jupiter.api.Test;
import org.kainos.ea.client.JobSpec;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

public class JobSpecDAOTest {
    Connection conn = Mockito.mock(Connection.class);
    PreparedStatement st = Mockito.mock(PreparedStatement.class);
    ResultSet rs = Mockito.mock(ResultSet.class);
    JobSpecDAO jobSpecDAO = new JobSpecDAO();
    @Test
    void getJobRoleById_shouldReturnJobSpec_whenValidRoleId() throws SQLException {
        JobSpec expectedResult = new JobSpec(
                "temp",
                "https://kainossoftwareltd.sharepoint.com"
        );

        DatabaseConnector.setConn(conn);
        Mockito.when(conn.prepareStatement(anyString())).thenReturn(st);
        Mockito.when(st.executeQuery()).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(true);
        Mockito.when(rs.getString("Job_Spec")).thenReturn("temp");
        Mockito.when(rs.getString("Sharepoint_Link")).thenReturn("https://kainossoftwareltd.sharepoint.com");

        JobSpec result = jobSpecDAO.getJobSpecByRoleId(3);

        assertEquals(expectedResult.getJobSpec(), result.getJobSpec());
        assertEquals(expectedResult.getSharepointLink(), result.getSharepointLink());
    }

    @Test
    void getJobRoleById_shouldReturnNull_whenJobRoleDoesNotExist() throws SQLException{
        DatabaseConnector.setConn(conn);
        Mockito.when(conn.prepareStatement(anyString())).thenReturn(st);
        Mockito.when(st.executeQuery()).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(false);

        JobSpec result = jobSpecDAO.getJobSpecByRoleId(-1);

        assertNull(result);
    }

    @Test
    void getJobRoleById_shouldThrowSQLException_databaseThrowsSQLException() throws SQLException{
        DatabaseConnector.setConn(conn);
        Mockito.when(conn.prepareStatement(anyString())).thenThrow(SQLException.class);


        assertThrows(SQLException.class, () -> jobSpecDAO.getJobSpecByRoleId(3));
    }
}
