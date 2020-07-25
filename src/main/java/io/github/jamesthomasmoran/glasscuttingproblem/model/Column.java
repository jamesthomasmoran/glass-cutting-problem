package io.github.jamesthomasmoran.glasscuttingproblem.model;

import java.util.ArrayList;
import java.util.List;

public class Column {
    private List<Shape> shapes = new ArrayList<>();

    private int width;

    private int xLocation;

    public List<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getXLocation() {
        return xLocation;
    }

    public void setXLocation(int xLocation) {
        this.xLocation = xLocation;
    }

    public void addShape(Shape shape){
        this.shapes.add(shape);
        this.width = this.width + shape.getCombinedWidth();
    }
}
