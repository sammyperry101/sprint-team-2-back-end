package org.kainos.ea.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class TokenService {

    private static final long EXPIRATION_TIME = 86400000; // 24 hours in milliseconds

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
}
