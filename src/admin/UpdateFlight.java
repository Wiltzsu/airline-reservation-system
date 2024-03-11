package admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class UpdateFlight {

	public static void UpdateOneFlight(Connection conn) {
		
		Scanner scanner = new Scanner(System.in);
		
		try {
			// Enter flight ID of the flight to update
			System.out.println("Enter flight ID to update: ");
            int flightId = Integer.parseInt(scanner.nextLine()); // Parse the input as an integer
            
            // Database query
            String dbQuery = "SELECT * FROM flights WHERE idFlightDetails = ?";
            
            // Prepare statement
            try (PreparedStatement dbStmt = conn.prepareStatement(dbQuery)) {
            	dbStmt.setInt(1, flightId); // '1' represents the index of the placeholder ?, placeholders are indexed to start from 1
            	
            	ResultSet rs = dbStmt.executeQuery();
            	
            	if (rs.next() ) {
            		System.out.println("Current flight details:");
            		System.out.println("Flight number: " + rs.getString("flightNumber"));
            		System.out.println("Departure location: " + rs.getString("departureLocation"));
            		System.out.println("Arrival location:" + rs.getString("arrivalLocation"));
            		
            		System.out.print("Departure time: ");
            		Timestamp departureTimestamp = rs.getTimestamp("departureTime"); // The getTimestamp method retrieves the DateTime value from the ResultSet for the column named "departureTime". This method returns a java.sql.Timestamp object.
            		if (departureTimestamp != null) {
            		    System.out.println(departureTimestamp.toString());
            		} else {
            		    System.out.println("No departure time set.");
            		}

            		System.out.print("Arrival time: ");
            		Timestamp arrivalTimestamp = rs.getTimestamp("arrivalTime");
            		if (arrivalTimestamp != null) {
            			System.out.println(arrivalTimestamp.toString());
            		} else {
            			System.out.println("No arrival time set.");
            		}

            		System.out.println("Price: " + rs.getBigDecimal("price"));
            		
            		// Ask for updates to each field and apply as necessary
            		System.out.println("Enter new flight (leave blank to keep current: ");
            		String newFlightNumber = scanner.nextLine();
            		
            		System.out.println("Enter new departure location (leave blank to keep current: ");
            		String newDepartureLocation = scanner.nextLine();
            		
            		System.out.println("Enter new arrival location (leave blank to keep current: ");
            		String newArrivalLocation = scanner.nextLine();
            		
                    System.out.print("Enter new departure time (leave blank to keep current, format yyyy-MM-dd HH:mm:ss): ");
                    String newDepartureTimeStr = scanner.nextLine();
                    LocalDateTime newDepartureTime = null;
                    if (!newDepartureTimeStr.isEmpty()) {
                        try {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                            newDepartureTime = LocalDateTime.parse(newDepartureTimeStr, formatter);
                            // Now we have newDepartureTime as a LocalDateTime object to use in your SQL statement
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid date-time format, please use the correct format (yyyy-MM-dd HH:mm:ss).");
                            // Handle error, maybe ask again or abort the operation
                        }
                    }
            		
            		System.out.println("Enter new arrival time (leave blank to keep current, format yyyy-MM-dd HH:mm:ss): ");
            		String newArrivalTimeStr = scanner.nextLine();
                    LocalDateTime newArrivalTime = null;
                    if (!newArrivalTimeStr.isEmpty()) {
                    	try {
                    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    		newArrivalTime = LocalDateTime.parse(newArrivalTimeStr, formatter);
                            // Now we have newArrivalTime as a LocalDateTime object to use in your SQL statement
                    	} catch (DateTimeParseException e) {
                            System.out.println("Invalid date-time format, please use the correct format (yyyy-MM-dd HH:mm:ss).");
                    	}
                    }
            		
            		System.out.println("Enter new price (leave blank to keep current: ");
            		int newPrice = scanner.nextInt();
            		
            		// Construct the update statement
            		String updateDb = "UPDATE flights SET flightNumber = ? WHERE idFlightDetails = ?";
            		try (PreparedStatement updateStatement = conn.prepareStatement(updateDb)) {
            			
            			// Set the parameters for the update statement
            			// Ternary conditional operator
            			// Checks whether newFlightNumber is empty an empty string. If it is empty, it means the user didn't provide a new flight number,
            			// so the current value is retrieved from the database (using rs.getString). rs is the ResultSet containing the data from the database query.
            			updateStatement.setString(1, newFlightNumber.isEmpty() ? rs.getString("flightNumber") : newFlightNumber);
            			updateStatement.setString(2, newDepartureLocation.isEmpty() ? rs.getString("departureLocation") : newDepartureLocation);
            			updateStatement.setString(3, newArrivalLocation.isEmpty() ? rs.getString("arrivalLocation") : newArrivalLocation);

                        // Handle time stamps:
                        // Check if the input strings are empty and use existing values from the ResultSet if they are
            			// The isEmpty() checks are used correctly on String objects.
            			// The LocalDateTime.parse() is used to convert non-empty strings to LocalDateTime objects.
            			// Converted LocalDateTime objects are further transformed to Timestamp objects for compatibility with SQL operations.
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        Timestamp newDepartureTimestamp = newDepartureTimeStr.isEmpty() ?
                                rs.getTimestamp("departureTime") :
                                Timestamp.valueOf(LocalDateTime.parse(newDepartureTimeStr, formatter));
                        updateStatement.setTimestamp(4, newDepartureTimestamp);

                        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        Timestamp newArrivalTimestamp = newArrivalTimeStr.isEmpty() ?
                        		rs.getTimestamp("arrivalTime") :
                        		Timestamp.valueOf(LocalDateTime.parse(newArrivalTimeStr, formatter2));
                        updateStatement.setTimestamp(5, newArrivalTimestamp);
            		}
        		}
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid integer for the flight ID.");
        } catch (SQLException e) {
            System.out.println("An error occurred while attempting to update the flight.");
            e.printStackTrace();
        }
	}
}
