package sait.bms.problemdomain;

/**
 * subclass for periodical, extends book superclass
 * 
 * @author Zennon and Joel
 *
 */
public class BookPeriodical extends Book {

	private char frequency;

	/**
	 * constructor for periodical subclass
	 * 
	 * @param isbn       unique number for each book
	 * @param callNumber used to sort physical books
	 * @param available  number of book to take out
	 * @param total      number of book library owns
	 * @param title      of book
	 * @param frequency  of publishing for periodicals
	 */
	public BookPeriodical(long isbn, String callNumber, int available, int total, String title, char frequency) {
		super(isbn, callNumber, available, total, title);
		this.frequency = frequency;
	}

	/**
	 * 
	 * @return the frequency of periodicals
	 */

	public char getFrequency() {
		return frequency;
	}

	/**
	 * 
	 * @param diet set the short format frequency for periodicals
	 */
	public void setFrequency(char frequency) {
		this.frequency = frequency;
	}

	/**
	 * converts the short format of frequency to the long spelled out version
	 * 
	 * @return the long version of frequency
	 */
	public String frequencyLong() {
		String tempFrequencyLong = "None";
		if (frequency == 'D') {
			tempFrequencyLong = "Daily";
		} else if (frequency == 'W') {
			tempFrequencyLong = "Weekly";
		} else if (frequency == 'M') {
			tempFrequencyLong = "Monthly";
		} else if (frequency == 'B') {
			tempFrequencyLong = "Bimonthly";
		} else if (frequency == 'Q') {
			tempFrequencyLong = "Quarterly";
		}
		return tempFrequencyLong;
	}
	/**
	 * print formatted children's book for the books.txt file
	 * @return formated string of the periodical object
	 */
	@Override
	public String toPrint() {
		return String.format("%d%1s %2s%1s %d%1s %d%1s %2s%1s %c%n", getIsbn(), ";",
				getCallNumber(), ";", getAvailable(), ";", getTotal(), ";", getTitle(),
			    ";", getFrequency());
	}

	/**
	 * prints formatted output for periodical objects
	 * @return formated string of the periodical object
	 */
	@Override
	public String toString() {
		return String.format("%-15s%d\n%-15s%s\n%-15s%d\n%-15s%d\n%-15s%s\n%-15s%s\n", "ISBN:", getIsbn(),
				"Call Number:", getCallNumber(), "Available:", getAvailable(), "Total:", getTotal(), "Title:",
				getTitle(), "Frequency", frequencyLong());
	}
}
