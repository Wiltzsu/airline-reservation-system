package admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewFlights {

	public static void ViewAllFlights(Connection conn) {
		
		String sql = "SELECT * FROM flights";
		
        // Initialize PreparedStatement inside the try-with-resources block
        try (PreparedStatement preparedStmt = conn.prepareStatement(sql);) {
        	
                ResultSet rs = preparedStmt.executeQuery(); // Use executeQuery for SELECT

                while (rs.next()) {
                // .next moves the cursor forward one row from its current position.
                // It returns true if the new current row is valid; false if there are no more rows.
                	int flightId = rs.getInt("idflightDetails");
                	String flightNumber = rs.getString("flightNumber");
                	String departureLocation = rs.getString("departureLocation");
                	String arrivalLocation = rs.getString("arrivalLocation");
                	String departureTime = rs.getString("departureTime");
                	String arrivalTime = rs.getString("arrivalTime");
                	Double price = rs.getDouble("price");
                	
                	// Print the details to the console
                	System.out.println("Flight ID: " + flightId +
                			", Flight number: " + flightNumber +
                			", Departure location: " + departureLocation +
                			", Arrival location: " + arrivalLocation +
                			", Dpearture time: " + departureTime +
                			", Arrival time: " + arrivalTime +
                			", Price " + price);
                }
            } catch (SQLException e) {
                // Handle possible SQLExceptions here
                e.printStackTrace();
            }
        }
    }