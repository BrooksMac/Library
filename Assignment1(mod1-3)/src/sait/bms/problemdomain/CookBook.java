package sait.bms.problemdomain;

public class CookBook extends Book {
	private String publisher;
	private char diet;
	

	

	/**
	 * 
	 */
	public CookBook() {
		super();
	}

	/**
	 * @param iSBN
	 * @param callNumber
	 * @param numberAvailable
	 * @param totalAmount
	 * @param title
	 * @param publisher
	 * @param diet
	 */
	public CookBook(long iSBN, String callNumber, int numberAvailable, int totalAmount, String title, String publisher,
			char diet) {
		super(iSBN, callNumber, numberAvailable, totalAmount, title);
		this.diet = diet;
		this.publisher = publisher;
	}


	public char getDiet() {
		return diet;
	}

	public void setDiet(char diet) {
		this.diet = diet;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	@Override
	public String toString() {
		return "CookBook [diet=" + diet + ", publisher=" + publisher + ", getISBN()=" + getISBN() + ", getCallNumber()="
				+ getCallNumber() + ", getNumberAvailable()=" + getNumberAvailable() + ", getTotalAmount()="
				+ getTotalAmount() + ", getTitle()=" + getTitle() + "]";
	}

}
