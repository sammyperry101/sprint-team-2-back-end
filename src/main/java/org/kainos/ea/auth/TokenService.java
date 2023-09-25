package org.kainos.ea.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.kainos.ea.cli.AuthRole;
import org.kainos.ea.cli.User;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

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

    public Optional<User> decodeToken(String token) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            int userId = claims.get("userId", Integer.class);
            String email = claims.get("email", String.class);
            AuthRole role = claims.get("role", AuthRole.class);
            User user = new User(userId, email, role);

            return Optional.of(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public boolean isValidToken(String token) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
