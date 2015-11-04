package edu.cuny.citytech.khatchad;

import java.io.File;
import java.io.FileNotFoundException;

public class MagicSquares {

	public static void main(String[] args) throws FileNotFoundException {
		String[] fileNameList = new String[] { "Mercury.txt", "Luna.txt", "Test.txt", "Test2.txt", "Test3.txt" };

		for (String fileName : fileNameList) {
			File file = new File(fileName);
			Square square = new Square(file);
			boolean magic = square.isMagic();
			System.out.println(square.getFile().getName() + " is " + (magic ? "" : "not ") + "magic.");
		}
	}
}
