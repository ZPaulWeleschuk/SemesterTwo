package sait.frs.problemdomain;

import sait.frs.exception.InvalidFlightCodeException;

/**
 * 
 * @author Joel Wood and Zennon Weleschuck
 *@version 1.0, July 6 2020
 */

public class Flight {
	
	/**
	 * The fields for the flight class
	 */
	private String code;
	private String airlineName;
	private String from;
	private String to;
	private String weekday;
	private String time;
	private int seats;
	private double costPerSeat;
	private boolean isDomestic;
	
	/**
	 * Constructor for the Flight class, takes in all variables
	 * @param code airline code
	 * @param airlineName name of the airline
	 * @param from where the user is coming from
	 * @param to where the user is flying to
	 * @param weekday day of the week
	 * @param time time they would like to leave
	 * @param seats the number of seats
	 * @param costPerSeat the cost for each seat
	 */
	public Flight() {
	}
	public Flight(String code, String from, String to, String weekday, String time, int seats,
			double costPerSeat) {
		this.code = code;
		this.from = from;
		this.to = to;
		this.weekday = weekday;
		this.time = time;
		this.seats = seats;
		this.costPerSeat = costPerSeat;
	}
	
	/**
	 * Gets the flight code
	 * @return code returns flight code
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * Gets Airline name
	 * @return airlineName Name of airline
	 * @throws InvalidFlightCodeException 
	 */
	public String getAirline() throws InvalidFlightCodeException {
		parseCode(code);
		return airlineName;
	}
	
	/**
	 * Gets originating airport
	 * @return from originating airport
	 */
	public String getFrom() {
		return from;
	}
	
	/**
	 * Gets the destination airport
	 * @return to destination airport
	 */
	public String getTo() {
		return to;
	}
	
	/**
	 * Gets the day of the week the flight leaves
	 * @return Week day
	 */
	public String getWeekday() {
		return weekday;
	}
	
	/**
	 * Gets the time the flight leaves (in 24 hour format)
	 * @return Time flight leaves
	 */
	public String getTime() {
		return time;
	}

	/**
	 * Gets the number of seats on the flight.
	 * @return seats Total number of seats.
	 */
	public int getSeats() {
		return seats;
	}

	/**
	 * Gets the cost per seat on the flight
	 * @return costPerSeat Cost per seat
	 */
	public double getCostPerSeat() {
		return costPerSeat;
	}
	
	/**
	 * Is this a domestic flight?
	 * @return isDomestic True if it is domestic or false if it is international.
	 */
	public boolean isDomestic() {
		switch(from){
		case ("YYC"): 
			if (to.equals("YEG") || to.equals("YUL") || to.equals("YOW") || to.equals("YYZ") || to.equals("YVR") || to.equals("YWG")) {
				isDomestic = true;
			}
			break;
		case ("YEG"):
			if (to.equals("YYC") || to.equals("YUL") || to.equals("YOW") || to.equals("YYZ") || to.equals("YVR") || to.equals("YWG")) {
				isDomestic = true;
			}
			break;
		case ("YUL"):
			if (to.equals("YYC") || to.equals("YEG") || to.equals("YOW") || to.equals("YYZ") || to.equals("YVR") || to.equals("YWG")) {
				isDomestic = true;
			}
			break;
		case ("YOW"):
			if (to.equals("YYC") || to.equals("YEG") || to.equals("YUL") || to.equals("YYZ") || to.equals("YVR") || to.equals("YWG")) {
				isDomestic = true;
			}
			break;
		case ("YYZ"):
			if (to.equals("YYC") || to.equals("YEG") || to.equals("YUL") || to.equals("YOW") || to.equals("YVR") || to.equals("YWG")) {
				isDomestic = true;
			}
			break;
		case ("YVR"):
			if (to.equals("YYC") || to.equals("YEG") || to.equals("YUL") || to.equals("YOW") || to.equals("YYZ") || to.equals("YWG")) {
				isDomestic = true;
			}
			break;
		case ("YWG"):
			if (to.equals("YYC") || to.equals("YEG") || to.equals("YUL") || to.equals("YOW") || to.equals("YYZ") || to.equals("YVR")) {
				isDomestic = true;
			}
			break;
		case ("ATL"):
			if (to.equals("ORD") || to.equals("DFW")) {
				isDomestic = true;
			}
			break;
		case ("ORD"):
			if (to.equals("ATL") || to.equals("DFW")) {
				isDomestic = true;
			}
			break;
		case ("DFW"):
			if (to.equals("ATL") || to.equals("ORD")) {
				isDomestic = true;
			}
			break;
		case("PEK"):
			if(to.equals("HKG") || to.equals("PVG")) {
				isDomestic = true;
			}
			break;
		case("HKG"):
			if(to.equals("PEK") || to.equals("PVG")) {
				isDomestic = true;
			}
			break;
		case("PVG"):
			if(to.equals("PEK") || to.equals("HKG")) {
				isDomestic = true;
			}
			break;
		default: isDomestic = false;
		}
		return isDomestic;
	}
	public void parseCode(String code) throws InvalidFlightCodeException {
		
		String codeToParse = getCode();
		
		char c1 = codeToParse.charAt(0);
		char c2 = codeToParse.charAt(1);
			if (c1 == 'O' && c2 == 'A') {
				airlineName = "Otto Airlines";
			}
			else if (c1 == 'C' && c2 == 'A') {
				airlineName = "Conned Air";
			}
			else if (c1 == 'T' && c2 == 'B') {
				airlineName = "Try a Bus Airways";
			}
			else if (c1 == 'V' && c2 == 'A') {
				airlineName = "Vertical Airways";
			}
			else {
				throw new InvalidFlightCodeException();
			}
		}
	
	@Override
	public String toString() {
		return String.format("%s%s%s%s%s%s%s%s%s%s%d%s%2.2f",getCode() , " From: ", getFrom() , " To: ", getTo(), " Day: ",
				getWeekday(), " Time: ", getTime(), " Seats: ", getSeats(), " Cost Per Seat: ", getCostPerSeat());
		
	}
	
	
	
}
