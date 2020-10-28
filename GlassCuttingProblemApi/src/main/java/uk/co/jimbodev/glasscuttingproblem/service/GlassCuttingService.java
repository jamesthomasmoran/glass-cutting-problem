package uk.co.jimbodev.glasscuttingproblem.service;

import org.decimal4j.util.DoubleRounder;
import uk.co.jimbodev.glasscuttingproblem.to.Shape;
import uk.co.jimbodev.glasscuttingproblem.to.Sheet;
import uk.co.jimbodev.glasscuttingproblem.to.Space;

import java.util.LinkedList;
import java.util.List;

public abstract class GlassCuttingService implements IGlassCuttingService {

    private static final int GREATER = 1;
    private static final int LESSER = -1;
    private static final int INSIDE = 0;
    private static final int LEFT_BOTTOM_EDGE = 0;
    private static final int FIRST_INSERTION_NUMBER = 1;
    public abstract List<Sheet> putShapesOnShelvesOnSheets(List<Shape> shapes);

    @Override
    public int getNumberOfSheets(List<Sheet> sheets) {
        return sheets.size();
    }

    @Override
    public double getSpaceEfficiencyPercentage(List<Sheet> sheets) {
        final double MAX_AREA = Sheet.SHEET_HEIGHT * Sheet.SHEET_WIDTH * sheets.size();
        double totalFilledArea = getTotalFilledAreaOfSheets(sheets);

        return DoubleRounder.round(totalFilledArea / MAX_AREA, 2);
    }

    private double getTotalFilledAreaOfSheets(List<Sheet> sheets){
        double totalFilledArea = 0;
        for(Sheet sheet : sheets){
            for(Shape shape : sheet.getShapes()){
                        double shapeArea = shape.getHeight() * shape.getWidth();
                        totalFilledArea += shapeArea;
                    }
        }
        return totalFilledArea;
    }


    protected List<Space> updateSpacesOnPlacedSheet(Sheet sheet, Shape shape){
        List<Space> spaces = new LinkedList();

        for(Space space : sheet.getSpaces()){
            int spaceCount = 0;

            int x1InSpace = pointInSpace(shape.getX1(), space.getX1(), space.getX2());
            int x2InSpace = pointInSpace(shape.getX2(), space.getX1(), space.getX2());
            int y1InSpace = pointInSpace(shape.getY1(), space.getY1(), space.getY2());
            int y2InSpace = pointInSpace(shape.getY2(), space.getY1(), space.getY2());

            if (newSpaceNeededOnSide(y1InSpace, x1InSpace, x2InSpace)) {
                spaceCount++;
                spaces.add(new Space(space.getX1(), space.getX2(), space.getY1(), shape.getY1()));

            }
            //new space on top
            if (newSpaceNeededOnSide(y2InSpace, x1InSpace, x2InSpace) ) {
                spaceCount++;
                spaces.add(new Space(space.getX1(), space.getX2(), shape.getY2(), space.getY2()));
            }
            //new space on left
            if (newSpaceNeededOnSide(x1InSpace, y1InSpace, y2InSpace)) {
                spaceCount++;
                spaces.add(new Space(space.getX1(), shape.getX1(), space.getY1(), space.getY2()));
            }
            //new space on right
            if (newSpaceNeededOnSide(x2InSpace, y1InSpace, y2InSpace)) {
                spaceCount++;
                spaces.add(new Space(shape.getX2(), space.getX2(), space.getY1(), space.getY2()));
            }

            if (spaceCount < 1) {
                spaces.add(space);
            }

        }

        for(int i = 0; i < spaces.size(); i++){
            for(int j = 0; j < spaces.size(); j++){
                if(smallerSpaceThanOther(spaces.get(i), spaces.get(j))){
                    spaces.remove(i);
                    i--;
                    j = spaces.size();
                }
            }
        }
        return spaces;
    }

    private int pointInSpace(int shapeVal, int spaceVal1, int spaceVal2){

        if(shapeVal > spaceVal2 || (shapeVal == Sheet.SHEET_WIDTH && shapeVal == spaceVal2)){
            return GREATER;
        }
        else if(shapeVal < spaceVal1 || (shapeVal == LEFT_BOTTOM_EDGE && shapeVal == spaceVal1)){
            return LESSER;
        }
        else {
            return INSIDE;
        }
    }

    private boolean smallerSpaceThanOther(Space space1, Space space2){
        if(space1.getX1() == space1.getX2() || space1.getY1() == space1.getY2()){
            return true;
        }
        if(space1.getX1() == space2.getX1() && space1.getY1() == space2.getY1() && space1.getX2() == space2.getX2() && space1.getY2() < space2.getY2()){
            return true;
        }
        if(space1.getX1() == space2.getX1() && space1.getY1() == space2.getY1() && space1.getY2() == space2.getY2() && space1.getX2() < space2.getX2()){
            return true;
        }
        if(space1.getX1() == space2.getX1() && space1.getX2() == space2.getX2() && space1.getY2() == space2.getY2() && space1.getY1() > space2.getY1()){
            return true;
        }
        if(space1.getX2() == space2.getX2() && space1.getY1() == space2.getY1() && space1.getY2() == space2.getY2() && space1.getX1() > space2.getX1()){
            return true;
        }
        return false;
    }

    private boolean newSpaceNeededOnSide(int checkedSide, int smallerOppositeAxis, int biggerOppositeAxis){
        return checkedSide == INSIDE && ((smallerOppositeAxis == INSIDE || biggerOppositeAxis == INSIDE) || (smallerOppositeAxis == LESSER && biggerOppositeAxis == GREATER));
    }

    protected boolean shapeFitsInSpace(Shape shape, Space space){
        return shape.getHeight() <= space.getHeight() && shape.getWidth() <= space.getWidth() ? true : false;
    }

    protected void setShapeXYCoordinates(Shape shape, Space space){
        shape.setX1(space.getX1());
        shape.setY1(space.getY1());
        shape.setX2(space.getX1() + shape.getWidth());
        shape.setY2(space.getY1() + shape.getHeight());
    }
}
