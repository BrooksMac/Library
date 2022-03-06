package sait.bms.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import sait.bms.problemdomain.*;

public class BookManager {

	private ArrayList<Book> books = new ArrayList<>();
	private Scanner in;
	private static final String PATH = "res/books.txt";

	public BookManager() throws IOException {
		in = new Scanner(System.in);
		loadBooks();
		displayMenu();
	}

	private void loadBooks() throws FileNotFoundException {
		Book book;
		Scanner in = new Scanner(new File(PATH));

		while (in.hasNext()) {
			String line = in.nextLine();
			String[] fields = line.split(";");

			char lastChar = fields[0].charAt(12);

			switch (lastChar) {
			case '0':
			case '1':
				book = new ChildrensBook(Long.parseLong(fields[0]), fields[1], Integer.parseInt(fields[2]),
						Integer.parseInt(fields[3]), fields[4], fields[5], fields[6].charAt(0));
				books.add(book);
				break;

			case '2':
			case '3':
				book = new CookBook(Long.parseLong(fields[0]), fields[1], Integer.parseInt(fields[2]),
						Integer.parseInt(fields[3]), fields[4], fields[5], fields[6].charAt(0));
				books.add(book);
				break;
			case '4':
			case '5':
			case '6':
			case '7':
				book = new PaperBack(Long.parseLong(fields[0]), fields[1], Integer.parseInt(fields[2]),
						Integer.parseInt(fields[3]), fields[4], fields[7].charAt(0), Integer.parseInt(fields[6]),
						fields[5]);
				books.add(book);
				break;
			case '8':
			case '9':
				book = new Periodicals(Long.parseLong(fields[0]), fields[1], Integer.parseInt(fields[2]),
						Integer.parseInt(fields[3]), fields[4], fields[5].charAt(0));
				books.add(book);
				break;
			}

		}
	}

	public void displayMenu() throws IOException {
		int option = 0;
		while (option != 5) {

			System.out.println("Welcome in ABC Company: How May We Assist You?");
			System.out.println("1\tCheckout book");
			System.out.println("2\tFind Books by Title");
			System.out.println("3\tDisplay Books by Type");
			System.out.println("4\tProduce Random Book List");
			System.out.println("5\tSave & Exit");

			System.out.print("Enter option: ");
			option = this.in.nextInt();

			switch (option) {
			case 1: {
				checkoutBook();
				break;
			}
			case 2: {
				findTitle();
				break;
			}
			case 3: {
				searchBookByType();
				break;
			}
			case 4: {
				// randomBook();
				break;
			}
			case 5: {
				saveAndExit();
			}
			default: {
				System.out.println("Invalid option!");
				break;
			}
			}
		}
	}

	private void checkoutBook() throws FileNotFoundException {
		boolean foundBook = false;
		int indexOfBook = 999;
		Scanner in = new Scanner(System.in);

		System.out.print("Please enter the ISBN# of the book you wish to checkout: ");
		long wantedISBN = in.nextLong();

		for (Book book : books) {
			if (book.getISBN() == (wantedISBN)) {
				indexOfBook = books.indexOf(book);
				foundBook = true;

			}
		}

		if (foundBook) {
			if (books.get(indexOfBook).getNumberAvailable() > 0) {
				System.out.println("Enjoy reading " + books.get(indexOfBook).getTitle());
				books.get(indexOfBook).setNumberAvailable(books.get(indexOfBook).getNumberAvailable() - 1);

			}
			if (books.get(indexOfBook).getNumberAvailable() == 0) {
				System.out.println("This book is no longer in stock.");
			}
		} else {
			System.out.println("This ISBN does not exist in our records.");

		}
	}

	// Allow user to search a book by title
	/**
	 * This method allow the user to search a book by it's title (not case-sensitive)
	 * @param in this will be used as a scanner to scan inputs
	 * @param title this will store the value of the user input
	 * @param printISBN Calls the Book's ISBN value as a String
	 * @param printCallNum Calls the Book's callNumber value as a String
	 * @param printAvail Calls the Book's totalNumberAvailable value as a String
	 * @param printTotal Calls the Book's totalAmount value as a String
	 * @param printTitle Calls the Book's title value as a String
	 * @param lastDig This will be used to call the Book's 13-number ISBN and get the last digit, to be used to get the Book's subclass
	 * @param format Calls the ChildrensBook format value
	 * @param printChildrenBookAuthor Calls the ChildrensBook's author value as a String
	 * @param pirntFormat stores the format value as a String depending on the condition
	 * @param diet Calls the CookBook's diet value
	 * @param printCookBookPub Calls the CookBook's publisher value as a String
	 * @param printDiet stores the diet value as a String depending on the condition
	 * @param genre Call's the PaperBack's genre value
	 * @param printPaperBackAuthor Calls the PaperBack's author value as a String
	 * @param printYear Calls the PaperBack's year value as a String
	 * @param printGenre stores the genre value as a String depending on the condition
	 * @param frequency Calls the Periodical frequency value
	 * @param printFrequency stores the frequency value as a String depending on the condition
	 */
	private void findTitle() {
		Scanner in = new Scanner(System.in);
		String title;
		System.out.print("Enter title to search for: "); //Ask user what title to look for
		title = in.nextLine(); //the user input of what to look for
		System.out.print("Matching Books: ");
		for (Book book : books) {
			if (book.getTitle().toUpperCase().contains(title.toUpperCase())) { //takes the user input and compare it to all of the Book's title to see if it contains the input (not case-sensitive)
				String printISBN = Long.toString(book.getISBN());
				String printCallNum = book.getCallNumber();
				String printAvail = Integer.toString(book.getNumberAvailable());
				String printTotal = Integer.toString(book.getTotalAmount());
				String printTitle = book.getTitle();
				/*
				 * Print out the values of all the matching books in the superclass Book
				 */
				System.out.printf("\nISBN:              %s", printISBN);
				System.out.printf("\nCall Number:       %s", printCallNum);
				System.out.printf("\nAvailable:         %s", printAvail);
				System.out.printf("\nTotal:             %s", printTotal);
				System.out.printf("\nTitle:             %s", printTitle);
				String lastDig = Long.toString(book.getISBN());
				if (lastDig.charAt(12) == '0' || lastDig.charAt(12) == '1') { //If Book last Digit ISBN is 0 or 1 then it's a ChildrensBook type and print the additional values
					String printChildrenBookAuthor = ((ChildrensBook) book).getAuthors();
					char format = ((ChildrensBook) book).getFormat();
					String printFormat = null;
					switch (format) { // takes the format of the ChildrensBook and modify the printFormat value
					case 'P':
						printFormat = "Picture Book";
						break;
					case 'E':
						printFormat = "Early Readers";
						break;
					case 'C':
						printFormat = "Chapter Book";
						break;
					}
					System.out.printf("\nAuthor:            %s", printChildrenBookAuthor);
					System.out.printf("\nFormat:            %s", printFormat);
				} else if (lastDig.charAt(12) == '2' || lastDig.charAt(12) == '3') { //If Book last Digit ISBN is 2 or 3 then it's a CookBook type and print the additional values
					String printCookBookPub = ((CookBook) book).getPublisher();
					char diet = ((CookBook) book).getDiet();
					String printDiet = null;
					switch (diet) { // takes the diet of the CookBook and modify the printDiet value
					case 'D':
						printDiet = "Diabetic";
						break;
					case 'V':
						printDiet = "Vegetarian";
						break;
					case 'G':
						printDiet = "Gluten-free";
						break;
					case 'I':
						printDiet = "International";
						break;
					case 'N':
						printDiet = "None";
						break;
					}
					System.out.printf("\nPublisher:         %s", printCookBookPub);
					System.out.printf("\nDiet:              %s", printDiet);
				} else if (lastDig.charAt(12) == '4' || lastDig.charAt(12) == '5' || lastDig.charAt(12) == '6'
						|| lastDig.charAt(12) == '7') { //If Book last Digit ISBN is 4, 5, 6 or 7 then it's a PaperBack type and print the additional values
					String printPaperBackAuthor = ((PaperBack) book).getAuthors();
					int printYear = ((PaperBack) book).getYear();
					char genre = ((PaperBack) book).getGenre();
					String printGenre = null;
					switch (genre) { // takes the genre of the PaperBack and modify the printGenre value
					case 'A':
						printGenre = "Adventure";
						break;
					case 'C':
						printGenre = "Classic";
						break;
					case 'D':
						printGenre = "Drama";
						break;
					case 'E':
						printGenre = "Education";
						break;
					case 'F':
						printGenre = "Fantasy";
						break;
					case 'S':
						printGenre = "Science Fiction";
						break;
					}
					System.out.printf("\nAuthor:            %s", printPaperBackAuthor);
					System.out.printf("\nYear:              %d", printYear);
					System.out.printf("\nGenre:             %s", printGenre);
				} else if (lastDig.charAt(12) == '8' || lastDig.charAt(12) == '9') { //If Book last Digit ISBN is 8 or 9 then it's a Periodical type and print the additional values
					char frequency = ((Periodicals) book).getFrequency();
					String printFrequency = null;
					switch (frequency) { // takes the frequency of the Periodical and modify the printFrequency value
					case 'D':
						printFrequency = "Daily";
						break;
					case 'W':
						printFrequency = "Weekly";
						break;
					case 'M':
						printFrequency = "Monthly";
						break;
					case 'B':
						printFrequency = "Bimonthly";
						break;
					case 'Q':
						printFrequency = "Quarterly";
						break;
					}
					System.out.printf("\nFrequency:         %s", printFrequency);
				}
				System.out.println(); //add a space after each iteration
			}

		}
		System.out.println(); // add a space after executing this method
	}
	/**
	 * This method will save all modifications in the arraylist books by rewriting them in the file, then exit the program.
	 * @param fw Creates a FileWriter object for the PATH
	 * @param arraySize Takes the size of the ArrayList books
	 * @throws IOException
	 */
	private void saveAndExit() throws IOException {
		int arraySize = books.size();
		try (FileWriter save = new FileWriter(PATH)) {
			for (Book book : books) {
				save.write((book.getISBN() + ";" + book.getCallNumber() + ";" + book.getNumberAvailable() + ";"
						+ book.getTotalAmount() + ";" + book.getTitle() + ";").toString()); // Prints the values of the superclass as a string
				if (book instanceof ChildrensBook) {
					save.write((((ChildrensBook) book).getAuthors() + ";" + ((ChildrensBook) book).getFormat())
							.toString()); // If the book is in the subclass ChildrensBook prints the additional values as a string
				} else if (book instanceof CookBook) {
					save.write((((CookBook) book).getPublisher() + ";" + ((CookBook) book).getDiet()).toString()); // If the book is in the subclass CookBook prints the additional values as a string
				} else if (book instanceof PaperBack) {
					save.write((((PaperBack) book).getAuthors() + ";" + ((PaperBack) book).getYear() + ";"
							+ ((PaperBack) book).getGenre()).toString()); // If the book is in the subclass PaperBack prints the additional values as a string
				} else if (book instanceof Periodicals) {
					save.write(Character.toString(((Periodicals) book).getFrequency())); // If the book is in the subclass Periodicals prints the additional values as a string
				}
				
				if (arraySize > 1) {
					save.write("\n"); //make a new line after printing a book
					arraySize = arraySize - 1;
				} else {
					break; //This will stop the code from printing a new line with no values
				}
			}
		}
		System.exit(0); // exits the program after rewriting the file
	}

	private void searchBookByType() {
		Scanner in = new Scanner(System.in);
		int category;
		System.out.println("What type of book would you like to find?");
		System.out.printf("%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n", "1.", "Children's Books", "2.",
				"Cookbooks", "3.", "Paperbacks", "4.", "Periodicals");
		category = in.nextInt();

		switch (category) {
		case 1: {
			System.out.println("Which format are you looking for?");
			System.out.printf("%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n", "1.", "Picture book", "2.", "Early readers",
					"3.", "Chapter book");
			category = in.nextInt();
			System.out.println("Matching Books:");
			for (Book book : books) {
				if (book instanceof ChildrensBook) {
					switch (category) {
					case 1:
						if (((ChildrensBook) book).getFormat() == 'P') {
							System.out.printf(
									"%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n",
									"ISBN:", book.getISBN(), "Call Number:", book.getCallNumber(), "Available:",
									book.getNumberAvailable(), "Total:", book.getTotalAmount(), "Title:",
									book.getTitle(), "Format:", "Picture book\n");
						}
						break;
					case 2:
						if (((ChildrensBook) book).getFormat() == 'E') {
							System.out.printf(
									"%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n",
									"ISBN:", book.getISBN(), "Call Number:", book.getCallNumber(), "Available:",
									book.getNumberAvailable(), "Total:", book.getTotalAmount(), "Title:",
									book.getTitle(), "Format:", "Early Readers\n");
						}
						break;
					case 3:
						if (((ChildrensBook) book).getFormat() == 'C') {
							System.out.printf(
									"%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n",
									"ISBN:", book.getISBN(), "Call Number:", book.getCallNumber(), "Available:",
									book.getNumberAvailable(), "Total:", book.getTotalAmount(), "Title:",
									book.getTitle(), "Format:", "Chapter book\n1");
						}
						break;
					}
				}
			}
			break;
		}
		case 2: {
			System.out.println("Which diet are you looking for?");
			System.out.printf("%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n", "1.", "Diabetic",
					"2.", "Vegetarian", "3.", "Gluten-free", "4.", "International", "5.", "None");
			category = in.nextInt();
			System.out.println("Matching Books:");
			for (Book book : books) {
				if (book instanceof CookBook) {
					switch (category) {
					case 1:
						if (((CookBook) book).getDiet() == 'D') {
							System.out.printf(
									"%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n",
									"ISBN:", book.getISBN(), "Call Number:", book.getCallNumber(), "Available:",
									book.getNumberAvailable(), "Total:", book.getTotalAmount(), "Title:",
									book.getTitle(), "Format:", "Diabetic\n");
						}
						break;

					case 2:
						if (((CookBook) book).getDiet() == 'V') {
							System.out.printf(
									"%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n",
									"ISBN:", book.getISBN(), "Call Number:", book.getCallNumber(), "Available:",
									book.getNumberAvailable(), "Total:", book.getTotalAmount(), "Title:",
									book.getTitle(), "Format:", "Vegetarian\n");
						}
						break;

					case 3:
						if (((CookBook) book).getDiet() == 'G') {
							System.out.printf(
									"%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n",
									"ISBN:", book.getISBN(), "Call Number:", book.getCallNumber(), "Available:",
									book.getNumberAvailable(), "Total:", book.getTotalAmount(), "Title:",
									book.getTitle(), "Format:", "Gluten-free\n");
						}
						break;

					case 4:

						if (((CookBook) book).getDiet() == 'I') {
							System.out.printf(
									"%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n",
									"ISBN:", book.getISBN(), "Call Number:", book.getCallNumber(), "Available:",
									book.getNumberAvailable(), "Total:", book.getTotalAmount(), "Title:",
									book.getTitle(), "Format:", ", International\n");
						}
						break;

					case 5:
						if (((CookBook) book).getDiet() == 'N') {
							System.out.printf(
									"%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n",
									"ISBN:", book.getISBN(), "Call Number:", book.getCallNumber(), "Available:",
									book.getNumberAvailable(), "Total:", book.getTotalAmount(), "Title:",
									book.getTitle(), "Format:", "None\n");
						}
						break;

					}
				}
			}
			break;
		}
		case 3: {
			System.out.println("Which genre are you looking for?");
			System.out.printf("%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n", "1.",
					"Adventure", "2.", "Drama", "3.", "Education", "4.", "Classic", "5.", "Fantasy", "6.",
					"Science Fiction");
			category = in.nextInt();
			System.out.println("Matching Books:");
			for (Book book : books) {
				if (book instanceof PaperBack) {
					switch (category) {
					case 1:
						if (((PaperBack) book).getGenre() == 'A') {
							System.out.printf(
									"%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n",
									"ISBN:", book.getISBN(), "Call Number:", book.getCallNumber(), "Available:",
									book.getNumberAvailable(), "Total:", book.getTotalAmount(), "Title:",
									book.getTitle(), "Format:", "Adventure\n");
						}
						break;

					case 2:
						if (((PaperBack) book).getGenre() == 'D') {
							System.out.printf(
									"%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n",
									"ISBN:", book.getISBN(), "Call Number:", book.getCallNumber(), "Available:",
									book.getNumberAvailable(), "Total:", book.getTotalAmount(), "Title:",
									book.getTitle(), "Format:", "Drama\n");
						}
						break;

					case 3:
						if (((PaperBack) book).getGenre() == 'E') {
							System.out.printf("%-10s %10s\n%-10s%10s\n%-10s %10s\n%-10s %10s\n%-10s %10s\n%-10s %10s\n",
									"ISBN:", book.getISBN(), "Call Number:", book.getCallNumber(), "Available:",
									book.getNumberAvailable(), "Total:", book.getTotalAmount(), "Title:",
									book.getTitle(), "Format:", "Education\n");
						}
						break;

					case 4:
						if (((PaperBack) book).getGenre() == 'C') {
							System.out.printf(
									"%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n",
									"ISBN:", book.getISBN(), "Call Number:", book.getCallNumber(), "Available:",
									book.getNumberAvailable(), "Total:", book.getTotalAmount(), "Title:",
									book.getTitle(), "Format:", "Classic\n");
						}
						break;

					case 5:
						if (((PaperBack) book).getGenre() == 'F') {
							System.out.printf(
									"%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n",
									"ISBN:", book.getISBN(), "Call Number:", book.getCallNumber(), "Available:",
									book.getNumberAvailable(), "Total:", book.getTotalAmount(), "Title:",
									book.getTitle(), "Format:", "Fantasy\n");
						}
						break;

					case 6:
						if (((PaperBack) book).getGenre() == 'S') {
							System.out.printf(
									"%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n",
									"ISBN:", book.getISBN(), "Call Number:", book.getCallNumber(), "Available:",
									book.getNumberAvailable(), "Total:", book.getTotalAmount(), "Title:",
									book.getTitle(), "Format:", "Science Fiction\n");
						}
						break;
					}
				}
			}
			break;
		}
		case 4: {
			System.out.println("Which frequency are you looking for?");
			System.out.printf("%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n", "1.", "Daily", "2.",
					"Weekly", "3.", "Monthly", "4.", "Bimonthly", "5.", "Quarterly");
			category = in.nextInt();
			for (Book book : books) {
				if (book instanceof Periodicals) {
					switch (category) {
					case 1:
						if (((Periodicals) book).getFrequency() == 'D') {
							System.out.println("Matching Books:");
							System.out.printf(
									"%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n",
									"ISBN:", book.getISBN(), "Call Number:", book.getCallNumber(), "Available:",
									book.getNumberAvailable(), "Total:", book.getTotalAmount(), "Title:",
									book.getTitle(), "Format:", "Daily\n");
						}

						break;
					case 2:
						if (((Periodicals) book).getFrequency() == 'W') {
							System.out.println("Matching Books:");
							System.out.printf(
									"%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n",
									"ISBN:", book.getISBN(), "Call Number:", book.getCallNumber(), "Available:",
									book.getNumberAvailable(), "Total:", book.getTotalAmount(), "Title:",
									book.getTitle(), "Format:", "Weekly\n");
						}

						break;
					case 3:
						if (((Periodicals) book).getFrequency() == 'M') {
							System.out.println("Matching Books:");
							System.out.printf(
									"%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n",
									"ISBN:", book.getISBN(), "Call Number:", book.getCallNumber(), "Available:",
									book.getNumberAvailable(), "Total:", book.getTotalAmount(), "Title:",
									book.getTitle(), "Format:", "Monthly\n");
						}
						break;

					case 4:
						if (((Periodicals) book).getFrequency() == 'B') {
							System.out.println("Matching Books:");
							System.out.printf(
									"%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n",
									"ISBN:", book.getISBN(), "Call Number:", book.getCallNumber(), "Available:",
									book.getNumberAvailable(), "Total:", book.getTotalAmount(), "Title:",
									book.getTitle(), "Format:", "Bimonthly\n");
						}
						break;

					case 5:
						if (((Periodicals) book).getFrequency() == 'Q') {
							System.out.println("Matching Books:");
							System.out.printf(
									"%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n%-20s %-20s\n",
									"ISBN:", book.getISBN(), "Call Number:", book.getCallNumber(), "Available:",
									book.getNumberAvailable(), "Total:", book.getTotalAmount(), "Title:",
									book.getTitle(), "Format:", "Quarterly\n");
						}
						break;
					}
				}
			}
		}
		}
	}
}
