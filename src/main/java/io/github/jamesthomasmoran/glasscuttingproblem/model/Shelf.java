package io.github.jamesthomasmoran.glasscuttingproblem.model; /**
 * @Purpose: The Shelf class represents a shelf with a list of shapes.
 * DO NOT MODIFY THE EXISTING METHODS, You may add additional methods if you wish
 * 
 * @author  RYK 
 * @since   30/10/2018
 * extended by @author 
 */

import java.util.ArrayList;
import java.util.List;

public class Shelf {

	// The used width: the width of all the shapes placed in the shelf
	private int usedWidth;

	// Shelf height: Equals to the height of the first shape placed in the shelf
	private int shelfHeight;

	List<Shape> shapes = new ArrayList<>();

	public Shelf() {

	}

	public int getHeight() {

		if (shapes.size() != 0) {
			// equals to the height of shape placed at the left
			this.shelfHeight = shapes.get(0).getHeight();
			return this.shelfHeight;
		} else
			return 0;
	}

	public void place(Shape shape) {

		usedWidth += shape.getCombinedWidth();
		shapes.add(shape);
	}

	public List<Shape> getShapes() {
		return shapes;
	}


	public int getWidth() {
		return usedWidth;
	}

}
