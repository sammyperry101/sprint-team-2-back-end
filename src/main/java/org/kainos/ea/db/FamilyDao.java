package org.kainos.ea.db;

import org.kainos.ea.cli.Family;

import java.sql.*;
import java.util.List;

public class FamilyDao {

    private DatabaseConnector databaseConnector;

    public FamilyDao(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    public Family getFamilyByID(int id) throws SQLException {
        Connection c = databaseConnector.getConnection();

        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT FamilyID, Name, CapabilityID FROM Families WHERE FamilyID = " + id);

        while(rs.next()){
            return new Family(
                    rs.getInt("FamilyID"),
                    rs.getString("Name"),
                    rs.getInt("CapabilityID")
            );
        }

        return null;
    }
}
