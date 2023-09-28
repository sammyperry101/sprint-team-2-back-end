package org.kainos.ea.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BandDao {
    private DatabaseConnector databaseConnector;

    public BandDao(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    public int checkBandNameExists(String bandName) throws SQLException {
        Connection c = databaseConnector.getConnection();

        int checkedBandId = -1;

        String statement = "SELECT BandID FROM Bands WHERE Name = ?;";

        PreparedStatement st = c.prepareStatement(statement);

        st.setString(1, bandName);

        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            checkedBandId = rs.getInt("BandID");
        }
        return checkedBandId;
    }
}
