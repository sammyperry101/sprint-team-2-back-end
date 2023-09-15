package org.kainos.ea.db;

import org.kainos.ea.client.FailedToGetJobRoles;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnector {

    private static Connection conn;
    private static Properties props;

    public DatabaseConnector(Properties properties) {
        this.props = properties;
    }
    public static Connection getConnection() throws SQLException {
        String user, password, host, name;

        if(conn != null && !conn.isClosed()){
            return conn;
        }

        try(FileInputStream propsStream = new FileInputStream("db.properties");){

            props.load(propsStream);

            user = props.getProperty("user");
            password = props.getProperty("password");
            host = props.getProperty("host");
            name = props.getProperty("name");

            System.out.println(user + " " + password + " " + host);

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
}
