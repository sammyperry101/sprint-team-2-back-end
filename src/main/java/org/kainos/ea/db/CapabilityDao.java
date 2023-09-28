package org.kainos.ea.db;

import java.sql.*;

public class CapabilityDao {
    private DatabaseConnector databaseConnector;

    public CapabilityDao(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    public int checkCapabilityNameExists(String capabilityName) throws SQLException {
        Connection c = databaseConnector.getConnection();

        int checkedCapabilityId = -1;

        String statement = "SELECT CapabilityID FROM Capabilities WHERE Name = ?;";

        PreparedStatement st = c.prepareStatement(statement);

        st.setString(1, capabilityName);

        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            checkedCapabilityId = rs.getInt("CapabilityID");
        }
        return checkedCapabilityId;
    }
}
