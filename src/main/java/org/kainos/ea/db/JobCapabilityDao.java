package org.kainos.ea.db;

import org.kainos.ea.cli.JobCapability;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JobCapabilityDao {
    private DatabaseConnector databaseConnector;

    public JobCapabilityDao(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    public JobCapability getCapabilityById(int id) throws SQLException {
        Connection c = databaseConnector.getConnection();

        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT CapabilityID, Name FROM Capabilities "
                + "WHERE CapabilityID = " + id);

        if (rs.next()) {
            JobCapability jobCapability = new JobCapability(
                    rs.getInt("CapabilityID"),
                    rs.getString("Name")
            );

            return jobCapability;
        }

        return null;
    }

    public List<JobCapability> getAllCapabilities() throws SQLException {
        List<JobCapability> jobCapabilities = new ArrayList<>();

        Connection c = databaseConnector.getConnection();

        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT CapabilityID, Name FROM Capabilities");

        while (rs.next()) {
            JobCapability jobCapability = new JobCapability(
                    rs.getInt("CapabilityID"),
                    rs.getString("Name")
            );

            jobCapabilities.add(jobCapability);
        }

        return jobCapabilities;
    }
}
