package airlinereservationsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;

public class DBConnection {
    public static Connection getConnection() throws SQLException, IOException {
        Connection conn = null;
        Properties props = new Properties();
        InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("db/db.properties");

        if (input == null) {
            System.out.println("Sorry, unable to find db.properties");
            return null;
        }

        try {
            // Load the properties
            props.load(input);

            // Retrieve properties
            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");

            // Establish the connection
            conn = DriverManager.getConnection(url, user, password);
        } finally {
            if (input != null) {
                input.close();
            }
        }
        return conn;
    }
}
