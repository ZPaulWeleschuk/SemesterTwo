package sait.bms.problemdomain;

/**
 * this subclass extend the super book class for paperback books
 * @author Zennon and Joel
 *
 */
public class BookPaperBack extends Book {
	private String author;
	private int year;
	private char genre;
	
	/**
	 * 
	 * @return the  author of paper back books
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * 
	 * @param author sets the author of paperback
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * 
	 * @return the year the book was published
	 */
	public int getYear() {
		return year;
	}
	
	/**
	 * 
	 * @param year set the year the book was published
	 */
	public void setYear(int year) {
		this.year = year;
	}
	/**
	 * 
	 * @return the short format version of the genre of the paper back book
	 */
	public char getGenre() {
		return genre;
	}
	/**
	 * 
	 * @param genre set the genre in short format for the paper back book
	 */
	public void setGenre(char genre) {
		this.genre = genre;
	}
	
	/**
	 * constructor for paperback book
	 * @param isbn unique number for each book
	 * @param callNumber used to sort physical books
	 * @param available number of book to take out
	 * @param total number of book library owns
	 * @param title of book
	 * @param author of paper back book
	 * @param year paper book was published
	 * @param genre short format of paper back book
	 */
	public BookPaperBack(long isbn, String callNumber, int available, int total, String title, String author, int year, char genre) {
		super(isbn, callNumber, available, total, title);
		this.author = author;
		this.year = year;
		this.genre = genre;
	}
	
	/**
	 * converts the short format version of genre to the long written out format
	 * @return the long format of genre
	 */
	public String GenreLong() {
		String tempGenreLong = "None";
		if (genre == 'A') {
			tempGenreLong = "Adventure";
		} else if (genre == 'D') {
			tempGenreLong = "Drama";
		} else if (genre == 'E') {
			tempGenreLong = "Education";
		} else if (genre == 'C') {
			tempGenreLong = "Classic";
		}else if (genre == 'F') {
			tempGenreLong = "Fantasy";
		}else if (genre == 'S') {
			tempGenreLong = "Science Fiction";;
		}return tempGenreLong;
	}
	/**
	 * print formatted children's book for the books.txt file
	 * @return formated string of the paperback book object
	 */
	@Override
	public String toPrint() {
		return String.format("%d%1s %2s%1s %d%1s %d%1s %2s%1s %2s%1s %d%1s %c%n", getIsbn(), ";",
				getCallNumber(), ";", getAvailable(), ";", getTotal(), ";", getTitle(),
				";", getAuthor(), ";", getYear(), ";", getGenre());
	}
	
	/**
	 * format print statement for paperback book objects
	 * @return formated string of the paperback book object
	 */
	@Override
	public String toString() {
		return String.format("%-15s%d\n%-15s%s\n%-15s%d\n%-15s%d\n%-15s%s\n%-15s%s\n%-15s%d\n%-15s%s\n", "ISBN:", getIsbn(),
				"Call Number:", getCallNumber(), "Available:", getAvailable(), "Total:", getTotal(), "Title:",
				getTitle(), "Author:", getAuthor(), "year:", getYear(), "Genre:", GenreLong());
	}

	
}
