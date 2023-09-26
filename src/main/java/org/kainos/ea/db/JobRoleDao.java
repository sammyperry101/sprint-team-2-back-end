package org.kainos.ea.db;

import org.kainos.ea.cli.JobRole;
import org.kainos.ea.cli.JobRoleRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobRoleDao {
    private DatabaseConnector databaseConnector;

    public JobRoleDao(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    public List<JobRole> getJobRoles() throws SQLException {
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

    //todo Admin authorisation
    public Integer createJobRole(JobRoleRequest jobRole)
            throws SQLException
    {
        Connection conn = DatabaseConnector.getConnection();

        String insertStatement = "INSERT INTO `Job_Roles` " +
                "(`Name`, `Job_Spec`, `Responsibilities`, `Sharepoint_Link`, `BandID`, `FamilyID`) " +
                "VALUES (?, ?, ?, ?, ?, ?);";

        PreparedStatement preparedStatement = conn.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setString(1, jobRole.getName());
        preparedStatement.setString(2, jobRole.getJobSpec());
        preparedStatement.setString(3, jobRole.getResponsibilities());
        preparedStatement.setString(4, jobRole.getSharepointLink());
        preparedStatement.setInt(5, jobRole.getBandID());
        preparedStatement.setInt(6, jobRole.getFamilyID());

        preparedStatement.executeUpdate();

        ResultSet rs = preparedStatement.getGeneratedKeys();

        if(rs.next()){
            System.out.println(rs.getInt(1));
        }

        return null;
    }
}
