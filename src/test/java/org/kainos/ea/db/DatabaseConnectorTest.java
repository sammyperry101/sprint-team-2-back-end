package org.kainos.ea.db;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
public class DatabaseConnectorTest {

    private Connection conn = Mockito.mock(Connection.class);
    @Test
    void getConnection_ShouldReturnConn_WhenConnIsNotNullAndNotClosed() throws SQLException {
        DatabaseConnector.setConn(conn);

        Connection returnedConn = DatabaseConnector.getConnection();

        Assertions.assertEquals(conn, returnedConn);
    }
//    @Test
//    void getConnection_ThrowsIllegalArgumentException_WhenUserIsNull() throws Exception {
//        Properties props = Mockito.mock(Properties.class);
//        DatabaseConnector databaseConnector = new DatabaseConnector(props);
//
//        Mockito.when(props.getProperty("user")).thenReturn(null);
//        Mockito.when(props.getProperty("password")).thenReturn("testpass");
//        Mockito.when(props.getProperty("host")).thenReturn("testhost");
//        Mockito.when(props.getProperty("name")).thenReturn("testname");
//        Assertions.assertThrows(IllegalArgumentException.class, () -> databaseConnector.getConnection());
//    }
//    @Test
//    void getConnection_ThrowsIllegalArgumentException_WhenHostIsNull() throws Exception {
//        Properties props = Mockito.mock(Properties.class);
//        DatabaseConnector databaseConnector = new DatabaseConnector(props);
//
//        Mockito.when(props.getProperty("user")).thenReturn("testuser");
//        Mockito.when(props.getProperty("password")).thenReturn("testpass");
//        Mockito.when(props.getProperty("host")).thenReturn(null);
//        Mockito.when(props.getProperty("name")).thenReturn("testname");
//        Assertions.assertThrows(IllegalArgumentException.class, () -> databaseConnector.getConnection());
//    }
}
