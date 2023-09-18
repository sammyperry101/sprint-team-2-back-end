package org.kainos.ea.api;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.kainos.ea.cli.LoginRequest;
import org.kainos.ea.cli.User;
import org.kainos.ea.client.FailedLoginException;
import org.kainos.ea.client.FailedToGenerateTokenException;
import org.kainos.ea.db.AuthDao;
import org.mindrot.jbcrypt.BCrypt;

import java.security.Key;
import java.sql.SQLException;
import java.util.Date;

public class AuthService {
    private static final long EXPIRATION_TIME = 86400000; // 24 hours in milliseconds


    public AuthService(AuthDao authDao) {
        this.authDao = authDao;

    }

    private AuthDao authDao;




    public String login(LoginRequest login) throws FailedLoginException, SQLException {
        try {
            User user = authDao.getUserByEmail(login.getEmail(), login.getPassword());
            if (user == null || !(isValidPassword(login.getPassword(), user.getHashedPassword()))){
                throw new FailedLoginException();
            }

            String token = generateToken(user.getEmail());
            if (token != null) {
                return token;
            }
        } catch (SQLException e) {
            throw new FailedToGenerateTokenException();
        }

        throw new FailedLoginException();
    }



    public String generateToken(String email) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        String subject = email;
        Date expiration = new Date(System.currentTimeMillis() + EXPIRATION_TIME);

        String token = Jwts.builder()
                .setSubject(subject)
                .setExpiration(expiration)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return token;
    }

    public boolean isValidPassword(String candidatePassword, String hashedPassword){
        return BCrypt.checkpw(candidatePassword, hashedPassword);
    }


}
