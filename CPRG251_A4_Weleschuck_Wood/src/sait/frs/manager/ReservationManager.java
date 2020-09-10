package sait.frs.manager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

import sait.frs.exception.InvalidFlightCodeException;
import sait.frs.exception.NoMoreSeatsException;
import sait.frs.problemdomain.Flight;
import sait.frs.problemdomain.Reservation;


public class ReservationManager extends Reservation {
	private ArrayList<Reservation> reservations = new ArrayList<Reservation>();
	Scanner scan = new Scanner(System.in);

	public ReservationManager() {
		populateFromBinary();
	}

	public Reservation makeReservation(Flight flight, String name, String citizenship) throws IOException, InvalidFlightCodeException {
		// add try catch for null or empty name and citiznship
		// add try cathc to see if flight has seats availble
		String resCode = generateReservationCode(flight);
		Reservation r1 = new Reservation(flight, name, citizenship, resCode);
		
		reservations.add(r1);
		persist();
		return r1;

	}

	public ArrayList<Reservation> findReservations(String code, String airline, String name) {
		ArrayList<Reservation> foundReservations = new ArrayList<>();
		for (Reservation r1 : reservations) { // for some reason this is an empty array
			//System.out.println(r1.getCode());
			//System.out.println(r1.getAirline());
			//System.out.println(r1.getName());
			if (r1.getCode().equals(code) || 
			r1.getAirline().equals(airline) || 
			r1.getName().equals(name)) {
				System.out.println("finReservations method found a match and added to array list");
						foundReservations.add(r1);
			}
			
			
			/**if (r1.getCode().equals(code)) {
				if (r1.getAirline().equals(airline)) {
					if (r1.getName().equals(name)) {
						foundReservations.add(r1);
						
					}
				}

			}
		}*/
		
	}
		return foundReservations;
	}

	public Reservation findReservationByCode(String code) {
		Reservation returnCode = new Reservation();
		for (Reservation r1 : reservations) {
			if (r1.getCode().equals(code)) {
				returnCode = r1;
			}
		}
		return returnCode; 
	}

	public void persist() throws IOException {
		RandomAccessFile randomFile = new RandomAccessFile("./res/reservations.dat", "rw");
		//add seek(randomFile.lenght())
		long position = 0;
		//randomFile.seek(position); // overwrite the whole file
		position = randomFile.getFilePointer();
		for (Reservation r1 : reservations) { // not sure if this will make one big line or multiple. . .
			randomFile.seek(position);
			randomFile.writeUTF(r1.getName());
			 for (int i = 0;i <20-r1.getName().length(); i++ ) {
				 randomFile.writeByte(20);
			 }
			randomFile.writeUTF(r1.getCitizenship());
			 for (int i = 0;i <20-r1.getCitizenship().length(); i++ ) {
				 randomFile.writeByte(20);
			 }
			randomFile.writeUTF(r1.getFlightCode());
			 for (int i = 0;i <20-r1.getFlightCode().length(); i++ ) {
				 randomFile.writeByte(20);
			 }
			randomFile.writeUTF(r1.getCode());
			 for (int i = 0;i <20-r1.getCode().length(); i++ ) {
				 randomFile.writeByte(20);
			 }
			randomFile.writeUTF(r1.getAirline());
			 for (int i = 0;i <20-r1.getAirline().length(); i++ ) {
				 randomFile.writeByte(20);
			 }
			randomFile.writeUTF(String.valueOf(r1.getCost()));
			 for (int i = 0;i <20-String.valueOf(r1.getCost()).length(); i++ ) {
				 randomFile.writeByte(20);
			 }
			randomFile.writeUTF(String.valueOf(r1.isActive()));
			 for (int i = 0;i <20-String.valueOf(r1.isActive()).length(); i++ ) {
				 randomFile.writeByte(20);
			 }
			position = randomFile.getFilePointer();
		}
		System.out.println("Persist method: saved arraylist to random file");
	}

	private int getAvailableSeats(Flight flight) throws NoMoreSeatsException {
		int availableSeats = flight.getSeats() - 1;
		if(availableSeats > 0) {
			return availableSeats;
		}
		else {
			throw new NoMoreSeatsException();
		}
	}

	private String generateReservationCode(Flight flight) {
		String code;
		if (flight.isDomestic()) {
			code = "D";
		} else {
			code = "I";
		}
		code = code + (int)(Math.random() * (10000 - 1000 + 1) + 1000); // fix this
		return code;
	}

	public void populateFromBinary() {
		final double record = 154;
		String code = "";
		String flightCode = "";
		String airline = "";
		String name = "";
		String citizenship = "";
		double cost = 0.0;
		boolean active = true;

		try {
			RandomAccessFile randomFile = new RandomAccessFile("./res/reservations.dat", "rw");
			long fileSize = randomFile.length();
			randomFile.seek(0);
			double NumRecords = fileSize / record;
			for (int j = 0; j < NumRecords; j++) {

				name = randomFile.readUTF();
				for (int i = 0; i < 20 - name.length(); i++) {
					randomFile.readByte();
				}
				citizenship = randomFile.readUTF();
				for (int i = 0; i < 20 - citizenship.length(); i++) {
					randomFile.readByte();
				}
				flightCode = randomFile.readUTF();
				for (int i = 0; i < 20 - flightCode.length(); i++) {
					randomFile.readByte();
				}
				code = randomFile.readUTF();
				for (int i = 0; i < 20 - code.length(); i++) {
					randomFile.readByte();
				}
				airline = randomFile.readUTF();
				for (int i = 0; i < 20 - airline.length(); i++) {
					randomFile.readByte();
				}
				cost = Double.parseDouble(randomFile.readUTF());
				for (int i = 0; i < 20 - String.valueOf(cost).length(); i++) {
					randomFile.readByte();
				}
				active = Boolean.parseBoolean(randomFile.readUTF());
				for (int i = 0; i < 20 - String.valueOf(active).length(); i++) {
					randomFile.readByte();
				}
				Reservation r1 = new Reservation(flightCode, name, citizenship, code, airline, cost, active);
				reservations.add(r1);
				System.out.println("populateFromBinary method: random file read and added to arraylist");
			}
			randomFile.close();

		} catch (java.io.IOException e) {
			System.out.println("IO exception");
			e.getMessage();
		}
	}

}
