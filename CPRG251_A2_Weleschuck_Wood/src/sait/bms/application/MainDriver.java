package sait.bms.application;

import java.io.*;
import java.util.*;
import sait.bms.managers.AppManager;

/**
 * This program manages a large collection of different types of books and
 * allows the user to check out a book by the ISBN number if its available, and
 * keeps track of the number of books available. This program also allows the
 * user to search for a book entering a snippet of the title and returns all
 * matching results. This program also allows the user to view the collection of
 * books based on the specific type of book This program will also display a
 * specified number of random books to the user.
 * 
 * 
 * This is the main method for running the program
 * 
 * @author Zennon and Joel
 *
 */
public class MainDriver {

	public static void main(String[] args) throws IOException {
		AppManager m = new AppManager();
		m.AppManager();
	}

}