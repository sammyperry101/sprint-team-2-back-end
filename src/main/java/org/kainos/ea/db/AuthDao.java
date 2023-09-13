package org.kainos.ea.db;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.kainos.ea.cli.LoginRequest;
import org.kainos.ea.cli.Role;
import org.kainos.ea.cli.User;
import org.kainos.ea.client.FailedLoginException;

import java.security.Key;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


public class AuthDao {
    private static final String SECRET_KEY = "ccGLbrAIzIlPmvOY"; // Replace with a strong secret key
    private static final long EXPIRATION_TIME = 86400000; // 24 hours in milliseconds
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
            return new User(
                    rs.getInt("UserID"),
                    rs.getString("Email"),
                    Role.fromId(rs.getInt("RoleID"))
            );

        }

        throw new FailedLoginException();
    }

    public String generateToken(String email) {
        // Create a key for signing the token (you can also use your own key management)
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        // Define the claims for the token (e.g., subject, expiration)
        String subject = email;
        Date expiration = new Date(System.currentTimeMillis() + EXPIRATION_TIME);

        // Build the JWT token
        String token = Jwts.builder()
                .setSubject(subject)
                .setExpiration(expiration)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return token;
    }
}