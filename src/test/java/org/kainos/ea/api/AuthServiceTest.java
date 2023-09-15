package org.kainos.ea.api;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.cli.LoginRequest;
import org.kainos.ea.cli.Role;
import org.kainos.ea.cli.User;
import org.kainos.ea.db.AuthDao;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.kainos.ea.client.FailedLoginException;
import java.security.Key;
import java.sql.SQLException;
import java.util.Date;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
    AuthDao authDaoMock = Mockito.mock(AuthDao.class);

    AuthService authService = new AuthService(authDaoMock);
    Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);


    @Test
    void login_ShouldReturnString_WhenUserIsValid() throws Exception, FailedLoginException {
        int userId = 1;
        String email = "johndoe@gmail.com";
        Role role = Role.ADMIN;
        User mockUser = new User(userId, email, role);
        String mockToken = Jwts.builder()
                .setSubject(mockUser.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        AuthService authServiceMock = Mockito.mock(AuthService.class);

        LoginRequest mockLoginRequest = new LoginRequest(email, "password");

        when(authDaoMock.validLogin(mockLoginRequest)).thenReturn(mockUser);

        String result = authService.login(mockLoginRequest);

        assertNotNull(result);

        assertEquals(mockUser, result);

    }

    @Test
    void login_ShouldReturnNull_WhenDaoReturnsNull() throws Exception, FailedLoginException {
        String email = "johndoe@gmail.com";

        LoginRequest mockLoginRequest = new LoginRequest(email, "password");

        when(authDaoMock.validLogin(mockLoginRequest)).thenReturn(null);

        String result = authService.login(mockLoginRequest);

        assertNull(result);
    }

    @Test
    void login_ShouldThrowException_WhenDaoThrowsException() throws SQLException {
        String email = "johndoe@gmail.com";

        LoginRequest mockLoginRequest = new LoginRequest(email, "password");

        when(authDaoMock.validLogin(mockLoginRequest)).thenThrow(SQLException.class);

        assertThrows(FailedLoginException.class, () -> authService.login(mockLoginRequest));
    }

    @Test
    void generateToken_ShouldReturnValidToken(){
        String email = "johndoe@gmail.com";
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        String token = authService.generateToken(email);

        assertNotNull(token);
    }

}
