package org.kainos.ea.db;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "org.kainos.ea.*")
public class DatabaseConnectorTest {

    private Connection conn = Mockito.mock(Connection.class);
    private Properties props = Mockito.mock(Properties.class);

    @Test
    void getConnection_ShouldReturnConn_WhenConnIsNotNullAndNotClosed() throws SQLException {
        DatabaseConnector.setConn(conn);

        Connection returnedConn = DatabaseConnector.getConnection();

        Assertions.assertEquals(conn, returnedConn);
    }
    @Test
    void getConnections_ThrowsIllegalArgumentException_WhenUserIsNull(){
        Mockito.whenNew()

        Mockito.when(props.getProperty("user")).thenReturn(null);

        Assertions.assertThrows(IllegalArgumentException.class, DatabaseConnector::getConnection);
    }
}
