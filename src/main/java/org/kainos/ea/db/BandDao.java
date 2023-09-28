package org.kainos.ea.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BandDao {
    private DatabaseConnector databaseConnector;

    public BandDao(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    public int checkBandIDExists(String bandName) throws SQLException {
        Connection c = databaseConnector.getConnection();

        int checkedBandId = -1;

        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT BandID FROM Bands WHERE Name = " + bandName);

        if (rs.next()) {
            checkedBandId = rs.getInt("BandID");
        }
        return checkedBandId;
    }
}
