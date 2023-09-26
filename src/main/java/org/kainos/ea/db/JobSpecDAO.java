package org.kainos.ea.db;

import org.kainos.ea.model.JobSpec;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JobSpecDAO {
    public JobSpec getJobSpecByRoleId(int roleId) throws SQLException {
        Connection connection = DatabaseConnector.getConnection();

        String selectQuery = "SELECT `RoleID`, `Job_Spec`, `Sharepoint_Link`, `Name` FROM Job_Roles WHERE `RoleID` = ?;";

        assert connection != null;
        PreparedStatement st = connection.prepareStatement(selectQuery);
        st.setInt(1, roleId);

        ResultSet rs = st.executeQuery();

        if(rs.next()){
            return new JobSpec(
                    rs.getString("Job_Spec"),
                    rs.getString("Sharepoint_Link"),
                    rs.getInt("RoleID"),
                    rs.getString("Name")
            );
        }

        return null;
    }
}
