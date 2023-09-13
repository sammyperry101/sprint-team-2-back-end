package org.kainos.ea.db;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DatabaseConnectorTest {
    @Test
    void getConnection_ShouldReturnConn_WhenConnIsNotNull() throws SQLException {
        Connection mockConnection = mock(Connection.class);

        DatabaseConnector.setConn(mockConnection);

        Connection result = DatabaseConnector.getConnection();

        Assertions.assertSame(mockConnection, result);
    }

    @Test
    void getConnection_ShouldReturnConn_WhenConnIsNotClosed() throws SQLException {
        Connection mockConnection = mock(Connection.class);

        DatabaseConnector.setConn(mockConnection);

        Mockito.when(mockConnection.isClosed()).thenReturn(false);

        Connection result = DatabaseConnector.getConnection();

        Assertions.assertSame(mockConnection, result);
    }
}
