package org.kainos.ea.api;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.kainos.ea.auth.TokenService;
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

    TokenService tokenService = new TokenService();

    public AuthService(AuthDao authDao, TokenService tokenService) {
        this.authDao = authDao;
        this.tokenService = tokenService;

    }

    private AuthDao authDao;




    public String login(LoginRequest login) throws FailedLoginException, SQLException {
        try {
            User user = authDao.getUserByEmail(login.getEmail());
            if (user == null || !(isValidPassword(login.getPassword(), user.getHashedPassword()))){
                throw new FailedLoginException();
            }

            String token = tokenService.generateToken(user.getEmail());
            if (token != null) {
                return token;
            }
        } catch (SQLException e) {
            throw new FailedToGenerateTokenException();
        }

        throw new FailedLoginException();
    }

    public boolean isValidPassword(String candidatePassword, String hashedPassword){
        return BCrypt.checkpw(candidatePassword, hashedPassword);
    }


}
