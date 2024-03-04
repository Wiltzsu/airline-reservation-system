package admin;
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
	public void addFlightDetails() {
		
       Scanner scanner = new Scanner(System.in); // Using a single scanner for all inputs

       System.out.println("Enter flight number:");
       this.setFlightNumber(scanner.nextLine()); // Correctly using the setter

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
	}
}
























