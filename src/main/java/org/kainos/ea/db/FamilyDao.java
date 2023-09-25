package org.kainos.ea.db;

import org.kainos.ea.cli.Band;
import org.kainos.ea.cli.Family;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FamilyDao {
    public Family getFamilyById(int id) throws SQLException {
        String selectStatement = "SELECT * FROM `Families` WHERE BandID = ?";

        Connection conn = DatabaseConnector.getConnection();

        PreparedStatement statement = conn.prepareStatement(selectStatement);
        statement.setInt(1, id);

        ResultSet rs = statement.executeQuery();

        if (rs.next()){
            return new Family(
                    rs.getInt("FamilyID"),
                    rs.getString("Name"),
                    rs.getInt("CapabilityID")
            );
        }

        return null;
    }
}
