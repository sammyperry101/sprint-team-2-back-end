package org.kainos.ea.db;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.anyString;

public class DatabaseConnectorTest {

    private Connection conn = Mockito.mock(Connection.class);

    private DatabasePropertiesManager props = Mockito.mock(DatabasePropertiesManager.class);

    DatabaseConnector databaseConnector = new DatabaseConnector();
    @Test
    void getConnection_ShouldReturnConn_WhenConnIsNotNullAndNotClosed() throws SQLException {
        DatabaseConnector.setConn(conn);

        Connection returnedConn = DatabaseConnector.getConnection();

        Assertions.assertEquals(conn, returnedConn);
    }
    @Test
    void getConnection_ThrowsIllegalArgumentException_WhenUserIsNull() throws Exception {

        Mockito.when(props.returnString(System.getenv("DB_USERNAME"))).thenReturn(null);
        Mockito.when(props.returnString(System.getenv("DB_PASSWORD"))).thenReturn("testpass");
        Mockito.when(props.returnString(System.getenv("DB_HOST"))).thenReturn("testhost");
        Mockito.when(props.returnString(System.getenv("DB_NAME"))).thenReturn("testname");
        Assertions.assertThrows(IllegalArgumentException.class, () -> databaseConnector.getConnection());
    }
    @Test
    void getConnection_ThrowsIllegalArgumentException_WhenHostIsNull() throws Exception {

        Mockito.when(props.returnString(System.getenv("DB_USERNAME"))).thenReturn("testuser");
        Mockito.when(props.returnString(System.getenv("DB_PASSWORD"))).thenReturn("testpass");
        Mockito.when(props.returnString(System.getenv("DB_HOST"))).thenReturn(null);
        Mockito.when(props.returnString(System.getenv("DB_NAME"))).thenReturn("testname");
        Assertions.assertThrows(IllegalArgumentException.class, () -> databaseConnector.getConnection());
    }
}
