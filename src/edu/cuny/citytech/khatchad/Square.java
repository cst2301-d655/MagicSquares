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

public class Square {

	private File file;

	private List<int[]> rowList = new ArrayList<>();

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

	public boolean isMagic() {
		return areRowsMagic() && areColumnsMagic() && areDiagonalsMagic();
	}

	private boolean areDiagonalsMagic() {
		List<int[]> diagonalList = new ArrayList<>(2);

		for (int i = 0; i < 2; i++)
			diagonalList.add(new int[this.rowList.size()]);

		for (int i = 0; i < this.rowList.size(); i++) {
			// NW to SE
			diagonalList.get(0)[i] = this.rowList.get(i)[i];

			// NE to SW
			diagonalList.get(1)[i] = this.rowList.get(i)[this.rowList.get(i).length - i - 1];
		}

		return areRowsMagic(diagonalList);
	}

	private boolean areColumnsMagic() {
		List<int[]> transposedRowList = transpose(this.rowList);
		return areRowsMagic(transposedRowList);
	}

	private static List<int[]> transpose(List<int[]> rowList) {
		List<int[]> ret = new ArrayList<>(rowList.size());

		for (int i = 0; i < rowList.size(); i++)
			ret.add(new int[rowList.get(i).length]);

		for (int i = 0; i < ret.size(); i++)
			for (int j = 0; j < ret.get(i).length; j++)
				ret.get(j)[i] = rowList.get(i)[j];

		return ret;
	}

	private boolean areRowsMagic() {
		return areRowsMagic(this.rowList);
	}

	private static boolean areRowsMagic(List<int[]> rowList) {
		return rowList.parallelStream().map(IntStream::of).map(IntStream::sum).distinct().count() == 1;
	}

	public File getFile() {
		return this.file;
	}

}
