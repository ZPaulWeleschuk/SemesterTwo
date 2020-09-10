package sait.bms.problemdomain;

/**
 * This subclass extends the books superclass and is for cook book objects
 * 
 * @author Zennon and Joel
 *
 */
public class BookCook extends Book {
	private String publisher;
	private char diet;

	/**
	 * Constructor for cook books
	 * 
	 * @param isbn       unique number for each book
	 * @param callNumber used to sort physical books
	 * @param available  number of book to take out
	 * @param total      number of book library owns
	 * @param title      of book
	 * @param publisher  of cook book
	 * @param diet       type of cook book
	 */
	public BookCook(long isbn, String callNumber, int available, int total, String title, String publisher, char diet) {
		super(isbn, callNumber, available, total, title);
		this.publisher = publisher;
		this.diet = diet;
	}

	/**
	 * 
	 * @return the publisher of cook book
	 */
	public String getPublisher() {
		return publisher;
	}

	/**
	 * 
	 * @param publisher of cook book
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	/**
	 * 
	 * @return the short format type of diet in cook book
	 */
	public char getDiet() {
		return diet;
	}

	/**
	 * 
	 * @param diet set the type of cook book
	 */
	public void setDiet(char diet) {
		this.diet = diet;
	}

	/**
	 * converts the short format of diet type to the long written out version
	 * 
	 * @return the long format version of diet type
	 */
	public String dietLong() {
		String tempDietLong = "None";
		if (diet == 'D') {
			tempDietLong = "Diabetic";
		} else if (diet == 'V') {
			tempDietLong = "Vegetarian";
		} else if (diet == 'G') {
			tempDietLong = "Gluten-Free";
		} else if (diet == 'I') {
			tempDietLong = "International";
		}
		return tempDietLong;
	}
	/**
	 * print formatted cook book for the books.txt file
	 *@return formated string of the cook book object
	 */
	@Override
	public String toPrint() {
		return String.format("%d%1s %2s%1s %d%1s %d%1s %2s%1s %2s%1s %c%n", getIsbn(), ";",
				getCallNumber(), ";", getAvailable(), ";", getTotal(), ";", getTitle(),
				";", getPublisher(), ";", getDiet());
	}

	/**
	 * for printing a formated output of cook book objects
	 * @return formated string of the cook book object
	 */
	@Override
	public String toString() {
		return String.format("%-15s%d\n%-15s%s\n%-15s%d\n%-15s%d\n%-15s%s\n%-15s%s\n%-15s%s\n", "ISBN:", getIsbn(),
				"Call Number:", getCallNumber(), "Available:", getAvailable(), "Total:", getTotal(), "Title:",
				getTitle(), "Publisher:", getPublisher(), "Diet", dietLong());
	}

}
