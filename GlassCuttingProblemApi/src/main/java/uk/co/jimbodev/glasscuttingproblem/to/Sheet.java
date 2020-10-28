package uk.co.jimbodev.glasscuttingproblem.to;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Sheet {
    public static final int SHEET_HEIGHT = 250; // sheet height

    public static final int SHEET_WIDTH = 300; // sheet width

    public static final int SHAPE_LIMIT = 20; // maximum number of shapes that can be placed in each sheet

    @Expose
    private List<Shape> shapes = new ArrayList();

    private List<Space> spaces = new ArrayList();

    public Sheet(){
        Space cleanSheet = new Space();
        cleanSheet.setX1(0);
        cleanSheet.setX2(SHEET_WIDTH);
        cleanSheet.setY1(0);
        cleanSheet.setY2(SHEET_HEIGHT);
        cleanSheet.setHeight(SHEET_HEIGHT);
        cleanSheet.setWidth(SHEET_WIDTH);
        spaces.add(cleanSheet);
    }

    public void addShape(Shape shape){
        this.shapes.add(shape);
    }

    public void addSpace(Space space){
        this.spaces.add(space);
    }
}
