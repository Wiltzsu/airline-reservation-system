package airlinereservationsystem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.FileInputStream;

public class DBConnection {
    public static Connection getConnection() {
        Connection conn = null;
        int test;
        try {
            Properties props = new Properties();
            FileInputStream in = new FileInputStream("src/db.properties");
            props.load(in);
            in.close();

            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");

            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
