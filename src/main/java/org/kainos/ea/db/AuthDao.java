package org.kainos.ea.db;

import org.checkerframework.checker.units.qual.A;
import org.kainos.ea.api.AuthService;
import org.kainos.ea.cli.LoginRequest;
import org.kainos.ea.cli.Role;
import org.kainos.ea.cli.User;
import org.kainos.ea.client.FailedLoginException;
import org.mindrot.jbcrypt.BCrypt;

import java.security.Key;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


public class AuthDao {

    private  DatabaseConnector databaseConnector;

    public AuthDao(DatabaseConnector databaseConnector) {
        this.databaseConnector=databaseConnector;
    }




    public User getUserByEmail(String email, String password) throws SQLException {
        Connection c = databaseConnector.getConnection();
        PreparedStatement ps = c.prepareStatement("SELECT UserID, Email, Password, RoleID FROM `Users` WHERE Email='" + email + "'");
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            User user = new User(
                    rs.getInt("UserID"),
                    rs.getString("Email"),
                    Role.fromId(rs.getInt("RoleID")),
                    rs.getString("Password")
            );
            return user;
        }

        return null;
    }
}