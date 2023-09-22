package org.kainos.ea.auth;

import io.dropwizard.auth.Authenticator;
import org.kainos.ea.cli.User;

import io.dropwizard.auth.AuthenticationException;
import java.util.Optional;

public class JWTAuthenticator implements Authenticator<String, User> {
    private TokenService tokenService;

    public JWTAuthenticator(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public Optional<User> authenticate(String token)  throws AuthenticationException {
        try {
            boolean isTokenValid = tokenService.isValidToken(token);

            if (isTokenValid) {
                Optional<User> user = tokenService.decodeToken(token);

                return user;
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new AuthenticationException("Failed to authenticate", e);
        }
    }
}
