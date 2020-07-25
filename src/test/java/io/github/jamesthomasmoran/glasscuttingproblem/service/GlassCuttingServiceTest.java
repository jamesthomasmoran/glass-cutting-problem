package io.github.jamesthomasmoran.glasscuttingproblem.service;

import io.github.jamesthomasmoran.glasscuttingproblem.model.Column;
import io.github.jamesthomasmoran.glasscuttingproblem.model.Shape;
import io.github.jamesthomasmoran.glasscuttingproblem.model.Sheet;
import io.github.jamesthomasmoran.glasscuttingproblem.model.Shelf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlassCuttingServiceTest {

    private GlassCuttingService glassCuttingService;

    @BeforeEach
    public void setup(){
        glassCuttingService = Mockito.mock(
                GlassCuttingService.class,
                Mockito.CALLS_REAL_METHODS);
    }

    @Test
    public void testGetNumberOfSheetsReturnsNumberOfSheetsInList(){
        List<Sheet> sheets = new ArrayList<>();
        sheets.add(new Sheet());
        sheets.add(new Sheet());
        assertEquals(2, glassCuttingService.getNumberOfSheets(sheets));
    }

    @Test
    public void testGetSpaceEfficiencyPercentage(){
        List<Sheet> sheets = new ArrayList<>();
        Sheet sheet1 = new Sheet();
        Sheet sheet2 = new Sheet();

        Shelf shelf1 = new Shelf();
        Shelf shelf2 = new Shelf();


        shelf1.addColumn();
        shelf1.addColumn();
        shelf1.addColumn();

        shelf2.addColumn();
        shelf2.addColumn();

        Shape shape1 = new Shape(50, 100);
        Shape shape2 = new Shape(100, 50);
        Shape shape3 = new Shape(50, 50);

        shelf1.place(shape1, 0);
        shelf1.place(shape2, 0);
        shelf1.place(shape3, 0);

        shelf2.place(shape1, 1);
        shelf2.place(shape2, 1);

        sheet1.addShelf(shelf1);
        sheet1.addShelf(shelf2);

        sheet2.addShelf(shelf2);
        sheet2.addShelf(shelf1);

        sheets.add(sheet1);
        sheets.add(sheet2);


        assertEquals(0.3, glassCuttingService.getSpaceEfficiencyPercentage(sheets));
    }

    @Test
    public void testSetShapeCoordinatesToZero(){
        Shape shape = new Shape(20, 20);
        shape = glassCuttingService.setShapeCoordinatesToZero(shape);

        assertEquals(0, shape.getXLocation());
        assertEquals(0, shape.getYLocation());
        assertEquals(true, shape.isValid());
    }

    @Test
    public void testShapeFitsOnNewShelfOnCurrentSheetSetsShapeIsValidToFalseWhenItCannotFitOnSheetInNewShelf(){
        Shape shape = new Shape(200,200);
        Shelf shelf = new Shelf();
        shelf.addColumn();
        shelf.place(shape, 0);
        Sheet sheet = new Sheet();
        sheet.addShelf(shelf);
        shape = glassCuttingService.shapeFitsOnNewShelfOnCurrentSheet(sheet, shape);

        assertEquals(false, shape.isValid());
    }

    @Test
    public void testShapeFitsOnNewShelfOnCurrentSheetSetsShapeIsValidToTrueAndZerosCoordinates(){
        Shape shape = new Shape(200,100);
        Shelf shelf = new Shelf();
        shelf.addColumn();
        shelf.place(shape, 0);
        Sheet sheet = new Sheet();
        sheet.addShelf(shelf);
        shape = glassCuttingService.shapeFitsOnNewShelfOnCurrentSheet(sheet, shape);

        assertEquals(0, shape.getXLocation());
        assertEquals(0, shape.getYLocation());
        assertEquals(true, shape.isValid());
    }

    @Test
    public void testLessThanMaxShapesPerShelfSetsValidToFalseWhenHas20Shapes(){
        Shelf shelf = new Shelf();
        Shape shape = new Shape(10, 10);

        for(int i = 0; i < 20; i++){
            shelf.addColumn();
            shelf.place(shape, i);
        }

        Shape newShape = new Shape(5,5);

        assertEquals(false, glassCuttingService.lessThanMaxShapesPerShelf(shelf, newShape).isValidShapeNumber());
    }

    @Test
    public void testLessThanMaxShapesPerShelfSetsValidToTrueWhenHas19Shapes(){
        Shelf shelf = new Shelf();
        Shape shape = new Shape(10, 10);

        for(int i = 0; i < 19; i++){
            shelf.addColumn();
            shelf.place(shape, i);
        }

        Shape newShape = new Shape(5,5);

        assertEquals(true, glassCuttingService.lessThanMaxShapesPerShelf(shelf, newShape).isValidShapeNumber());
    }

    @Test
    public void testFitsHeightWithShapeThatFitsHeightWithoutPlacedShapeSetsYTo0ValidToTrue(){
        Shelf shelf = new Shelf();
        Shape shape = new Shape(20, 20);

        shape = glassCuttingService.fitsHeight(shelf, shape, null);

        assertEquals(0, shape.getYLocation());
        assertEquals(true, shape.isValidHeight());
    }

    @Test
    public void testFitsHeightWithShapeThatDoesNotFitHeightWithoutPlacedShapeSetsValidToFalse(){
        Shelf shelf = new Shelf();
        Shape shape1 = new Shape(20, 20);
        shelf.addColumn();
        shelf.place(shape1, 0);
        Shape shape2 = new Shape(20, 21);
        shape2 = glassCuttingService.fitsHeight(shelf, shape2, null);

        assertEquals(false, shape2.isValidHeight());
    }

    @Test
    public void testFitsHeightWithShapeThatFitsHeightWithPlacedShapeSetsYToTopOfPlacedShapeValidToTrue(){
        Shelf shelf = new Shelf();
        Shape shape1 = new Shape(20, 20);
        shelf.addColumn();
        shelf.place(shape1, 0);
        Shape shape2 = new Shape(20, 10);
        shelf.addColumn();
        shelf.place(shape2, 1);
        Shape shape3 = new Shape(20, 5);
        shape3 = glassCuttingService.fitsHeight(shelf, shape3, shape2);

        assertEquals(10, shape3.getYLocation());
        assertEquals(true, shape3.isValidHeight());
    }

    @Test
    public void testFitsHeightWithShapeThatDoesNotFitHeightWithPlacedShapeSetsValidToFalse(){
        Shelf shelf = new Shelf();
        Shape shape1 = new Shape(20, 20);
        shelf.place(shape1, 0);
        Shape shape2 = new Shape(20, 10);
        shelf.addColumn();
        shelf.place(shape2, 1);
        Shape shape3 = new Shape(20, 11);
        shape3 = glassCuttingService.fitsHeight(shelf, shape3, shape2);

        assertEquals(false, shape3.isValidHeight());
    }

    @Test
    public void testFitsWidthWithShapeThatDoesFitWidthWithPlacedShapeAndNoNextPlacedShapeSetsXToStartOfPlaceShapeValidToTrue(){
        Shelf shelf = new Shelf();

        Shape shape1 = new Shape(20, 10);
        shape1.setXLocation(20);
        shelf.place(shape1, 0);
        Shape shape2 = new Shape(20, 10);
        shape2 = glassCuttingService.fitsWidth(shelf, shape2, shape1, null);

        assertEquals(20, shape2.getXLocation());
        assertEquals(true, shape2.isValidWidth());
    }

    @Test
    public void testFitsWidthWithShapeThatDoesFitWidthWithPlacedShapeAndNextPlacedShapeSetsXToStartOfPlaceShapeValidToTrue(){
        Shelf shelf = new Shelf();

        Shape shape1 = new Shape(20, 10);
        shape1.setXLocation(20);
        shelf.place(shape1, 0);
        Shape shape2 = new Shape(20, 10);

        Shape shape3 = new Shape(20,10);
        shape3.setXLocation(40);
        shape2 = glassCuttingService.fitsWidth(shelf, shape2, shape1, shape3);

        assertEquals(20, shape2.getXLocation());
        assertEquals(true, shape2.isValidWidth());
    }
    @Test
    public void testFitsWidthWithShapeThatDoesFitWidthWithPlacedShapeButIsLongerAndNoNextPlacedShapeSetsXToStartOfPlaceShapeValidToTrue(){
        Shelf shelf = new Shelf();

        Shape shape1 = new Shape(20, 10);
        shape1.setXLocation(20);

        shelf.place(shape1, 0);
        Shape shape2 = new Shape(50, 10);
        shape2 = glassCuttingService.fitsWidth(shelf, shape2, shape1, null);

        assertEquals(20, shape2.getXLocation());
        assertEquals(true, shape2.isValidWidth());
    }

    @Test
    public void testFitsWidthWithShapeThatDoesFitWidthWithPlacedShapeButIsLongerAndNextPlacedShapeSetsValidToFalse(){
        Shelf shelf = new Shelf();

        Shape shape1 = new Shape(20, 10);
        shape1.setXLocation(20);

        shelf.place(shape1, 0);
        Shape shape2 = new Shape(30, 10);

        Shape shape3 = new Shape(50,10);
        shape3.setXLocation(40);
        shape2 = glassCuttingService.fitsWidth(shelf, shape2, shape1, shape3);


        assertEquals(false, shape2.isValidWidth());
    }

    @Test
    public void testFitsWidthWithShapeThatDoesNotFitValidToFalse(){
        Shelf shelf = new Shelf();

        Shape shape1 = new Shape(51, 10);
        shape1.setXLocation(0);
        shelf.place(shape1, 0);
        Shape shape2 = new Shape(51,10);
        shape2.setXLocation(51);
        shelf.addColumn();
        shelf.place(shape2, 1);
        Shape shape3 = new Shape(300, 10);


        shape2 = glassCuttingService.fitsWidth(shelf, shape3, shape2, null);


        assertEquals(false, shape3.isValidWidth());
    }

}
