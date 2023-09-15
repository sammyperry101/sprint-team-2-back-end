package org.kainos.ea.resources;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.api.AuthService;
import org.kainos.ea.cli.LoginRequest;
import org.kainos.ea.cli.Role;
import org.kainos.ea.cli.User;
import org.kainos.ea.client.FailedLoginException;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;

import java.security.Key;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {
    AuthService authServiceMock = Mockito.mock(AuthService.class);

    AuthController authController = new AuthController(authServiceMock);
    Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    @Test
    void login_ShouldReturn200Response_whenLoginSuccessful() throws FailedLoginException {
        int userId = 1;
        String email = "mateenparkar4@gmail.com";
        Role role = Role.ADMIN;
        User mockUser = new User(userId, email, role);

        String token = Jwts.builder()
                .setSubject(mockUser.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        LoginRequest loginRequest = new LoginRequest(email, System.getenv("PASSWORD"));

        when(authServiceMock.login(loginRequest))
                .thenReturn(token);

        Response response = authController.login(loginRequest);

        assertEquals(200, response.getStatus());
    }

    @Test
    void login_ShouldReturn401_WhenLoginUnsuccessful() {
        String email = "email@email.com";


        LoginRequest mockLoginRequest = new LoginRequest(email, "password");
        when(authServiceMock.login(mockLoginRequest))
                .thenThrow(new FailedLoginException());

        Response response = authController.login(mockLoginRequest);

        assertEquals(401, response.getStatus());

    }


}
