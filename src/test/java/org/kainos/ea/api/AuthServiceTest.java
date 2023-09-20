package org.kainos.ea.api;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.auth.TokenService;
import org.kainos.ea.cli.LoginRequest;
import org.kainos.ea.cli.RegisterRequest;
import org.kainos.ea.cli.Role;
import org.kainos.ea.cli.User;
import org.kainos.ea.client.FailedToGenerateTokenException;
import org.kainos.ea.client.FailedToRegisterException;
import org.kainos.ea.db.AuthDao;
import org.kainos.ea.validator.PasswordValidator;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.kainos.ea.client.FailedLoginException;
import java.security.Key;
import java.sql.SQLException;


import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
    AuthDao authDaoMock = Mockito.mock(AuthDao.class);
    TokenService tokenService = Mockito.mock(TokenService.class);
    AuthService authService = new AuthService(authDaoMock, tokenService);

    PasswordValidator passwordValidatorMock = Mockito.mock(PasswordValidator.class);

    Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);


    @Test
    void login_ShouldReturnString_WhenUserIsValid() throws Exception, FailedLoginException {

        int userId = 2;
        String email = "email@email.com";
        Role role = Role.ADMIN;
        String password = "$2a$09$NNN97Lre5WJPO5I7tQAKOOIfXNnkG4u.bDXlYlV2W3kS0wDEsuCyK";

        User mockUser = new User(userId, email, role, password);

        String mockToken = "token";
        LoginRequest mockLoginRequest = new LoginRequest(email, "password");
        when(authDaoMock.getUserByEmail(mockLoginRequest.getEmail())).thenReturn(mockUser);
        when(tokenService.generateToken(mockLoginRequest.getEmail())).thenReturn(mockToken);

        String result = authService.login(mockLoginRequest);

        assertNotNull(result);
        assertEquals("token", result);

    }

    @Test
    void login_ShouldThrowException_WhenDaoReturnsNull() throws Exception, FailedLoginException {
        String email = "johndoe@gmail.com";

        LoginRequest mockLoginRequest = new LoginRequest(email, "password");

        when(authDaoMock.getUserByEmail(mockLoginRequest.getEmail())).thenReturn(null);


        assertThrows(FailedLoginException.class, () -> authService.login(mockLoginRequest));
    }

    @Test
    void login_ShouldThrowException_WhenDaoThrowsException() throws SQLException {
        String email = "johndoe@gmail.com";

        LoginRequest mockLoginRequest = new LoginRequest(email, "password");

        when(authDaoMock.getUserByEmail(mockLoginRequest.getEmail())).thenThrow(SQLException.class);

        assertThrows(FailedToGenerateTokenException.class, () -> authService.login(mockLoginRequest));
    }

    @Test
    void login_testValidPassword() {
        // Given
        String password = "myPassword123";
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        // When
        boolean isValid = authService.isValidPassword(password, hashedPassword);

        // Then
        assertTrue(isValid);
    }

    @Test
    void login_testInvalidPassword() {
        // Given
        String correctPassword = "myPassword123";
        String wrongPassword = "wrongPassword123";
        String hashedPassword = BCrypt.hashpw(correctPassword, BCrypt.gensalt());

        // When
        boolean isValid = authService.isValidPassword(wrongPassword, hashedPassword);

        // Then
        assertFalse(isValid);
    }

    @Test
    void register_ShouldThrowFailedRegisterException_WhenValidatorFails(){
        RegisterRequest registerRequest = new RegisterRequest("user8@user.com", "password", Role.ADMIN);

        when(passwordValidatorMock.validateUser(registerRequest)).thenThrow(FailedToRegisterException.class);

        assertThrows(FailedToRegisterException.class, () -> authService.register(registerRequest));

    }


}
