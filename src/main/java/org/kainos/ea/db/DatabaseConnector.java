package org.kainos.ea.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnector {

    private static Connection conn;
    private static DatabasePropertiesManager props;
    public static Connection getConnection() throws SQLException {
        String user, password, host, name;

        if(conn != null && !conn.isClosed()){
            return conn;
        }

        try {

            user = props.returnString(System.getenv("DB_USERNAME"));
            password = props.returnString(System.getenv("DB_PASSWORD"));
            host = props.returnString(System.getenv("DB_HOST"));
            name = props.returnString(System.getenv("DB_NAME"));

            if(user == null || password == null || host == null){
                throw new Exception("Properties file must exist " +
                        "and must contain user, password, name and host properties");
            }

            conn = DriverManager.getConnection("jdbc:mysql://" + host + "/" + name + "?useSSL=false", user, password);
            return conn;

        } catch (Exception e) {
            System.err.println(e.getMessage());

            throw new IllegalArgumentException();
        }

    }
    public static void setConn(Connection connection){
        conn = connection;
    }

    public static void setProps(DatabasePropertiesManager properties){
        props = properties;
    }
}
