package org.kainos.ea.db;

import org.kainos.ea.cli.JobRoleRequest;
import org.kainos.ea.cli.JobRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JobRoleDao {
    private DatabaseConnector databaseConnector;

    public JobRoleDao(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }
    public int deleteRole(int id) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String deleteStatement = "DELETE FROM Job_Roles WHERE RoleID = ?";

        PreparedStatement st = c.prepareStatement(deleteStatement);

        st.setInt(1, id);

        return st.executeUpdate();
    }

    public JobRoleRequest getRoleById(int id) throws SQLException {
        Connection c = databaseConnector.getConnection();

        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT j.RoleId, j.Name, j.Sharepoint_Link, b.Name as bandName, c.Name as capabilityName" +
                " FROM Job_Roles AS j INNER JOIN" +
                " Bands AS b ON j.BandID=b.BandID" +
                " INNER JOIN Families AS f ON j.FamilyID=f.FamilyID" +
                " INNER JOIN Capabilities AS c ON f.capabilityID=c.CapabilityID " +
                " WHERE j.RoleID = " + id);

        while (rs.next()) {
            return new JobRoleRequest(
                    rs.getInt("RoleID"),
                    rs.getString("Name"),
                    rs.getString("Sharepoint_Link"),
                    rs.getString("bandName"),
                    rs.getString("capabilityName")
            );
        }

        return null;
    }

    public List<JobRoleRequest> getJobRoles() throws SQLException{
        List<JobRoleRequest> roles = new ArrayList<>();

        Connection c = databaseConnector.getConnection();

        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT j.RoleId, j.Name, j.Sharepoint_Link, b.Name as bandName, c.Name as capabilityName" +
                " FROM Job_Roles AS j INNER JOIN" +
                " Bands AS b ON j.BandID=b.BandID" +
                " INNER JOIN Families AS f ON j.FamilyID=f.FamilyID" +
                " INNER JOIN Capabilities AS c ON f.capabilityID=c.CapabilityID;");

        while (rs.next()) {
            JobRoleRequest role = new JobRoleRequest(
                    rs.getInt("RoleID"),
                    rs.getString("Name"),
                    rs.getString("Sharepoint_Link"),
                    rs.getString("bandName"),
                    rs.getString("capabilityName")
            );

            roles.add(role);
        }

        return roles;
    }
}
