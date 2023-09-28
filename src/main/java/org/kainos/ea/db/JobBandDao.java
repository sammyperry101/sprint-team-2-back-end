package org.kainos.ea.db;

import org.kainos.ea.cli.JobBand;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JobBandDao {

    private DatabaseConnector databaseConnector;

    public JobBandDao(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    public List<JobBand> getAllBands() throws SQLException {
        List<JobBand> jobBands = new ArrayList<>();

        Connection c = databaseConnector.getConnection();

        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT BandID, Name FROM Bands");

        while (rs.next()) {
            JobBand jobBand = new JobBand(
                    rs.getInt("BandID"),
                    rs.getString("Name")
            );

            jobBands.add(jobBand);
        }

        return jobBands;
    }
}
