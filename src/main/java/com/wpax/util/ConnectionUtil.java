package com.wpax.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import io.github.cdimascio.dotenv.Dotenv;

public class ConnectionUtil {

    public static Connection getConnection() {
        // if DB env are setup then create connection

        Dotenv dotenv = Dotenv.load();

        if (dotenv.get("RDS_HOSTNAME") != null) {
            try {
                // grab postgres driver (Maven dependency)
                Class.forName("org.postgresql.Driver");

                // grab DB connection parameters from env
                String dbName = dotenv.get("RDS_DB_NAME");
                String userName = dotenv.get("RDS_USERNAME");
                String password = dotenv.get("RDS_PASSWORD");
                String hostname = dotenv.get("RDS_HOSTNAME");
                String port = dotenv.get("RDS_PORT");

                String jdbcUrl = "jdbc:postgresql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName
                        + "&password=" + password;

                // attempt to connect to DB
                return DriverManager.getConnection(jdbcUrl);

            } catch (ClassNotFoundException e) {

                // postgres driver not found
                System.out.println("Class not found: " + e.toString());
            } catch (SQLException e) {

                // error connecting to DB
                e.printStackTrace();
                System.out.println("SQL Error: " + e.toString());
            }
        } else {
            System.out.println("ENV variables not found.");

        }
        // return null if no connection was made
        return null;
    }

}
