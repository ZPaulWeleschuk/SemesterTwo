package sait.frs.application;

import java.io.FileNotFoundException;

import sait.frs.gui.*;

/**
 * Application driver.
 * 
 */
public class AppDriver {

	/**
	 * Entry point to Java application.
	 * @param args passes in the main method
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		MainWindow mainWindow = new MainWindow();
		mainWindow.display();
	}

}