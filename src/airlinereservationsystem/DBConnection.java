package airlinereservationsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.InputStream;

public class DBConnection {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            // Load properties file from the classpath
            Properties props = new Properties();
            InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("db/db.properties");
            
            if (input == null) {
                System.out.println("Sorry, unable to find db.properties");
                return null;
            }
            
            // Load the properties
            props.load(input);
            input.close();

            // Retrieve properties
            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");

            // Establish the connection
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            // Handle exceptions appropriately
            e.printStackTrace();
        }
        return conn;
    }
}
