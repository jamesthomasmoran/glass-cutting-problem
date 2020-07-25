package io.github.jamesthomasmoran.glasscuttingproblem.model;

/**
 * @Purpose: The shape class represents a single shape. DO NOT MODIFY THE
 *           SIGNITURE OF EXISTING METHODS, You may add additional methods if
 *           you wish
 * 
 * @author RYK
 * @since 30/10/2018 
 * extended by @author
 * 
 **/

public class Shape implements Comparable<Shape> {

	private int width;
	private int height;

	private int xLocation;

	private int yLocation;

	//field used when shape placed above another
	private int combinedWidth;

	private boolean valid;

	private boolean validHeight;

	private boolean validWidth;

	private boolean validShapeNumber;

	private int insertionNumber;

	public Shape(int width, int height) {

		// Shape width and height should not be greater than the sheet width and height
		if (width > Sheet.SHEET_WIDTH || height > Sheet.SHEET_HEIGHT) {
			throw new IllegalArgumentException("Shape width or height is not valid");
		}

		this.width = width;
		this.height = height;
		this.combinedWidth = width;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public int getXLocation() {
		return xLocation;
	}

	public void setXLocation(int xLocation) {
		this.xLocation = xLocation;
	}

	public int getYLocation() {
		return yLocation;
	}

	public void setYLocation(int yLocation) {
		this.yLocation = yLocation;
	}

	public boolean isValidHeight() {
		return validHeight;
	}

	public void setValidHeight(boolean validHeight) {
		this.validHeight = validHeight;
	}

	public boolean isValidWidth() {
		return validWidth;
	}

	public void setValidWidth(boolean validWidth) {
		this.validWidth = validWidth;
	}

	public boolean isValidShapeNumber() {
		return validShapeNumber;
	}

	public void setValidShapeNumber(boolean validShapeNumber) {
		this.validShapeNumber = validShapeNumber;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public int getCombinedWidth() {
		return combinedWidth;
	}

	public void setCombinedWidth(int combinedWidth) {
		this.combinedWidth = combinedWidth;
	}

	public int getInsertionNumber() {
		return insertionNumber;
	}

	public void setInsertionNumber(int insertionNumber) {
		this.insertionNumber = insertionNumber;
	}

	@Override
	public int compareTo(Shape o) {
		int oArea=o.getHeight()*o.getWidth();
		int thisArea=this.getHeight()*this.getWidth();
		if(thisArea>oArea){
			return 1;
		}
		else if(thisArea<oArea){
			return -1;
		}
		else{
			return 0;
		}
	}
}
