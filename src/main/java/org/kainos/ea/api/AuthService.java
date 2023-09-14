package org.kainos.ea.api;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.kainos.ea.cli.LoginRequest;
import org.kainos.ea.cli.User;
import org.kainos.ea.client.FailedLoginException;
import org.kainos.ea.db.AuthDao;

import java.security.Key;
import java.util.Date;

public class AuthService {
    private static final long EXPIRATION_TIME = 86400000; // 24 hours in milliseconds


    public AuthService(AuthDao authDao) {
        this.authDao = authDao;

    }

    private AuthDao authDao;




    public ImmutablePair<User, String> login(LoginRequest login) throws FailedLoginException {
        try {
            User user = authDao.validLogin(login);
            if (user != null) {
                return new ImmutablePair<>(user, generateToken(user.getEmail()));
            }else {
                throw new FailedLoginException();
            }
        } catch (Exception e) {
            e.printStackTrace();
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
