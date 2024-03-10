package admin;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import airlinereservationsystem.DBConnection;

public class Operations {

	public static void adminOperations(Scanner scanner) {
		
		Connection conn = null;
		
		try {
			conn = DBConnection.getConnection();
			if (conn == null) {
				System.out.println("Could not establish connection to the database.");
				return;
			}
			
			boolean exit = false; // Boolean value to exit loop
			while (!exit) {
	            System.out.println("Choose operation:");
	            System.out.println("1. Add new flight");
	            System.out.println("2. Update flight details");
	            System.out.println("3. Delete flights");
	            System.out.println("4. View all flights");
	            System.out.println("5. View bookings");
	            System.out.println("6. Modify bookings");
	            System.out.println("7. Cancel bookings");
	            System.out.println("8. Set pricing");
	            System.out.println("9. Create offers");
	            System.out.println("0. Exit"); // Option to exit

	            System.out.print("Enter choice: ");
	            int choice = scanner.nextInt(); // Read user choice
	            
	            switch (choice) {
	                case 1:
                        AddFlight addFlight = new AddFlight(); // Create a new AddFlight object
                        addFlight.addFlightDetails(conn); // Call the method to add flights
                        System.out.println("Adding new flight...");
	                    break;
	                    
	                case 2:
	                    admin.UpdateFlight.UpdateOneFlight(conn);
	                    break;
	                case 3:
	                    // Delete flights logic
	                	admin.DeleteFlight.DeleteOneFlight(conn);
	                    break;
	                case 4:
                		admin.ViewFlights.ViewAllFlights(conn);
	                    break;
	                case 5:
	                    // View bookings logic
	                    System.out.println("Viewing bookings...");
	                    break;
	                case 6:
	                    // Modify bookings logic
	                    System.out.println("Modifying bookings...");
	                    break;
	                case 7:
	                    // Cancel bookings logic
	                    System.out.println("Cancelling bookings...");
	                    break;
	                case 8:
	                    // Set pricing logic
	                    System.out.println("Setting pricing...");
	                    break;
	                case 9:
	                    // Create offers logic
	                    System.out.println("Creating offers...");
	                    break;
	                case 0:
	                    // Exit option
	                    System.out.println("Exiting...");
	                    return; // Exit the program
	                default:
	                    System.out.println("Invalid choice, please try again.");
	                    break;
                }
            }
	    } catch (SQLException e) {
	        System.out.println("Database connection error.");
	        e.printStackTrace();
	    } catch (IOException e) {
	        System.out.println("Error reading database configuration.");
	        e.printStackTrace();
	    } finally {
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                System.out.println("Failed to close the database connection.");
	                e.printStackTrace();
	            }
	        }
	    }
	}
}

