package org.kainos.ea.db;

import org.kainos.ea.cli.Capability;
import org.kainos.ea.cli.CapabilityFamilyJoinRequest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class CapabilityDao {

    DatabaseConnector databaseConnector = new DatabaseConnector();

    public CapabilityDao(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    public List<CapabilityFamilyJoinRequest> getCapabilitiesWithFamilyID() throws SQLException {
        List<CapabilityFamilyJoinRequest> list = new ArrayList<>();

        Connection c = databaseConnector.getConnection();

        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT Families.FamilyID, Capabilities.CapabilityID, Capabilities.Name" +
                " FROM Families INNER JOIN Capabilities ON Families.CapabilityID = Capabilities.CapabilityID");

        while(rs.next()){
            CapabilityFamilyJoinRequest capabilityFamilyJoinRequest = new CapabilityFamilyJoinRequest(
                    rs.getInt("FamilyID"),
                    rs.getInt("CapabilityID"),
                    rs.getString("Name")
            );

            list.add(capabilityFamilyJoinRequest);
        }

        return list;
    }
}
