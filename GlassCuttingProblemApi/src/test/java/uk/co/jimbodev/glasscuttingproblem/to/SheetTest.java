package uk.co.jimbodev.glasscuttingproblem.to;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SheetTest {

    @Test
    public void testSheetTOInitialisedFirstSpaceIs250Tall300Wide(){
        Sheet sheet = new Sheet();

        assertEquals(0, sheet.getSpaces().get(0).getX1());
        assertEquals(300, sheet.getSpaces().get(0).getX2());
        assertEquals(0, sheet.getSpaces().get(0).getY1());
        assertEquals(250, sheet.getSpaces().get(0).getY2());
        assertEquals(250, sheet.getSpaces().get(0).getHeight());
        assertEquals(300, sheet.getSpaces().get(0).getWidth());
    }

    @Test
    public void testShapeCanBeAddedToEndOfSheet(){
        Sheet sheet = new Sheet();

        sheet.addShape(new Shape(20, 30));

        assertEquals(20, sheet.getShapes().get(0).getWidth());
        assertEquals(30, sheet.getShapes().get(0).getHeight());
    }

    @Test
    public void testSpaceCanBeAddedToEndOfSheet(){
        Sheet sheet = new Sheet();

        sheet.addSpace(new Space(0, 10, 0,20));

        assertEquals(0, sheet.getSpaces().get(1).getX1());
        assertEquals(10, sheet.getSpaces().get(1).getX2());
        assertEquals(0, sheet.getSpaces().get(1).getY1());
        assertEquals(20, sheet.getSpaces().get(1).getY2());
    }
}
