package org.kainos.ea.db;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
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


    public User validLogin(LoginRequest login) throws SQLException, FailedLoginException {
        Connection c = databaseConnector.getConnection();

        PreparedStatement ps = c.prepareStatement("SELECT UserID, Email, Password, RoleID FROM `Users` WHERE Email='"
                + login.getEmail() + "'");

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String hashedPassword = rs.getString("Password");
            String candidatePassword = login.getPassword();

            if (BCrypt.checkpw(candidatePassword, hashedPassword)) {
                return new User(
                        rs.getInt("UserID"),
                        rs.getString("Email"),
                        Role.fromId(rs.getInt("RoleID"))
                );
            }

        }

        throw new FailedLoginException();
    }
}