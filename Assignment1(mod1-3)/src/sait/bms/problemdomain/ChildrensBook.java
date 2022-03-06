package sait.bms.problemdomain;

public class ChildrensBook extends Book {
	private String authors;
	private char format;
	/**
	 * 
	 */
	public ChildrensBook() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param iSBN
	 * @param callNumber
	 * @param numberAvailable
	 * @param totalAmount
	 * @param title
	 * @param authors
	 * @param format
	 */
	public ChildrensBook(long iSBN, String callNumber, int numberAvailable, int totalAmount, String title,
			String authors, char format) {
		super(iSBN, callNumber, numberAvailable, totalAmount, title);
		this.authors = authors;
		this.format = format;
	}
	public String getAuthors() {
		return authors;
	}
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	public char getFormat() {
		return format;
	}
	public void setFormat(char format) {
		this.format = format;
	}
	@Override
	public String toString() {
		return "ChildrensBook [authors=" + authors + ", format=" + format + ", getISBN()=" + getISBN()
				+ ", getCallNumber()=" + getCallNumber() + ", getNumberAvailable()=" + getNumberAvailable()
				+ ", getTotalAmount()=" + getTotalAmount() + ", getTitle()=" + getTitle() + "]";
	}
	
	
	


	
	
	
	
}
