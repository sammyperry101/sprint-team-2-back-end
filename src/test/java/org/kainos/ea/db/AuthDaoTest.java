package org.kainos.ea.db;
import io.dropwizard.testing.junit5.DAOTestExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.cli.LoginRequest;
import org.kainos.ea.cli.Role;
import org.kainos.ea.cli.User;
import org.kainos.ea.client.FailedLoginException;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.Key;
import java.sql.*;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthDaoTest {
    private DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);
    private Connection connection = Mockito.mock(Connection.class);
    private PreparedStatement statement = Mockito.mock(PreparedStatement.class);
    private ResultSet resultSet = Mockito.mock(ResultSet.class);


    private AuthDao authDao = new AuthDao(databaseConnector);


    @Test
    void validLogin_ShouldReturnUser_WhenLoginRequestIsValid() throws SQLException, FailedLoginException {
        String email = "mateenparkar4@gmail.com";
        String salt = BCrypt.gensalt();
        LoginRequest validLoginRequest = new LoginRequest("mateenparkar4@gmail.com", "password");
        User expectedUser = new User(1, "mateenparkar4@gmail.com", Role.ADMIN);

        String preparedStatement = "SELECT UserID, Email, Password, RoleID FROM `Users` WHERE Email='" + email + "'";
        DatabaseConnector.setConn(connection);
        Mockito.when(connection.prepareStatement(preparedStatement)).thenReturn(statement);
        Mockito.when(statement.executeQuery()).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(true);
        Mockito.when(resultSet.getInt("UserID")).thenReturn(1);
        Mockito.when(resultSet.getString("Email")).thenReturn("mateenparkar4@gmail.com");
        Mockito.when(resultSet.getString("Password")).thenReturn(BCrypt.hashpw("password", salt));
        Mockito.when(resultSet.getInt("RoleID")).thenReturn(1);

        User user = authDao.validLogin(validLoginRequest);

        assertEquals(expectedUser.getUserId(),user.getUserId());
        assertEquals(expectedUser.getEmail(),user.getEmail());
        assertEquals(expectedUser.getRole(),user.getRole());
    }

    @Test
    void validLogin_ShouldReturnException_WhenLoginRequestIsInvalid() throws SQLException {
        String email = "mateenparkar21@gmail.com";
        LoginRequest invalidLoginRequest = new LoginRequest(email, "password");

        String preparedStatement = "SELECT UserID, Email, Password, RoleID FROM `Users` WHERE Email='" + email + "'" + "AND PASSWORD='" + invalidLoginRequest.getPassword() + "'";
        DatabaseConnector.setConn(connection);
        Mockito.when(connection.prepareStatement(preparedStatement)).thenReturn(statement);
        Mockito.when(statement.executeQuery()).thenThrow(FailedLoginException.class);

        assertThrows(FailedLoginException.class,
                () -> authDao.validLogin(invalidLoginRequest));
    }
}