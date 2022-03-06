package sait.bms.problemdomain;

public class PaperBack extends Book {
	private char genre;
	private int year;
	private String authors;

	public PaperBack() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PaperBack(long iSBN, String callNumber, int numberAvailable, int totalAmount, String title) {
		super(iSBN, callNumber, numberAvailable, totalAmount, title);
		// TODO Auto-generated constructor stub
	}

	public PaperBack(long iSBN, String callNumber, int numberAvailable, int totalAmount, String title, char genre,
			int year, String authors) {
		super(iSBN, callNumber, numberAvailable, totalAmount, title);
		this.genre = genre;
		this.year = year;
		this.authors = authors;
	}

	public char getGenre() {
		return genre;
	}

	public void setGenre(char genre) {
		this.genre = genre;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	@Override
	public String toString() {
		return "PaperBack [genre=" + genre + ", year=" + year + ", authors=" + authors + ", getISBN()=" + getISBN()
				+ ", getCallNumber()=" + getCallNumber() + ", getNumberAvailable()=" + getNumberAvailable()
				+ ", getTotalAmount()=" + getTotalAmount() + ", getTitle()=" + getTitle() + "]";
	}

}
