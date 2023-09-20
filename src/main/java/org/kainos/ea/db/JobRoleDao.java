package org.kainos.ea.db;

import org.kainos.ea.cli.JobRoleRequest;

import java.sql.Connection;
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

    public List<JobRoleRequest> getJobRoles() throws SQLException{
        List<JobRoleRequest> roles = new ArrayList<>();

        Connection c = databaseConnector.getConnection();

        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT j.RoleId, j.Name, j.Sharepoint_Link, b.Name, c.Name FROM Job_Roles AS j INNER JOIN" +
                " Bands AS b ON j.BandID=b.BandID" +
                " INNER JOIN Families AS f ON j.FamilyID=f.FamilyID" +
                " INNER JOIN Capabilities AS c ON f.capabilityID=c.CapabilityID;");

        while (rs.next()) {
            JobRoleRequest role = new JobRoleRequest(
                    rs.getInt("Job_Roles.RoleID"),
                    rs.getString("Job_Roles.Name"),
                    rs.getString("Job_Roles.Sharepoint_Link"),
                    rs.getString("Bands.Name"),
                    rs.getString("Capabilities.Name")
            );

            roles.add(role);
        }

        return roles;
    }
}
