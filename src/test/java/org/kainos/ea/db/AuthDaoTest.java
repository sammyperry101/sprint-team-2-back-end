package org.kainos.ea.db;
import io.dropwizard.testing.junit5.DAOTestExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.cli.LoginRequest;
import org.kainos.ea.cli.Role;
import org.kainos.ea.cli.User;
import org.kainos.ea.client.FailedLoginException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.*;
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
        LoginRequest validLoginRequest = new LoginRequest("mateenparkar4@gmail.com", "password");
        User expectedUser = new User(1, "mateenparkar4@gmail.com", Role.ADMIN);

        String preparedStatement = "SELECT UserID, Email, Password, RoleID FROM `Users` WHERE Email=?;";
        Mockito.when(databaseConnector.getConnection()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(preparedStatement)).thenReturn(statement);



        // Act
        User user = authDao.validLogin(validLoginRequest);
        // Assert
        assertEquals(expectedUser, user);
    }


}
