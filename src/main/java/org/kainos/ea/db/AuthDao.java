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
        Object[] userAndPassword = getUserByEmail(login.getEmail());

        if (userAndPassword != null) {
            User user = (User) userAndPassword[0];
            String hashedPassword = (String) userAndPassword[1];

            if (isValidPassword(login.getPassword(), hashedPassword)) {
                return user;
            }
        }

        throw new FailedLoginException();
    }

    public Object[] getUserByEmail(String email) throws SQLException {
        Connection c = databaseConnector.getConnection();
        PreparedStatement ps = c.prepareStatement("SELECT UserID, Email, Password, RoleID FROM `Users` WHERE Email='" + email + "'");
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            User user = new User(
                    rs.getInt("UserID"),
                    rs.getString("Email"),
                    Role.fromId(rs.getInt("RoleID"))
            );
            String hashedPassword = rs.getString("Password");
            return new Object[]{user, hashedPassword};
        }

        return null;
    }
    public boolean isValidPassword(String candidatePassword, String hashedPassword) {
        return BCrypt.checkpw(candidatePassword, hashedPassword);
    }

}