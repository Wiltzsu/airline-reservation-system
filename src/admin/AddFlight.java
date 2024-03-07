package admin;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class AddFlight {
	
	// Attributes for AddFlight
	private String flightNumber;
	private String departureLocation;
	private String arrivalLocation;
	private Date departureTime;
	private Date arrivalTime;
	private BigDecimal price;
	
	// Constructor
	public AddFlight() 
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
		
	public void setDepartureTime(java.util.Date date) {
	    if (date != null) {
	        this.departureTime = new Date(date.getTime()); // Correctly convert to java.sql.Date
	    } else {
	        this.departureTime = null;
	    }
	}
	
//	In Java, java.sql.Date extends java.util.Date but is used for JDBC to handle date-only values without time, 
//	while java.util.Date includes both date and time information.
//	To fix this issue, you should convert java.util.Date to java.sql.Date properly when setting the departureTime and arrivalTime. 
//	This is done by using the getTime() method of java.util.Date to get the milliseconds value and then constructing a new java.sql.Date object with that time value.	
	
	public void setArrivalTime(java.util.Date date) {
	    if (date != null) {
	        this.arrivalTime = new Date(date.getTime()); // Correctly convert to java.sql.Date
	    } else {
	        this.arrivalTime = null;
	    }
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	// Getters
	public String getFlightNumber() {
	    return this.flightNumber;
	}

	public String getDepartureLocation() {
	    return this.departureLocation;
	}

	public String getArrivalLocation() {
	    return this.arrivalLocation;
	}

	public java.sql.Date getDepartureTime() {
	    return this.departureTime;
	}

	public java.sql.Date getArrivalTime() {
	    return this.arrivalTime;
	}

	public BigDecimal getPrice() {
	    return this.price;
	}

	
	// Method to add new flight
	public void addFlightDetails(Connection conn) {
		
       Scanner scanner = new Scanner(System.in); // Using a single scanner for all inputs

       try {
           System.out.println("Enter flight number:");
           this.setFlightNumber(scanner.nextLine());

           System.out.println("Enter departure location:");
           this.setDepartureLocation(scanner.nextLine());

           System.out.println("Enter arrival location:");
           this.setArrivalLocation(scanner.nextLine());

           SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

           System.out.println("Enter departure time (yyyy-MM-dd HH:mm):");
           String departureTimeString = scanner.nextLine();
           this.setDepartureTime(dateFormat.parse(departureTimeString));

           System.out.println("Enter arrival time (yyyy-MM-dd HH:mm):");
           String arrivalTimeString = scanner.nextLine();
           this.setArrivalTime(dateFormat.parse(arrivalTimeString));

           System.out.println("Enter price:");
           this.setPrice(new BigDecimal(scanner.nextLine()));

       } catch (ParseException e) {
           System.out.println("Error parsing the date. Please enter the date in the correct format (yyyy-MM-dd HH:mm).");
       } catch (NumberFormatException e) {
           System.out.println("Error parsing the price. Please enter a valid number.");
       } catch (Exception e) {
           System.out.println("Error reading input: " + e.getMessage());
       }
       // SQL INSERT statement with placeholders for values to be bound to parameters
       String sql = "INSERT INTO flights (flightNumber, departureLocation, arrivalLocation, departureTime, arrivalTime, price) VALUES (?, ?, ?, ?, ?, ?)";

       // Auto-closeable try block to manage resources
       try (PreparedStatement preparedStmt = conn.prepareStatement(sql)) {
           // Bind values to the parameters of the prepared statement in the specified order
           preparedStmt.setString(1, this.flightNumber);
           preparedStmt.setString(2, this.departureLocation);
           preparedStmt.setString(3, this.arrivalLocation);
           preparedStmt.setDate(4, this.departureTime);
           preparedStmt.setDate(5, this.arrivalTime);
           preparedStmt.setBigDecimal(6, this.price); // Assuming price is a string as specified in the table, else use setDouble if it's a numeric value

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
























