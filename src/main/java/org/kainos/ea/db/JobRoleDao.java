package org.kainos.ea.db;

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

    public List<JobRole> getJobRoles() throws SQLException{
        List<JobRole> roles = new ArrayList<>();

        Connection c = databaseConnector.getConnection();

        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT RoleID, Name, Job_Spec, Responsibilities, Sharepoint_Link, " +
                "BandID, FamilyID FROM Job_Roles");

        while (rs.next()) {
            JobRole role = new JobRole(
                    rs.getInt("RoleID"),
                    rs.getString("Name"),
                    rs.getString("Job_Spec"),
                    rs.getString("Responsibilities"),
                    rs.getString("Sharepoint_Link"),
                    rs.getInt("BandID"),
                    rs.getInt("FamilyID")
            );

            roles.add(role);
        }

        return roles;
    }
}
