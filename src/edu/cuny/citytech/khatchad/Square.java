package edu.cuny.citytech.khatchad;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * A representation of a square.
 * 
 * @author <a href="mailto:rkhatchadourian@citytech.cuny.edu">Raffi Khatchadourian</a>
 */
public class Square {

    /**
     * The file used to create this square.
     */
	private File file;

    /**
     * The internal representation of this square.
     */
	private List<int[]> rowList = new ArrayList<>();

    /**
     * Creates a square from the given file.
     *
     * @param file The file in which to create the square. The components should be
     * separated by tabs.
     * @throws FileNotFoundException If the given file does not exist.
     */
	public Square(File file) throws FileNotFoundException {
		// store the file name.
		this.file = file;

		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				// scan the next line.
				String line = scanner.nextLine();

				// split the line by the tab character.
				String[] stringsOnLine = line.split("\t");

				// map the array of strings to an array of ints.
				int[] array = Stream.of(stringsOnLine).parallel().mapToInt(Integer::valueOf).toArray();

				// store the array.
				this.rowList.add(array);
			}
		}
	}

    /**
     * Returns true if this square is magic and false otherwise.
     *
     * @return True if this square is magic and false otherwise.
     */
	public boolean isMagic() {
		return areRowsMagic() && areColumnsMagic() && areDiagonalsMagic();
	}

    /**
     * Returns true if this square's two diagonals sum to the same number.
     *
     * @return true if this square's two diagonals sum to the same number.
     */
	private boolean areDiagonalsMagic() {
        //a list of the two diagonals.
		List<int[]> diagonalList = new ArrayList<>(2);

        //initialize the two diagonals.
		for (int i = 0; i < 2; i++)
			diagonalList.add(new int[this.rowList.size()]);

		for (int i = 0; i < this.rowList.size(); i++) {
			// NW to SE
			diagonalList.get(0)[i] = this.rowList.get(i)[i];

			// NE to SW
			diagonalList.get(1)[i] = this.rowList.get(i)[this.rowList.get(i).length - i - 1];
		}

        //return whether the diagonals sum to the same number.
		return areRowsMagic(diagonalList);
	}

    /**
     * Returns true if the square's columns sum to the same number and false otherwise.
     *
     * @return true if the square's columns sum to the same number and false otherwise.
     */
	private boolean areColumnsMagic() {
		List<int[]> transposedRowList = transpose(this.rowList);
		return areRowsMagic(transposedRowList);
	}

    /**
     * Returns a transposed version of the given list. The given list is not modified.
     * @param The list to transposed (not modified).
     * @return A transposed copy of the given list.
     */
	private static List<int[]> transpose(List<int[]> rowList) {
		List<int[]> ret = new ArrayList<>(rowList.size());

		for (int i = 0; i < rowList.size(); i++)
			ret.add(new int[rowList.get(i).length]);

		for (int i = 0; i < ret.size(); i++)
			for (int j = 0; j < ret.get(i).length; j++)
				ret.get(j)[i] = rowList.get(i)[j];

		return ret;
	}

    /**
     * Returns true if this square's rows sum to the same number and false otherwise.
     *
     * @return True if this square's rows sum to the same number and false otherwise.
     */
	private boolean areRowsMagic() {
		return areRowsMagic(this.rowList);
	}

    /**
     * Returns true if the given List contains integer arrays that sum to the same number and false otherwise.
     *
     * @return True if the given List contains integer arrays that sum to the same number and false otherwise.
     */
	private static boolean areRowsMagic(List<int[]> rowList) {
		return rowList.parallelStream().map(IntStream::of).map(IntStream::sum).distinct().count() == 1;
	}

    /**
     * Returns the file in which this square was created.
     * 
     * @return The file in which this square was created.
     */
	public File getFile() {
		return this.file;
	}
}
