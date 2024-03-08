package admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DeleteFlight {

	// Connection object can be reused in different parts of the app. This promotes code reusability
	// Using the same connection for all database operations helps maintain atomicity
	// Creating a new database connection is a relatively expensive operation
	public static void DeleteOneFlight(Connection conn) {
		
		Scanner scanner = new Scanner(System.in);
		
		try {
			// Enter flight ID of the flight to delete
			System.out.println("Enter flight ID to delete: ");
            int flightId = Integer.parseInt(scanner.nextLine()); // Parse the input as an integer.
			
			// SQL DELETE statement where id is a placeholder for the id that's going to be deleted
			String sql = "DELETE FROM flights where idFlightDetails = ?";
			
			// Try block to manage the PreparedStatement resources
			try (PreparedStatement preparedStmt = conn.prepareStatement(sql)) {
				// '1' symbolizes parameterIndex, which refers to the position of the ? in the SQL statement
				// The indexes start at 1, which means 1 is the first placeholder, which is idflightDetails (flightId)
				preparedStmt.setInt(1, flightId);
				
				System.out.println("Are you sure you want to delete this flight? (yes/no");
				String choice = scanner.nextLine();
				
                if ("yes".equalsIgnoreCase(choice)) {
                    // Execute the DELETE operation if 'yes' is confirmed.
                    int rowsAffected = preparedStmt.executeUpdate();

					// Check if a flight was deleted
					if (rowsAffected > 0) {
						System.out.println("Flight deleted succesfully!");
					} else {
						System.out.println("No flight was deleted (it may not exist).");
					}
                } else if ("no".equalsIgnoreCase(choice)) {
                	System.out.println("Canceling flight deletion.");
                } else {
                	System.out.println("Please enter 'yes' or 'no'.");
                }

            } catch (SQLException e) {
                System.out.println("An error occurred while attempting to delete the flight.");
                e.printStackTrace();
            }

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid integer for the flight ID.");
        }

        // Note: Do not close the scanner here, as it's passed from outside and might be used again.
    }
}
