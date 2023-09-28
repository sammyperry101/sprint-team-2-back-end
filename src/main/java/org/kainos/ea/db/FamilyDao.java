package org.kainos.ea.db;

import org.kainos.ea.cli.Family;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FamilyDao {
    public List<Family> getFamilies() throws SQLException {
        String selectStatement = "SELECT * FROM `Families`;";

        Connection conn = DatabaseConnector.getConnection();

        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(selectStatement);

        List<Family> familyList = new ArrayList<>();

        while (rs.next()){
            familyList.add(new Family(
                    rs.getInt("FamilyID"),
                    rs.getString("Name"),
                    rs.getInt("CapabilityID")
            ));
        }

        return familyList;
    }
    public Family getFamilyById(int id) throws SQLException {
        String selectStatement = "SELECT * FROM `Families` WHERE FamilyID = ?";

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
