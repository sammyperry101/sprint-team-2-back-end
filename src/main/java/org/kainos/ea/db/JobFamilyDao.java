package org.kainos.ea.db;

import org.kainos.ea.cli.JobFamily;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JobFamilyDao {
    private DatabaseConnector databaseConnector;

    public JobFamilyDao(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    public List<JobFamily> getFamilyByCapability(int capabilityID) throws SQLException {
        List<JobFamily> jobFamilies = new ArrayList<>();

        Connection c = databaseConnector.getConnection();

        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT FamilyID, Name, CapabilityID FROM Families "
                + "WHERE CapabilityID = " + capabilityID);

        while (rs.next()) {
            JobFamily jobFamily = new JobFamily(
                    rs.getInt("FamilyID"),
                    rs.getInt("CapabilityID"),
                    rs.getString("Name")
            );

            jobFamilies.add(jobFamily);
        }

        return jobFamilies;
    }
}