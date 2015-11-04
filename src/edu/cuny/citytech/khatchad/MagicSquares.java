package edu.cuny.citytech.khatchad;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * The main class for the magic squares assignment.
 * @author <a href="mailto:rkhatchadourian@citytech.cuny.edu">Raffi Khatchadourian</a>
 */
public class MagicSquares {

	public static void main(String[] args) throws FileNotFoundException {
        //list of file names to process.
		String[] fileNameList = new String[] { "Mercury.txt", "Luna.txt", "Test.txt", "Test2.txt", "Test3.txt" };

        //for each file.
		for (String fileName : fileNameList) {
            //create a file representation.
			File file = new File(fileName);

            //create a square from that file representatin.
			Square square = new Square(file);

            //find out if the square is "magic."
			boolean magic = square.isMagic();

            //output the result.
			System.out.println(square.getFile().getName() + " is " + (magic ? "" : "not ") + "magic.");
		}
	}
}
