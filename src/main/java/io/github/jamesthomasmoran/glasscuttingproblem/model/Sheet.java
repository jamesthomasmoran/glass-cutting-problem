package io.github.jamesthomasmoran.glasscuttingproblem.model; /**
 * @Purpose: The Sheet class represents a sheet with a list of shelves.
 * DO NOT MODIFY THE EXISTING METHODS SIGNITURE, You may add additional methods if you wish
 * 
 * @author  RYK 
 * @since   30/10/2018
 * extended by @author 
 */
import java.util.ArrayList;
import java.util.List;

public class Sheet {

	public static final int SHEET_HEIGHT = 250; // sheet height

	public static final int SHEET_WIDTH = 300; // sheet width

	public static final int SHAPE_LIMIT = 20; // maximum number of shapes that can be placed in each sheet

	private List<Shelf> shelves = new ArrayList<Shelf>(); // list of shelves

	public Sheet() {
	}

	public void addShelf(Shelf shelf) {
		shelves.add(shelf);
	}

	public int allShelvesHeight() {

		int total = 0;

		for (Shelf shelf : this.shelves) {

			// has a shelf with at least 1 shape
			if (!shelf.getShapes().isEmpty()) {

				// add all shelf height to total
				total += shelf.getHeight();
			}
		}
		return total;
	}

	public List<Shelf> getShelves() {
		return this.shelves;
	}

		public int getHeight() {
		return SHEET_HEIGHT;
	}

	public int getWidth() {
		return SHEET_WIDTH ;
	}


}
