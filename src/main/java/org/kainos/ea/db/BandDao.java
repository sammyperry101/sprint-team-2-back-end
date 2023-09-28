package org.kainos.ea.db;

import org.kainos.ea.cli.Band;
import org.kainos.ea.cli.Family;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BandDao {
    public List<Band> getBands() throws SQLException {
        String selectStatement = "SELECT * FROM `Bands`;";

        Connection conn = DatabaseConnector.getConnection();

        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(selectStatement);

        List<Band> bandList = new ArrayList<>();

        while (rs.next()){
            bandList.add(new Band(
                    rs.getInt("BandID"),
                    rs.getString("Name"),
                    rs.getInt("Level"),
                    rs.getString("Training_Available"),
                    rs.getString("Competencies")
            ));
        }

        return bandList;
    }

    public Band getBandById(int id) throws SQLException {
        String selectStatement = "SELECT * FROM `Bands` WHERE BandID = ?";

        Connection conn = DatabaseConnector.getConnection();

        PreparedStatement statement = conn.prepareStatement(selectStatement);
        statement.setInt(1, id);

        ResultSet rs = statement.executeQuery();

        if (rs.next()){
            return new Band(
                    rs.getInt("BandId"),
                    rs.getString("Name"),
                    rs.getInt("Level"),
                    rs.getString("Training_Available"),
                    rs.getString("Competencies")
            );
        }

        return null;
    }
}
