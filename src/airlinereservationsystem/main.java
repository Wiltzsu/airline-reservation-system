package airlinereservationsystem;
import java.util.Scanner;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import admin.AddFlight;
import admin.Operations;

public class main {

	public static void main(String[] args) {
		
        try {
            // Attempt to get a connection
            Connection conn = DBConnection.getConnection();
            
            // If successful, print out a success message
            if (conn != null) {
                System.out.println("Successfully connected to the database.");
                
                // Don't forget to close the connection when done
                conn.close();
            }
            
        } catch (SQLException e) {
        	System.out.println("Database connection error.");
            e.printStackTrace();
            
        } catch (IOException e) {
            System.out.println("Error loading database configuration.");
            e.printStackTrace();
        }
		
		// Create a scanner to read input
		Scanner scanner = new Scanner(System.in);
		
		try {
			System.out.println("Admin or Customer?");
			String userType = scanner.nextLine(); // Read user input as string
			
			// Logic based on user input
			if (userType.equalsIgnoreCase("admin")) {
				
				System.out.println("Welcome admin!");
				// Admin related functions
				Operations.adminOperations(scanner); // Pass the scanner to the admin operations method		
				
			} else if (userType.equalsIgnoreCase("customer")) {
				
				System.out.println("Welcome customer!");
				// Customer related functions
				
			} else {
				System.out.println("Invalid input. Please type admin or customer.");
			}
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
	}
}
