package org.kainos.ea.db;

import org.kainos.ea.cli.JobRoleFilter;
import org.kainos.ea.cli.JobRoleRequest;

import java.sql.*;
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

    public List<JobRoleRequest> getJobRolesWithFilter(JobRoleFilter filter) throws SQLException{
        List<JobRoleRequest> roles = new ArrayList<>();

        Connection c = databaseConnector.getConnection();

        String filterStatement = "SELECT j.RoleId, j.Name, j.Sharepoint_Link, b.Name as bandName, c.Name as capabilityName" +
                " FROM Job_Roles AS j INNER JOIN" +
                " Bands AS b ON j.BandID=b.BandID" +
                " INNER JOIN Families AS f ON j.FamilyID=f.FamilyID" +
                " INNER JOIN Capabilities AS c ON f.capabilityID=c.CapabilityID" +
                " WHERE UPPER(j.Name) LIKE ?" +
                " AND UPPER(b.Name) LIKE ?" +
                " AND UPPER(c.Name) LIKE ?;";

        PreparedStatement st = c.prepareStatement(filterStatement);

        st.setString(1, "%" + filter.getRoleNameFilter().toUpperCase() + "%");
        st.setString(2, "%" + filter.getBandNameFilter().toUpperCase() + "%");
        st.setString(3, "%" + filter.getCapabilityNameFilter().toUpperCase() + "%");

        ResultSet rs = st.executeQuery();

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
