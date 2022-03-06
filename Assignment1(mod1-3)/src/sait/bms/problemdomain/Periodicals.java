package sait.bms.problemdomain;

public class Periodicals extends Book {
	private char frequency;

	public Periodicals() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Periodicals(long iSBN, String callNumber, int numberAvailable, int totalAmount, String title) {
		super(iSBN, callNumber, numberAvailable, totalAmount, title);
		// TODO Auto-generated constructor stub
	}

	public Periodicals(long iSBN, String callNumber, int numberAvailable, int totalAmount, String title,
			char frequency) {
		super(iSBN, callNumber, numberAvailable, totalAmount, title);
		this.frequency = frequency;
	}

	public char getFrequency() {
		return frequency;
	}

	public void setFrequency(char frequency) {
		this.frequency = frequency;
	}

	@Override
	public String toString() {
		return "Periodicals [frequency=" + frequency + ", getISBN()=" + getISBN() + ", getCallNumber()="
				+ getCallNumber() + ", getNumberAvailable()=" + getNumberAvailable() + ", getTotalAmount()="
				+ getTotalAmount() + ", getTitle()=" + getTitle() + "]";
	}

}
