import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MagicSquares {
	public static void main(String[] args) {
		Scanner scanner = null;
		try {
			 scanner = new Scanner(new File("Luna.txt"));
		} catch (FileNotFoundException e) {
			System.err.println("File not found.");
			System.exit(1);
		}
		
		while (scanner.hasNextLine()) {
			String in = scanner.nextLine();
			System.out.println(in);
		}
	}
}
