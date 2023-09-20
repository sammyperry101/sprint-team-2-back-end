package org.kainos.ea.db;

import org.kainos.ea.cli.Band;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BandDAO {
    public Band getBandById(int id){
        try{
            String selectStatement = "SELECT * FROM `Bands` WHERE BandID = ?";

            Connection conn = DatabaseConnector.getConnection();

            PreparedStatement statement = conn.prepareStatement(selectStatement);
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                return new Band(
                        rs.getInt("BandId"),
                        rs.getString("Name"),
                        rs.getInt("Level"),
                        rs.getString("Training_Available"),
                        rs.getString("Competencies")
                );
            }
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }

        return null;
    }
}
