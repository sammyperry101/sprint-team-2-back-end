package org.kainos.ea.resources;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.api.AuthService;
import org.kainos.ea.cli.LoginRequest;
import org.kainos.ea.cli.RegisterRequest;
import org.kainos.ea.cli.Role;
import org.kainos.ea.cli.User;
import org.kainos.ea.client.FailedLoginException;
import org.kainos.ea.client.FailedToGetRoleException;
import org.kainos.ea.client.FailedToRegisterException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;

import java.security.Key;
import java.sql.SQLException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {
    AuthService authServiceMock = Mockito.mock(AuthService.class);

    AuthController authController = new AuthController(authServiceMock);

    Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    @Test
    void login_ShouldReturn200Response_whenLoginSuccessful() throws FailedLoginException, SQLException {
        int userId = 1;
        String email = "email@email.com";
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
    void login_ShouldReturn401_WhenLoginUnsuccessful() throws SQLException {
        String email = "email@email.com";


        LoginRequest mockLoginRequest = new LoginRequest(email, "password");
        when(authServiceMock.login(mockLoginRequest))
                .thenThrow(new FailedLoginException());

        Response response = authController.login(mockLoginRequest);

        assertEquals(401, response.getStatus());

    }

    @Test
    void login_ShouldReturn500_WhenServerError() throws SQLException{
        String email = "email@email.com";


        LoginRequest mockLoginRequest = new LoginRequest(email, "password");
        when(authServiceMock.login(mockLoginRequest))
                .thenThrow(new SQLException());

        Response response = authController.login(mockLoginRequest);

        assertEquals(500, response.getStatus());
    }

    @Test
    void register_ShouldReturn201_WhenRegisterSuccessful() throws FailedToRegisterException {
        RegisterRequest registerRequest = new RegisterRequest("user6@user.com", "Password$", Role.ADMIN);
        Response response = authController.register(registerRequest);

        assertEquals(201, response.getStatus());
    }

    @Test
    void register_ShouldReturn400_WhenRegisterUnsuccessful() throws FailedToRegisterException, SQLException, FailedToGetRoleException {
        RegisterRequest registerRequest = new RegisterRequest("user6@user.com", "password$", Role.ADMIN);
        Mockito.doThrow(new FailedToRegisterException()).when(authServiceMock).register(registerRequest);

        Response response = authController.register(registerRequest);

        assertEquals(400, response.getStatus());
    }

    @Test
    void register_ShouldReturn500_WhenInternalServerError() throws FailedToRegisterException, SQLException, FailedToGetRoleException {
        RegisterRequest registerRequest = new RegisterRequest("user6@user.com", "PASSWORD$", Role.ADMIN);
        Mockito.doThrow(new SQLException()).when(authServiceMock).register(registerRequest);

        Response response = authController.register(registerRequest);

        assertEquals(500, response.getStatus());
    }


}
