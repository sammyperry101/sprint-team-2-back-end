package org.kainos.ea.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FamilyDao {
    private DatabaseConnector databaseConnector;

    public FamilyDao(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    public int checkFamilyIDExists(int familyID) throws SQLException {
        Connection c = databaseConnector.getConnection();

        int checkedBandId = -1;

        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT FamilyID FROM Families WHERE FamilyID = " + familyID);

        if (rs.next()) {
            checkedBandId = rs.getInt("FamilyID");
        }
        return checkedBandId;
    }
}
