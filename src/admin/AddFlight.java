package admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AddFlight {
	
	// Attributes for AddFlight
	private String flightNumber;
	private String departureLocation;
	private String arrivalLocation;
	private String departureTime;
	private String arrivalTime;
	private double price;
	
	// Constructor
	public AddFlight
	() 
	{	
		this.flightNumber = flightNumber;
		this.departureLocation = departureLocation;
		this.arrivalLocation = arrivalLocation;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.price = price;
	}
	
	// Setters
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	
	public void setDepartureLocation(String departureLocation) {
		this.departureLocation = departureLocation;
	}
	
	public void setArrivalLocation(String arrivalLocation) {
		this.arrivalLocation = arrivalLocation;
	}
	
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	// Getters
	public String getFlightNumber(String flightNumber) {
		return this.flightNumber;
	}
	
	public String getDepartureLocation(String departureLocation) {
		return this.departureLocation;
	}
	
	public String getArrivalLocation(String arrivalLocation) {
		return this.arrivalLocation;
	}
	
	public String getDepartureTime(String departureTime) {
		return this.departureTime;
	}
	
	public String getArrivalTime(String arrivalTime) {
		return this.arrivalTime;
	}
	
	public double getPrice(double price) {
		return this.price;
	}
	
	
	// Method to add new flight
	public void addFlightDetails(Connection conn) {
		
       Scanner scanner = new Scanner(System.in); // Using a single scanner for all inputs

       System.out.println("Enter flight number:");
       this.setFlightNumber(scanner.nextLine()); // Using setter

       System.out.println("Enter departure location:");
       this.setDepartureLocation(scanner.nextLine());

       System.out.println("Enter arrival location:");
       this.setArrivalLocation(scanner.nextLine());
       
       System.out.println("Enter departure time:");
       this.setDepartureTime(scanner.nextLine());
       
       System.out.println("Enter arrival time:");
       this.setArrivalTime(scanner.nextLine());
       
       System.out.println("Enter price");
       this.setPrice(scanner.nextDouble());
       
       // With all the details, insert the flight into the database
    // SQL INSERT statement with placeholders for values to be bound to parameters
       String sql = "INSERT INTO flights (flightNumber, departureLocation, arrivalLocation, departureTime, arrivalTime, price) VALUES (?, ?, ?, ?, ?, ?)";

       // Auto-closeable try block to manage resources
       try (PreparedStatement preparedStmt = conn.prepareStatement(sql)) {
           // Bind values to the parameters of the prepared statement in the specified order
           preparedStmt.setString(1, this.flightNumber);
           preparedStmt.setString(2, this.departureLocation);
           preparedStmt.setString(3, this.arrivalLocation);
           preparedStmt.setString(4, this.departureTime);
           preparedStmt.setString(5, this.arrivalTime);
           preparedStmt.setDouble(6, this.price); // Assuming price is a string as specified in the table, else use setDouble if it's a numeric value

           // Execute the SQL statement and retrieve the number of rows affected
           int rowsAffected = preparedStmt.executeUpdate();

           // Check the number of inserted rows
           if (rowsAffected > 0) {
               System.out.println("Flight added successfully!");
           } else {
               System.out.println("No flight was added.");
           }
       } catch (SQLException e) {
           // Handle possible SQLExceptions here
           System.out.println("An error occurred while attempting to add the flight.");
           e.printStackTrace();
       }
	}
}
























