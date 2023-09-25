package org.kainos.ea.db;

import org.kainos.ea.cli.JobRole;
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

    public void editJobRole(int id, JobRoleRequest jobRoleRequest) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String editStatement = "UPDATE Job_Roles SET Name = ?, Job_Spec = ?, Responsibilities = ?, Sharepoint_Link = ?, " +
                "BandID = ?, FamilyID = ? FROM Job_Roles WHERE RoleID = ?";

        PreparedStatement st = c.prepareStatement(editStatement);

        st.setString(1, jobRoleRequest.getName());
        st.setString(2, jobRoleRequest.getJob_Spec());
        st.setString(3, jobRoleRequest.getResponsibilities());
        st.setString(4, jobRoleRequest.getSharepointLink());
        st.setInt(5, jobRoleRequest.getBandId());
        st.setInt(6, jobRoleRequest.getFamilyId());
        st.setInt(7, id);

        st.executeUpdate();
    }

    public JobRole getJobRoleById(int id) throws SQLException {
        Connection c =databaseConnector.getConnection();

        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT JobRoleId, Name, Job_Spec, Responsibilities, Sharepoint_Link, " +
                "BandID, FamilyID FROM Job_Roles Where RoleID =" + id);

        while(rs.next()) {
            return new JobRole(
                    rs.getInt("JobRoleId"),
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
