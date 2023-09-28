package org.kainos.ea.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CapabilityDao {
    private DatabaseConnector databaseConnector;

    public CapabilityDao(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    public int checkCapabilityNameExists(String CapabilityName) throws SQLException {
        Connection c = databaseConnector.getConnection();

        int checkedCapabilityId = -1;

        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT CapabilityID FROM Capabilities WHERE Name = " + CapabilityName);

        if (rs.next()) {
            checkedCapabilityId = rs.getInt("CapabilityID");
        }
        return checkedCapabilityId;
    }
}
