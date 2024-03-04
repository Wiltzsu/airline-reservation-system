package airlinereservationsystem;
import java.util.Scanner;
import admin.AddFlight;

public class main {

	public static void main(String[] args) {
		// Create a scanner to read user input
		Scanner userChoice = new Scanner(System.in);

		System.out.println("Admin or Customer?");
		String userType = userChoice.nextLine(); // Read user input as string
		
		// Logic based on user input
		if (userType.equalsIgnoreCase("admin")) {
			System.out.println("Welcome admin!");
			
			Scanner adminchoiceScanner = new Scanner(System.in);
			
			// Admin related functions
	        while (true) {
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
	            int choice = adminchoiceScanner.nextInt(); // Read user choice

	            switch (choice) {
	                case 1:
	                    // Add new flight logic
	                    AddFlight addFlight = new AddFlight(); // Create an instance of AddFlight
	                    addFlight.addFlightDetails(); // Call the method to add flights
	                    System.out.println("Adding new flight...");
	                    break;
	                case 2:
	                    // Update flight details logic
	                    System.out.println("Updating flight details...");
	                    break;
	                case 3:
	                    // Delete flights logic
	                    System.out.println("Deleting flights...");
	                    break;
	                case 4:
	                    // View all flights logic
	                    System.out.println("Viewing all flights...");
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
	                    adminchoiceScanner.close(); // Close the scanner before exiting
	                    return; // Exit the program
	                default:
	                    System.out.println("Invalid choice, please try again.");
	                    break;
	            }
				
			}
			
		} else if (userType.equalsIgnoreCase("customer")) {
			System.out.println("Welcome customer!");
			
			// Customer related functions

			
		} else {
			System.out.println("Invalid input. Please type admin or customer.");
		}
		
		userChoice.close();
		
	}

}
