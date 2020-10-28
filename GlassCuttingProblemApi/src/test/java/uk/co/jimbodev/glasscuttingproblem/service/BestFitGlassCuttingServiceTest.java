package uk.co.jimbodev.glasscuttingproblem.service;

import org.junit.jupiter.api.Test;
import uk.co.jimbodev.glasscuttingproblem.to.Shape;
import uk.co.jimbodev.glasscuttingproblem.to.Sheet;
import uk.co.jimbodev.glasscuttingproblem.to.Space;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BestFitGlassCuttingServiceTest {

    @Test
    public void testShapeOnTopOfShape1InOverhangShape3RightOfShape1UnderShape(){
        List<Shape> shapes = new ArrayList();
        Shape shape1 = new Shape(50,100);
        Shape Shape = new Shape(70,100);
        Shape shape3 = new Shape(20,100);
        shapes.add(shape1);
        shapes.add(Shape);
        shapes.add(shape3);
        List<Sheet> sheets = new BestFitGlassCuttingService().putShapesOnShelvesOnSheets(shapes);

        List<Shape> placed = sheets.get(0).getShapes();
        List<Space> spaces = sheets.get(0).getSpaces();


        assertEquals(placed.get(0).getX1(), 0);
        assertEquals(placed.get(0).getX2(), 50);
        assertEquals(placed.get(0).getY1(), 0);
        assertEquals(placed.get(0).getY2(), 100);

        assertEquals(placed.get(1).getX1(), 0);
        assertEquals(placed.get(1).getX2(), 70);
        assertEquals(placed.get(1).getY1(), 100);
        assertEquals(placed.get(1).getY2(), 200);

        assertEquals(placed.get(2).getX1(), 50);
        assertEquals(placed.get(2).getX2(), 70);
        assertEquals(placed.get(2).getY1(), 0);
        assertEquals(placed.get(2).getY2(), 100);


    }
    @Test
    public void testShapeThatDoesNotOnEndOfSheetAddedToNewSheet(){
        List<Shape> shapes = new ArrayList();
        Shape shape1 = new Shape(50,100);
        Shape Shape = new Shape(70,100);
        Shape shape3 = new Shape(251,100);
        shapes.add(shape1);
        shapes.add(Shape);
        shapes.add(shape3);
        List<Sheet> sheets = new BestFitGlassCuttingService().putShapesOnShelvesOnSheets(shapes);

        List<Shape> placed = sheets.get(0).getShapes();
        List<Shape> placed2 = sheets.get(1).getShapes();
        List<Space> spaces = sheets.get(0).getSpaces();


        assertEquals(placed.get(0).getX1(), 0);
        assertEquals(placed.get(0).getX2(), 50);
        assertEquals(placed.get(0).getY1(), 0);
        assertEquals(placed.get(0).getY2(), 100);

        assertEquals(placed.get(1).getX1(), 0);
        assertEquals(placed.get(1).getX2(), 70);
        assertEquals(placed.get(1).getY1(), 100);
        assertEquals(placed.get(1).getY2(), 200);

        assertEquals(placed2.get(0).getX1(), 0);
        assertEquals(placed2.get(0).getX2(), 251);
        assertEquals(placed2.get(0).getY1(), 0);
        assertEquals(placed2.get(0).getY2(), 100);
    }
    @Test
    public void testShapeFitsInGapBetween3OverShapes(){
        List<Shape> shapes = new ArrayList();
        Shape shape1 = new Shape(50,100);
        Shape Shape = new Shape(70,100);
        Shape shape3 = new Shape(70,120);
        Shape shape4 = new Shape(20,100);
        shapes.add(shape1);
        shapes.add(Shape);
        shapes.add(shape3);
        shapes.add(shape4);
        List<Sheet> sheets = new BestFitGlassCuttingService().putShapesOnShelvesOnSheets(shapes);

        List<Shape> placed = sheets.get(0).getShapes();
        List<Space> spaces = sheets.get(0).getSpaces();


        assertEquals(placed.get(0).getX1(), 0);
        assertEquals(placed.get(0).getX2(), 50);
        assertEquals(placed.get(0).getY1(), 0);
        assertEquals(placed.get(0).getY2(), 100);

        assertEquals(placed.get(1).getX1(), 0);
        assertEquals(placed.get(1).getX2(), 70);
        assertEquals(placed.get(1).getY1(), 100);
        assertEquals(placed.get(1).getY2(), 200);

        assertEquals(placed.get(2).getX1(), 70);
        assertEquals(placed.get(2).getX2(), 140);
        assertEquals(placed.get(2).getY1(), 0);
        assertEquals(placed.get(2).getY2(), 120);

        assertEquals(placed.get(3).getX1(), 50);
        assertEquals(placed.get(3).getX2(), 70);
        assertEquals(placed.get(3).getY1(), 0);
        assertEquals(placed.get(3).getY2(), 100);
    }

    @Test
    public void testShapeFitsAtLeftOfSheetOnceLargeShapeBlocksLengthOfSheet(){
        List<Shape> shapes = new ArrayList();
        Shape shape1 = new Shape(50,75);
        Shape Shape = new Shape(30,75);
        Shape shape3 = new Shape(50,75);
        Shape shape4 = new Shape(50,150);
        Shape shape5 = new Shape(20,50);
        shapes.add(shape1);
        shapes.add(Shape);
        shapes.add(shape3);
        shapes.add(shape4);
        shapes.add(shape5);
        List<Sheet> sheets = new BestFitGlassCuttingService().putShapesOnShelvesOnSheets(shapes);

        List<Shape> placed = sheets.get(0).getShapes();
        List<Space> spaces = sheets.get(0).getSpaces();


        assertEquals(placed.get(0).getX1(), 0);
        assertEquals(placed.get(0).getX2(), 50);
        assertEquals(placed.get(0).getY1(), 0);
        assertEquals(placed.get(0).getY2(), 75);

        assertEquals(placed.get(1).getX1(), 50);
        assertEquals(placed.get(1).getX2(), 80);
        assertEquals(placed.get(1).getY1(), 0);
        assertEquals(placed.get(1).getY2(), 75);

        assertEquals(placed.get(2).getX1(), 80);
        assertEquals(placed.get(2).getX2(), 130);
        assertEquals(placed.get(2).getY1(), 0);
        assertEquals(placed.get(2).getY2(), 75);

        assertEquals(placed.get(3).getX1(), 130);
        assertEquals(placed.get(3).getX2(), 180);
        assertEquals(placed.get(3).getY1(), 0);
        assertEquals(placed.get(3).getY2(), 150);

        assertEquals(placed.get(4).getX1(), 0);
        assertEquals(placed.get(4).getX2(), 20);
        assertEquals(placed.get(4).getY1(), 75);
        assertEquals(placed.get(4).getY2(), 125);
    }

    @Test
    public void testShapeThatDoesNotOnEndOfSheetCanBeAddedToAlreadyCreatedSecondSheet(){
        List<Shape> shapes = new ArrayList();
        Shape shape1 = new Shape(50,100);
        Shape Shape = new Shape(70,100);
        Shape shape3 = new Shape(251,100);
        Shape shape4 = new Shape(251,100);
        shapes.add(shape1);
        shapes.add(Shape);
        shapes.add(shape3);
        shapes.add(shape4);
        List<Sheet> sheets = new BestFitGlassCuttingService().putShapesOnShelvesOnSheets(shapes);

        List<Shape> placed = sheets.get(0).getShapes();
        List<Shape> placed2 = sheets.get(1).getShapes();
        List<Space> spaces = sheets.get(0).getSpaces();


        assertEquals(placed.get(0).getX1(), 0);
        assertEquals(placed.get(0).getX2(), 50);
        assertEquals(placed.get(0).getY1(), 0);
        assertEquals(placed.get(0).getY2(), 100);

        assertEquals(placed.get(1).getX1(), 0);
        assertEquals(placed.get(1).getX2(), 70);
        assertEquals(placed.get(1).getY1(), 100);
        assertEquals(placed.get(1).getY2(), 200);

        assertEquals(placed2.get(0).getX1(), 0);
        assertEquals(placed2.get(0).getX2(), 251);
        assertEquals(placed2.get(0).getY1(), 0);
        assertEquals(placed2.get(0).getY2(), 100);

        assertEquals(placed2.get(1).getX1(), 0);
        assertEquals(placed2.get(1).getX2(), 251);
        assertEquals(placed2.get(1).getY1(), 100);
        assertEquals(placed2.get(1).getY2(), 200);
    }

    @Test
    public void testLastShapeFitsInSmallSpaceBetweenShapes(){
        List<Shape> shapes = new ArrayList();
        Shape shape1 = new Shape(50,125);
        Shape Shape2 = new Shape(100,100);
        Shape shape4 = new Shape(50,150);
        Shape shape5 = new Shape(20,50);
        shapes.add(shape1);
        shapes.add(Shape2);
        shapes.add(shape4);
        shapes.add(shape5);
        List<Sheet> sheets = new BestFitGlassCuttingService().putShapesOnShelvesOnSheets(shapes);

        List<Shape> placed = sheets.get(0).getShapes();
        List<Space> spaces = sheets.get(0).getSpaces();


        assertEquals(placed.get(0).getX1(), 0);
        assertEquals(placed.get(0).getX2(), 50);
        assertEquals(placed.get(0).getY1(), 0);
        assertEquals(placed.get(0).getY2(), 125);

        assertEquals(placed.get(1).getX1(), 0);
        assertEquals(placed.get(1).getX2(), 100);
        assertEquals(placed.get(1).getY1(), 125);
        assertEquals(placed.get(1).getY2(), 225);

        assertEquals(placed.get(2).getX1(), 100);
        assertEquals(placed.get(2).getX2(), 150);
        assertEquals(placed.get(2).getY1(), 0);
        assertEquals(placed.get(2).getY2(), 150);

        assertEquals(placed.get(3).getX1(), 50);
        assertEquals(placed.get(3).getX2(), 70);
        assertEquals(placed.get(3).getY1(), 0);
        assertEquals(placed.get(3).getY2(), 50);
    }
}
