package sait.mms.application;

import java.sql.ResultSet;

import java.sql.SQLException;

import contracts.DatabaseDriver;

import java.sql.*;
import drivers.*;
import drivers.MariaDBDriver;
import sait.mms.managers.MovieManagementSystem;

/**
 * This is the main driver for the application calls the MovieManagementSystemClass
 * @author Joel Wood
 * @author Zennon Weleschuck
 * @version August 6 2020
 */

public class appDriver {

	public static void main(String[] args) throws SQLException {
		MovieManagementSystem.displayMenu();
		
		
		//uncomment this out to test connection to DB
	
		/*DatabaseDriver driver = new MariaDBDriver();
		
		try { 
			driver.connect();
			
			System.out.println("Connected!");
			
			ResultSet rs = driver.get("SELECT 'Hello from the other side!'");
			
			boolean hasRow = rs.next();
			
			if (hasRow) {
				String column1 = rs.getString(1);
				System.out.println(column1);
			} else {
				System.out.println("Ooops! No rows were found.");
			}
			
			driver.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}*/

	}

}
