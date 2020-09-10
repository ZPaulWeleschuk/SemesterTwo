package sait.frs.problemdomain;

import java.io.RandomAccessFile;

import sait.frs.exception.InvalidFlightCodeException;
import sait.frs.problemdomain.Flight;

/**
 * This class is for the reservation object and contains its constructors,
 * accessor, and muterators
 * 
 * @author Joel Wood and Zennon Weleschuk
 * 
 *
 */
public class Reservation {
	private String code;
	private String flightCode;
	private String airline;
	private String name;
	private String citizenship;
	private double cost;
	private boolean active;

	/**
	 * no args construcrtor
	 */
	public Reservation() {

	}

	/**
	 * constructor for reservations object
	 * 
	 * @param flight      object from flight class
	 * @param name        of the customers
	 * @param citizenship country name of the customer
	 */
	public Reservation(Flight flight, String name, String citizenship) {
		this.name = name;
		this.citizenship = citizenship;
		this.flightCode = flight.getCode();
		this.code = getCode();
		this.airline = getAirline();
		this.cost = flight.getCostPerSeat();
		this.active = isActive();

	}

	/**
	 * constructor for reservation object that accepts requires all params
	 * 
	 * @param FlightCode  of the flight
	 * @param name        of customer
	 * @param citizenship country name of customer
	 * @param code        of the reservation
	 * @param airline     name
	 * @param cost        of the flight
	 * @param active      statues of the reservation
	 */
	public Reservation(String FlightCode, String name, String citizenship, String code, String airline, double cost,
			boolean active) {
		this.name = name;
		this.citizenship = citizenship;
		this.flightCode = FlightCode;
		this.code = code;
		this.airline = airline;
		this.cost = cost;
		this.active = active;
	}

	/**
	 * constructor that take also accepts the code value in the args
	 * 
	 * @param flight      object of the flight
	 * @param name        of the customer
	 * @param citizenship country of the customer
	 * @param code        of the reservation
	 * @throws InvalidFlightCodeException in case of invalid flight code
	 */
	public Reservation(Flight flight, String name, String citizenship, String code) throws InvalidFlightCodeException {
		this.name = name;
		this.citizenship = citizenship;
		this.flightCode = flight.getCode();
		this.code = code;
		this.airline = flight.getAirline();
		this.cost = flight.getCostPerSeat();
		this.active = true;
	}

	/**
	 * getter for code variable
	 * 
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * set the reservation code
	 * 
	 * @param code of the reservation
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * get the flight code
	 * 
	 * @return the code of the flight
	 */
	public String getFlightCode() {
		return flightCode;
	}

	/**
	 * get the airline name
	 * 
	 * @return the airline name
	 */
	public String getAirline() {
		return airline;
	}

	/**
	 * get the name of the customer
	 * 
	 * @return the name of the customer
	 */
	public String getName() {
		return name;
	}

	/**
	 * get the citizenship of the customer
	 * 
	 * @return the citizenship of the customer
	 */
	public String getCitizenship() {
		return citizenship;
	}

	/**
	 * get the cost of the flight
	 * 
	 * @return the cost of the flight
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * weather the reservation is active or not
	 * 
	 * @return the state of the reservation
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * set the name of the customer
	 * 
	 * @param name of the customer
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * set the customers citizenship
	 * 
	 * @param citizenship of the customer
	 */
	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	/**
	 * set weather the reservation is active or not
	 * 
	 * @param active if the reservation is active or not
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * override toString for displaying the desired information of the reservation
	 * object
	 */
	@Override
	public String toString() {
		return code;
	}

}
