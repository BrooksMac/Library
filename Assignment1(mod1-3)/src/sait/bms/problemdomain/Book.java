package sait.bms.problemdomain;

public class Book {
	private long ISBN;
	private String callNumber;
	private int numberAvailable;
	private int totalAmount;
	private String Title;

	public Book() {
		super();
	}

	public Book(long iSBN, String callNumber, int numberAvailable, int totalAmount, String title) {
		super();
		ISBN = iSBN;
		this.callNumber = callNumber;
		this.numberAvailable = numberAvailable;
		this.totalAmount = totalAmount;
		Title = title;
	}

	public long getISBN() {
		return ISBN;
	}

	public void setISBN(long iSBN) {
		ISBN = iSBN;
	}

	public String getCallNumber() {
		return callNumber;
	}

	public void setCallNumber(String callNumber) {
		this.callNumber = callNumber;
	}

	public int getNumberAvailable() {
		return numberAvailable;
	}

	public void setNumberAvailable(int numberAvailable) {
		this.numberAvailable = numberAvailable;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	@Override
	public String toString() {
		return "Books [ISBN=" + ISBN + ", callNumber=" + callNumber + ", numberAvailable=" + numberAvailable
				+ ", totalAmount=" + totalAmount + ", Title=" + Title + "]";
	}

}
