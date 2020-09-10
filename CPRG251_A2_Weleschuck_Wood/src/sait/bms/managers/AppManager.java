package sait.bms.managers;

import java.io.*;
import java.util.*;

import sait.bms.problemdomain.Book;
import sait.bms.problemdomain.BookChildrens;
import sait.bms.problemdomain.BookCook;
import sait.bms.problemdomain.BookPaperBack;
import sait.bms.problemdomain.BookPeriodical;

/**
 * 
 * Collection of methods used by the MainDriver Application
 * 
 * @author Zennon and Joel
 */
// branch 'master' of https://github.com/joeljwood/CPRG251_A2_Weleschuck_Wood_Working.git
public class AppManager {
	private static final String PATH = "res/books.txt";
	private ArrayList<Book> bookList = new ArrayList<>();
	Scanner input = new Scanner(System.in);
	private String checkIsbn;
	private int lastIsbn;
	private long isbn;
	private String callNumber;
	private int available;
	private int total;
	private String title;
	private String author;
	private char format;
	private String publisher;
	private char diet;
	private int year;
	private char genre;
	private char frequency;
	private Book b;

	/**
	 * default constructor for appManager class, calls different methods in the appManager to build the application
	 * @throws IOException 
	 */
	public void AppManager() throws IOException {
		int option = 0;
		
		this.bookList = new ArrayList<>();
		this.LoadBooks();
		
		while (option != 5) {
			this.BooksMenu();
			option = input.nextInt();
			if (option == 1) {
				this.CheckoutBook();
			} else if (option == 2) {
				this.titleSearch();
			} else if (option == 3) {
				this.SpecificType();
			} else if (option == 4) {
				this.randomBooks();
			} else if (option == 5) {
				this.SaveBooks();
				 System.exit(0);
			} else {
				System.out.println("invaild input!");
			}
			
		}
		input.close();
			
		}

	/**
	 * This method reads the books.txt file and determines what what type of
	 * subclass it belongs to and loads the attributes accordingly
	 * 
	 * @throws FileNotFoundException used for importing books.txt file
	 */
	public void LoadBooks() throws FileNotFoundException {
		// FileInputStream isFile = new FileInputStream(PATH);
		// Scanner inFile = new Scanner(isFile, "UTF-8");
		Scanner inFile = new Scanner(new File(PATH));
		while (inFile.hasNextLine()) {
			String line = inFile.nextLine();
			String[] parts = line.split(";");

			checkIsbn = parts[0];
			lastIsbn = Integer.parseInt(checkIsbn.substring(checkIsbn.length() - 1)); // gets last digit of ISBN
			switch (lastIsbn) {
			case 0:
			case 1: // Children's books
				isbn = Long.parseLong(checkIsbn);
				callNumber = parts[1];
				available = Integer.parseInt(parts[2]);
				total = Integer.parseInt(parts[3]);
				title = parts[4];
				author = parts[5];
				format = parts[6].charAt(0);

				b = new BookChildrens(isbn, callNumber, available, total, title, author, format);
				bookList.add(b);
				break;

			case 2:
			case 3: // Cook books
				isbn = Long.parseLong(checkIsbn);
				callNumber = parts[1];
				available = Integer.parseInt(parts[2]);
				total = Integer.parseInt(parts[3]);
				title = parts[4];
				publisher = parts[5];
				diet = parts[6].charAt(0);

				b = new BookCook(isbn, callNumber, available, total, title, publisher, diet);
				bookList.add(b);
				break;

			case 4:
			case 5:
			case 6:
			case 7: // Paperback books
				isbn = Long.parseLong(checkIsbn);
				callNumber = parts[1];
				available = Integer.parseInt(parts[2]);
				total = Integer.parseInt(parts[3]);
				title = parts[4];
				author = parts[5];
				year = Integer.parseInt(parts[6]);
				genre = parts[7].charAt(0);

				b = new BookPaperBack(isbn, callNumber, available, total, title, author, year, genre);
				bookList.add(b);
				break;

			case 8:
			case 9: // Periodicals
				isbn = Long.parseLong(checkIsbn);
				callNumber = parts[1];
				available = Integer.parseInt(parts[2]);
				total = Integer.parseInt(parts[3]);
				title = parts[4];
				frequency = parts[5].charAt(0);

				b = new BookPeriodical(isbn, callNumber, available, total, title, frequency);
				bookList.add(b);
				break;
			}
		}

		/*
		 * for (Book b1 : bookList) {// for testing System.out.println(b1.toString());//
		 * for testing }
		 */

		/*
		 * for (Book b1 : bookList) {// for testing output of loadBooks
		 * System.out.println(b1.toString());// for testing output of loadBooks }
		 */

		inFile.close();
	}

	/**
	 * this method displays the main menu when choosing books
	 */
	public void BooksMenu() {
		System.out.println("Welcome in ABC Book Company: How May We Assist You?");
		System.out.println("1	Checkout Book");
		System.out.println("2	Find Books by Title");
		System.out.println("3	Display Books by Type");
		System.out.println("4	Produce Random Book List");
		System.out.println("5	Save and Exit");
		System.out.println();
		System.out.print("Enter an option: ");	
	}

	/**
	 * this method allows you to check out a book
	 */
	public void CheckoutBook() {

		System.out.print("Enter the ISBN of book: ");
		try {
			long searchIsbn = input.nextLong();
			int lastDigit = (int) (searchIsbn % 10);
			for (Book b1 : bookList) {

				if ((b1.getIsbn() == searchIsbn) && (lastDigit == 8 || lastDigit == 9)) {
					System.out.println();
					System.out.print("This ISBN is for a periodical, Periodicals cannot be checked out");
					System.out.println();
				} else if ((b1.getIsbn() == searchIsbn) && (b1.getAvailable() > 0)) {
					System.out.println();
					System.out.println("The book \"" + b1.getTitle() + "\" has been checked out.");
					System.out.println("It can be located using call number " + b1.getCallNumber());
					System.out.println();
					b1.setAvailable(b1.getAvailable() - 1);

				} else if ((b1.getIsbn() == searchIsbn) && (b1.getAvailable() == 0)) {
					System.out.println();
					System.out.print("This book is unavailable");
					System.out.println();
				}
			}
		} catch (Exception e) {
			System.out.print("Input accepts long type only");
		}
	}

	/**
	 * this method allows the user to do case insensitive search for stings in the
	 * titles
	 */
	public void titleSearch() {
		System.out.print("Enter title to search for: ");
		String query = (input.next());
		System.out.println("Matching Results:");
		for (Book b1 : bookList) {
			if (b1.getTitle().toLowerCase().contains(query.toLowerCase())) { // case insensitive title search
				System.out.println(b1.toString());
			}
		}
	}
/**
 * This method displays books in a specific category and format, diet, genre, or frequency (depending on the type of book) 
 * this will narrow down the list further
 */
	public void SpecificType() {
		int type;
		System.out.println("#	Type");
		System.out.println("1	Children's Books");
		System.out.println("2	Cookbooks");
		System.out.println("3	Paperbacks");
		System.out.println("4	Periodicals");
		System.out.println();
		System.out.print("Enter type of book: ");
		try {
		type = input.nextInt();
		if(type == 1) {
			System.out.print("Enter a Format (P for Picture book, E for Early Readers, and C for Chapter book): ");
			System.out.println();
			String formatString = input.next();
			char format = formatString.toUpperCase().charAt(0);
			for (Book b1 : bookList) {
				int lastDigit = (int)(b1.getIsbn() % 10);
				if((lastDigit == 0 || lastDigit == 1) && ((BookChildrens) b1).getFormat() == format){
					System.out.println(((BookChildrens) b1).toString());
					}
				}
			}
		else if(type ==2) {
			System.out.print("Enter a Diet (D for Diabetic, V for Vegetarian, G for Gluten-free, I for International or N for None): ");
			String dietString = input.next();
			char diet = dietString.toUpperCase().charAt(0);
			for (Book b1 : bookList) {
				int lastDigit = (int)(b1.getIsbn() % 10);
				if((lastDigit == 2 || lastDigit == 3) && ((BookCook) b1).getDiet() == diet){
					System.out.println(((BookCook) b1).toString());
					}
				}
			}
		else if(type ==3) {
			System.out.print("Enter a Genre (A for Adventure, D for Drama, E for Education, C for Classic, "
					+ "F for Fantasy or S for Science Fiction): ");
			String genreString = input.next();
			char genre = genreString.toUpperCase().charAt(0);
			for (Book b1 : bookList) {
				int lastDigit = (int)(b1.getIsbn() % 10);
				if((lastDigit == 4 || lastDigit == 5 || lastDigit == 6 || lastDigit == 7) && ((BookPaperBack) b1).getGenre() == genre){
					System.out.println(((BookPaperBack) b1).toString());
					}
				}
		}
		else if(type == 4) {
			//Daily, Weekly, Monthly, Bimonthly, and Quarterly. 
			System.out.print("Enter a Frequency (D for Daily, W for Weekly, M for Monthly, B for Bimonthly or Q for Quarterly): ");
			String frequencyString = input.next();
			char frequency = frequencyString.toUpperCase().charAt(0);
			for (Book b1 : bookList) {
				int lastDigit = (int)(b1.getIsbn() % 10);
				if((lastDigit == 8 || lastDigit == 9) && ((BookPeriodical) b1).getFrequency() == frequency){
					System.out.println(((BookPeriodical) b1).toString());
					}
				}
		}
		else {
			System.out.print("This is not a valid type");
		}
		}catch(Exception e) {
			System.out.print("Input accepts integers only");
		}
		
	}
	/**
	 * this method returns a specified number of random books from the list of books
	 */
	public void randomBooks() {
		System.out.print("Enter the number of random books to display: ");
		try {
			int randomBookAmount = input.nextInt();
			Collections.shuffle(bookList);
			System.out.println("Random Results:");
			for (int i = 0; i < randomBookAmount; i++) {
				System.out.println(bookList.get(i));
			}
		} catch (Exception e) {
			System.out.println("Input accepts integers only");

		}

	}
	/**
	 * This method takes the Arraylist of books and prints it out to the books.txt file
	 * @throws IOException
	 */
	public void SaveBooks() throws IOException {
		File newBooks = new File(PATH);
		PrintWriter output = new PrintWriter(newBooks);
		
		for(Book b1: bookList) {
			output.print(b1.toPrint());
		}
		
		output.close();
	}

}
