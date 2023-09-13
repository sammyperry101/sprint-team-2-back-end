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

    @Test
    void getConnection_ShouldThrowIllegalArgumentException_WhenUserIsNull() throws IOException {
        //FileInputStream mockStream = mock(FileInputStream.class);

        //Mockito.when(mockStream.read()).thenReturn(-1);

        /*DatabaseConnector.setUser(null);
        DatabaseConnector.setPassword("testpass");
        DatabaseConnector.setHost("testost");
        DatabaseConnector.setName("testname");

        //Mockito.when(new FileInputStream(anyString())).thenReturn(mockStream);

        assertThrows(IllegalArgumentException.class, () -> DatabaseConnector.getConnection());*/

        /*DatabaseConnector mockConnector = mock(DatabaseConnector.class);

        when(mockConnector.getUser()).thenReturn(null);
        when(mockConnector.getPassword()).thenReturn("testpass");
        when(mockConnector.getHost()).thenReturn("testhost");
        when(mockConnector.getName()).thenReturn("testname");

        assertThrows(IllegalArgumentException.class, () -> mockConnector.getConnection());*/
    }

    @Test
    void getConnection_ShouldThrowIllegalArgumentException_WhenPassIsNull() throws IOException {

    }

    @Test
    void getConnection_ShouldThrowIllegalArgumentException_WhenhostIsNull() throws IOException {

    }


}
