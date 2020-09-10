package sait.mms.managers;

import java.sql.Driver;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import contracts.DatabaseDriver;
import drivers.MariaDBDriver;

/**
 * The MovieManagement system contains methods that display the menu, create new movie record, find all movies in a year, display 
 * a list of random movies and delete a movie from the system
 * @author Joel Wood
 * @author Zennon Weleschuck
 * @version August 6 2020
 *
 */
public class MovieManagementSystem {
	// remember SQL counts starting from ONE!
	static Scanner in = new Scanner(System.in);
	static DatabaseDriver driver = new MariaDBDriver();
	
	/**
	 * This method displays the Movie Database Selection menu and pass's to the methods that perform actions
	 * @throws SQLException SQL is used to manipulate the database
	 */
	public static void displayMenu() throws SQLException {

	String input;
		int selection = 9;
		while (selection != 0) {
			driver.connect();
			System.out.println("Welcome to the Movie Database Selection Menu:");
			System.out.println("Enter 1 to create a new movie record");
			System.out.println("Enter 2 to find all the moives release in a specified year");
			System.out.println("Enter 3 for a random list of movies");
			System.out.println("Enter 4 to delete a movie from the database");
			System.out.println("Enter 0 to exit the program");

			input = in.next();
			if (Character.isDigit(input.charAt(0)) && input.length() == 1) {
				selection = Integer.parseInt(input);
				if (selection < 0 || selection > 4)
					System.out.println("invalid option");
			} else {
				System.out.println("Invalid option, enter a number beteween 0-4");
			}

			if (selection == 1)
				addMovie();
			else if (selection == 2)
				printMoviesInYear();
			else if (selection == 3)
				printRandomMovies();
			else if (selection == 4)
				deleteMovie();
			else if (selection == 0);
				break;
		}
	}
	/**
	 * This method will make add a movie into the database at the end using user input for title, duration and year
	 * @throws SQLException SQL is used to manipulate the database
	 */
	public static void addMovie() throws SQLException {
		int id = 0;
		int duration;
		String title = "";
		int year;
		int rows;
		int size;
		
		ResultSet rs = driver.get("select * from movies");
		if (rs != null) 
		{
		  rs.last();    // moves cursor to the last row
		  id = (rs.getRow() + 1); // get row id 
		}
		System.out.print("Enter movie title:");
		in.nextLine();
		title = in.nextLine();
		System.out.print("Enter movie duration: ");
		duration = in.nextInt();
		System.out.print("Enter movie release year: ");
		year = in.nextInt();
		 
		PreparedStatement ps = driver.getPreparedStatment
				("insert into movies values (?,?,?,?)");
		ps.setInt(1,id);
		ps.setInt(2, duration);
		ps.setString(3, title);
		ps.setInt(4, year);
		
		rows = ps.executeUpdate();
		if (rows > 0)
			System.out.println("movie is added to database");
		else
			System.out.print("failed to add movie");
		
		System.out.println();
		rs.close();
		displayMenu();
	}

	/**
	 * This method prints all movies in a year specified by the user
	 * @throws SQLException SQL is used to manipulate the database
	 */
	public static void printMoviesInYear() throws SQLException {
		int id;
		int duration;
		String title;
		int year = 0;
		String input;
		//int rows = 0;
		
		System.out.print("Enter the release year: ");
		input = in.next();
		if (Integer.parseInt(input) > 0 &&  Integer.parseInt(input) < 3000) {
			year = Integer.parseInt(input);	
		} else {
			System.out.println("Invalid number input, enter a number between 0 and 3000");
		}
		
		ResultSet rs = driver.get("select * from movies where year =" + year);
		while (rs.next()) {
			id = rs.getInt(1);
			duration = rs.getInt(2);
			title = rs.getString(3);
			year = rs.getInt(4);
			System.out.println("ID: " + id + ", Duration: " + duration + ", Title: " + title + ", year: " + year);
		}
		
		System.out.println();
		rs.close();
		displayMenu();
		
	}

	/**
	 * This method prints 10 random movies from the database
	 * @throws SQLException SQL is used to manipulate the database
	 */
	public static void printRandomMovies() throws SQLException {
		int id;
		int duration;
		String title;
		int year;
					
		ResultSet rs = driver.get("select * from movies Order by Rand() Limit 10"); 
		while(rs.next()) {
			id = rs.getInt(1);
			duration = rs.getInt(2);
			title = rs.getString(3);
			year = rs.getInt(4);
			System.out.println("ID: " + id + ", Duration: " + duration + ", Title: " + title + ", year: " + year);
					
				}
		
		System.out.println();
		rs.close();
		displayMenu();
	}
	
	/**
	 * This method deletes a movie based on the ID specified by the user
	 * @throws SQLException SQL is used to manipulate the database
	 */
	public static void deleteMovie() throws SQLException {
		int id;
		int rows = 0;
		
		System.out.println("Enter movie ID: ");
		id = in.nextInt();
		if(id == (int)id) {
			rows = driver.update("delete from movies where id = " + id);
			
			if (rows > 0)
				System.out.println("movie has been deleted from database");
			else
				System.out.print("failed to delete movie");
			}
		else {
			System.out.println("Please enter an integer");
		}
	System.out.println();
	displayMenu();
	}
}
