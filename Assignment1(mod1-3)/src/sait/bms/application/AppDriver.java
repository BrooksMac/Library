package sait.bms.application;

import java.io.FileNotFoundException;
import java.io.IOException;

import sait.bms.manager.BookManager;

public class AppDriver {
	public static void main(String[] args) throws IOException {

		new BookManager();
		// BookManager bms = new BookManager();
		// bms.displayMenu();

	}
}
