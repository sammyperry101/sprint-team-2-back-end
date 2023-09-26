package org.kainos.ea.db;

import org.kainos.ea.cli.JobRole;
import org.kainos.ea.cli.JobRoleEditRequest;

import org.kainos.ea.cli.JobRoleRequest;

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

    public int editJobRole(int id, JobRoleEditRequest jobRoleEditRequest) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String editStatement = "UPDATE Job_Roles SET Name = ?, Job_Spec = ?, Responsibilities = ?, Sharepoint_Link = ?, " +
                "BandID = ?, FamilyID = ? WHERE RoleID = ?";

        PreparedStatement st = c.prepareStatement(editStatement);

        st.setString(1, jobRoleEditRequest.getName());
        st.setString(2, jobRoleEditRequest.getJob_Spec());
        st.setString(3, jobRoleEditRequest.getResponsibilities());
        st.setString(4, jobRoleEditRequest.getSharepointLink());
        st.setInt(5, jobRoleEditRequest.getBandId());
        st.setInt(6, jobRoleEditRequest.getFamilyId());
        st.setInt(7, id);

        st.executeUpdate();

        return id;
    }

    public JobRole getJobRoleById(int id) throws SQLException {
        Connection c =databaseConnector.getConnection();

        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT RoleID, Name, Job_Spec, Responsibilities, Sharepoint_Link, " +
                "BandID, FamilyID FROM Job_Roles WHERE RoleID =" + id);

        while(rs.next()) {
            return new JobRole(
                    rs.getInt("RoleID"),
                    rs.getString("Name"),
                    rs.getString("Job_Spec"),
                    rs.getString("Responsibilities"),
                    rs.getString("Sharepoint_Link"),
                    rs.getInt("BandID"),
                    rs.getInt("FamilyID")
            );
        }
        return null;
    }
}
