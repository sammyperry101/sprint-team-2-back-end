package org.kainos.ea.db;

import org.kainos.ea.cli.JobRoleFilter;
import org.kainos.ea.cli.JobRoleRequest;
import org.kainos.ea.cli.JobRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    public List<JobRoleRequest> getJobRolesWithFilter(JobRoleFilter filter) throws SQLException{
        List<JobRoleRequest> roles = new ArrayList<>();

        Connection c = databaseConnector.getConnection();

        StringBuilder filterStatementBuilder = new StringBuilder("SELECT j.RoleId, j.Name, j.Sharepoint_Link, b.Name as bandName, c.Name as capabilityName")
                .append(" FROM Job_Roles AS j")
                .append(" INNER JOIN Bands AS b ON j.BandID=b.BandID")
                .append(" INNER JOIN Families AS f ON j.FamilyID=f.FamilyID")
                .append(" INNER JOIN Capabilities AS c ON f.capabilityID=c.CapabilityID");

        int paramCount = 0;

        if (!filter.getRoleNameFilter().isEmpty()) {
            filterStatementBuilder.append(" WHERE UPPER(j.name) LIKE ?");
            paramCount++;
        }

        if (filter.getBandID() != 0) {
            filterStatementBuilder.append(paramCount == 0 ? " WHERE" : " AND").append(" b.BandID = ?");
            paramCount++;
        }

        if (filter.getCapabilityID() != 0) {
            filterStatementBuilder.append(paramCount == 0 ? " WHERE" : " AND").append(" c.CapabilityID = ?");
            paramCount++;
        }

        String filterStatement = filterStatementBuilder.toString();

        PreparedStatement st = c.prepareStatement(filterStatement);

        if(filter.getCapabilityID() != 0){
            st.setInt(paramCount, filter.getCapabilityID());
            paramCount--;
        }
        if(filter.getBandID() != 0){
            st.setInt(paramCount, filter.getBandID());
            paramCount--;
        }
        if(!filter.getRoleNameFilter().isEmpty()){
            st.setString(paramCount, "%" + filter.getRoleNameFilter().toUpperCase() + "%");
            paramCount--;
        }

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
