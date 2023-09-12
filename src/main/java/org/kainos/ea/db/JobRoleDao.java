package org.kainos.ea.db;

import org.kainos.ea.cli.JobRole;

import java.sql.*;

public class JobRoleDao {
    // TODO: Ensure works when DatabaseConnector added
    private DatabaseConnector databaseConnector;

    public JobRoleDao(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    // TODO: Ensure delete string is correct for database and connection works when added
    public void deleteRole(int id) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String deleteStatement = "DELETE FROM Job_Role WHERE RoleID = ?";

        PreparedStatement st = c.prepareStatement(deleteStatement);

        st.setInt(1, id);

        st.executeUpdate();

    }

    public JobRole getRoleById(int id) throws SQLException {
        Connection c = databaseConnector.getConnection();

        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT RoleID, Name, Job_Spec, Responsibilities, Sharepoint_Link, " +
                "CapabilityID, BandID, FamilyID FROM Job_Role WHERE RoleID = " + id);

        while (rs.next()) {
            return new JobRole(
                    rs.getInt("RoleID"),
                    rs.getString("Name"),
                    rs.getString("Job_Spec"),
                    rs.getString("Responsibilities"),
                    rs.getString("Sharepoint_Link"),
                    rs.getInt("CapabilityID"),
                    rs.getInt("BandID"),
                    rs.getInt("FamilyID")
            );
        }

        return null;
    }

}
