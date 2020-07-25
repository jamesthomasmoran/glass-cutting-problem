package io.github.jamesthomasmoran.glasscuttingproblem.service;

import io.github.jamesthomasmoran.glasscuttingproblem.model.Shape;
import io.github.jamesthomasmoran.glasscuttingproblem.model.Sheet;
import io.github.jamesthomasmoran.glasscuttingproblem.model.Shelf;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FirstFitGlassCuttingServiceTest {

    @Autowired
    FirstFitGlassCuttingService firstFitGlassCuttingService;

    @Test
    public void test4ShapesAdded4thShapeFitsOnTopOf2ndShape(){
        List<Shape> shapes = new ArrayList<>();
        Shape shape1 = new Shape(20, 20);

        Shape shape2 = new Shape(20, 10);
        Shape shape3 = new Shape(20, 19);
        Shape shape4 = new Shape(20, 5);

        shapes.add(shape1);
        shapes.add(shape2);
        shapes.add(shape3);
        shapes.add(shape4);

        List<Sheet> sheets = firstFitGlassCuttingService.putShapesOnShelvesOnSheets(shapes);

        Shelf shelf = sheets.get(0).getShelves().get(0);

        assertEquals(0, shelf.getColumns().get(0).getShapes().get(0).getXLocation());
        assertEquals(0, shelf.getColumns().get(0).getShapes().get(0).getYLocation());
        assertEquals(1, shelf.getColumns().get(0).getShapes().get(0).getInsertionNumber());

        System.out.println(shelf.getColumns().get(1).getShapes());
        assertEquals(20, shelf.getColumns().get(1).getShapes().get(1).getXLocation());
        assertEquals(10, shelf.getColumns().get(1).getShapes().get(1).getYLocation());
        assertEquals(4, shelf.getColumns().get(1).getShapes().get(1).getInsertionNumber());

        assertEquals(20, shelf.getColumns().get(1).getShapes().get(0).getXLocation());
        assertEquals(0, shelf.getColumns().get(1).getShapes().get(0).getYLocation());
        assertEquals(2, shelf.getColumns().get(1).getShapes().get(0).getInsertionNumber());

        assertEquals(40, shelf.getColumns().get(2).getShapes().get(0).getXLocation());
        assertEquals(0, shelf.getColumns().get(2).getShapes().get(0).getYLocation());
        assertEquals(3, shelf.getColumns().get(2).getShapes().get(0).getInsertionNumber());

    }
    @Test
    public void test4ShapesAdded4thShapeFitsOnEndAsTooWideToGoOnTop(){
        List<Shape> shapes = new ArrayList<>();
        Shape shape1 = new Shape(20, 20);

        Shape shape2 = new Shape(20, 10);
        Shape shape3 = new Shape(20, 19);
        Shape shape4 = new Shape(30, 5);

        shapes.add(shape1);
        shapes.add(shape2);
        shapes.add(shape3);
        shapes.add(shape4);

        List<Sheet> sheets = firstFitGlassCuttingService.putShapesOnShelvesOnSheets(shapes);

        Shelf shelf = sheets.get(0).getShelves().get(0);

        assertEquals(0, shelf.getColumns().get(0).getShapes().get(0).getXLocation());
        assertEquals(0, shelf.getColumns().get(0).getShapes().get(0).getYLocation());
        assertEquals(1, shelf.getColumns().get(0).getShapes().get(0).getInsertionNumber());


        assertEquals(20, shelf.getColumns().get(1).getShapes().get(0).getXLocation());
        assertEquals(0, shelf.getColumns().get(1).getShapes().get(0).getYLocation());
        assertEquals(2, shelf.getColumns().get(1).getShapes().get(0).getInsertionNumber());

        assertEquals(40, shelf.getColumns().get(2).getShapes().get(0).getXLocation());
        assertEquals(0, shelf.getColumns().get(2).getShapes().get(0).getYLocation());
        assertEquals(3, shelf.getColumns().get(2).getShapes().get(0).getInsertionNumber());

        assertEquals(60, shelf.getColumns().get(3).getShapes().get(0).getXLocation());
        assertEquals(0, shelf.getColumns().get(3).getShapes().get(0).getYLocation());
        assertEquals(4, shelf.getColumns().get(3).getShapes().get(0).getInsertionNumber());

    }
    @Test
    public void test4ShapesAdded4thShapeFitsOnEndOf1stShelf(){
        List<Shape> shapes = new ArrayList<>();
        Shape shape1 = new Shape(20, 20);

        Shape shape2 = new Shape(20, 10);
        Shape shape3 = new Shape(20, 19);
        Shape shape4 = new Shape(20, 20);

        shapes.add(shape1);
        shapes.add(shape2);
        shapes.add(shape3);
        shapes.add(shape4);

        List<Sheet> sheets = firstFitGlassCuttingService.putShapesOnShelvesOnSheets(shapes);

        Shelf shelf = sheets.get(0).getShelves().get(0);
    System.out.println(shelf.getColumns().get(0).getShapes());
        assertEquals(0, shelf.getColumns().get(0).getShapes().get(0).getXLocation());
        assertEquals(0, shelf.getColumns().get(0).getShapes().get(0).getYLocation());
        assertEquals(1, shelf.getColumns().get(0).getShapes().get(0).getInsertionNumber());

        assertEquals(20, shelf.getColumns().get(1).getShapes().get(0).getXLocation());
        assertEquals(0, shelf.getColumns().get(1).getShapes().get(0).getYLocation());
        assertEquals(2, shelf.getColumns().get(1).getShapes().get(0).getInsertionNumber());

        assertEquals(40, shelf.getColumns().get(2).getShapes().get(0).getXLocation());
        assertEquals(0, shelf.getColumns().get(2).getShapes().get(0).getYLocation());
        assertEquals(3, shelf.getColumns().get(2).getShapes().get(0).getInsertionNumber());

        assertEquals(60, shelf.getColumns().get(3).getShapes().get(0).getXLocation());
        assertEquals(0, shelf.getColumns().get(3).getShapes().get(0).getYLocation());
        assertEquals(4, shelf.getColumns().get(3).getShapes().get(0).getInsertionNumber());
    }

    @Test
    public void test4ShapesAdded4thShapeDoesntFitOnShelfAsTooTall(){
        List<Shape> shapes = new ArrayList<>();
        Shape shape1 = new Shape(20, 20);

        Shape shape2 = new Shape(20, 10);
        Shape shape3 = new Shape(20, 19);
        Shape shape4 = new Shape(20, 50);

        shapes.add(shape1);
        shapes.add(shape2);
        shapes.add(shape3);
        shapes.add(shape4);

        List<Sheet> sheets = firstFitGlassCuttingService.putShapesOnShelvesOnSheets(shapes);

        Shelf shelf = sheets.get(0).getShelves().get(0);

        Shelf shelf2 = sheets.get(0).getShelves().get(1);


        assertEquals(0, shelf.getColumns().get(0).getShapes().get(0).getXLocation());
        assertEquals(0, shelf.getColumns().get(0).getShapes().get(0).getYLocation());
        assertEquals(1, shelf.getColumns().get(0).getShapes().get(0).getInsertionNumber());

        assertEquals(20, shelf.getColumns().get(1).getShapes().get(0).getXLocation());
        assertEquals(0, shelf.getColumns().get(1).getShapes().get(0).getYLocation());
        assertEquals(2, shelf.getColumns().get(1).getShapes().get(0).getInsertionNumber());

        assertEquals(40, shelf.getColumns().get(2).getShapes().get(0).getXLocation());
        assertEquals(0, shelf.getColumns().get(2).getShapes().get(0).getYLocation());
        assertEquals(3, shelf.getColumns().get(2).getShapes().get(0).getInsertionNumber());

        assertEquals(0, shelf2.getColumns().get(0).getShapes().get(0).getXLocation());
        assertEquals(0, shelf2.getColumns().get(0).getShapes().get(0).getYLocation());
        assertEquals(4, shelf2.getColumns().get(0).getShapes().get(0).getInsertionNumber());
    }

    @Test
    public void test4ShapesAdded4thShapeDoesntFitOnShelfAsTooWide(){
        List<Shape> shapes = new ArrayList<>();
        Shape shape1 = new Shape(20, 20);

        Shape shape2 = new Shape(20, 10);
        Shape shape3 = new Shape(20, 19);
        Shape shape4 = new Shape(280, 10);

        shapes.add(shape1);
        shapes.add(shape2);
        shapes.add(shape3);
        shapes.add(shape4);

        List<Sheet> sheets = firstFitGlassCuttingService.putShapesOnShelvesOnSheets(shapes);

        Shelf shelf = sheets.get(0).getShelves().get(0);

        Shelf shelf2 = sheets.get(0).getShelves().get(1);


        assertEquals(0, shelf.getColumns().get(0).getShapes().get(0).getXLocation());
        assertEquals(0, shelf.getColumns().get(0).getShapes().get(0).getYLocation());
        assertEquals(1, shelf.getColumns().get(0).getShapes().get(0).getInsertionNumber());

        assertEquals(20, shelf.getColumns().get(1).getShapes().get(0).getXLocation());
        assertEquals(0, shelf.getColumns().get(1).getShapes().get(0).getYLocation());
        assertEquals(2, shelf.getColumns().get(1).getShapes().get(0).getInsertionNumber());

        assertEquals(40, shelf.getColumns().get(2).getShapes().get(0).getXLocation());
        assertEquals(0, shelf.getColumns().get(2).getShapes().get(0).getYLocation());
        assertEquals(3, shelf.getColumns().get(2).getShapes().get(0).getInsertionNumber());

        assertEquals(0, shelf2.getColumns().get(0).getShapes().get(0).getXLocation());
        assertEquals(0, shelf2.getColumns().get(0).getShapes().get(0).getYLocation());
        assertEquals(4, shelf2.getColumns().get(0).getShapes().get(0).getInsertionNumber());
    }

    @Test
    public void test4ShapesAdded3rdShapeDoesntFitOnSheetAsTooTall(){
        List<Shape> shapes = new ArrayList<>();
        Shape shape1 = new Shape(20, 50);

        Shape shape2 = new Shape(20, 10);
        Shape shape3 = new Shape(20, 240);
        Shape shape4 = new Shape(20, 20);

        shapes.add(shape1);
        shapes.add(shape2);
        shapes.add(shape3);
        shapes.add(shape4);

        List<Sheet> sheets = firstFitGlassCuttingService.putShapesOnShelvesOnSheets(shapes);

        Shelf shelf = sheets.get(0).getShelves().get(0);

        Shelf shelf2 = sheets.get(1).getShelves().get(0);


        assertEquals(0, shelf.getColumns().get(0).getShapes().get(0).getXLocation());
        assertEquals(0, shelf.getColumns().get(0).getShapes().get(0).getYLocation());
        assertEquals(1, shelf.getColumns().get(0).getShapes().get(0).getInsertionNumber());

        assertEquals(20, shelf.getColumns().get(1).getShapes().get(0).getXLocation());
        assertEquals(0, shelf.getColumns().get(1).getShapes().get(0).getYLocation());
        assertEquals(2, shelf.getColumns().get(1).getShapes().get(0).getInsertionNumber());

        assertEquals(0, shelf2.getColumns().get(0).getShapes().get(0).getXLocation());
        assertEquals(0, shelf2.getColumns().get(0).getShapes().get(0).getYLocation());
        assertEquals(3, shelf2.getColumns().get(0).getShapes().get(0).getInsertionNumber());

        assertEquals(20, shelf.getColumns().get(1).getShapes().get(1).getXLocation());
        assertEquals(10, shelf.getColumns().get(1).getShapes().get(1).getYLocation());
        assertEquals(4, shelf.getColumns().get(1).getShapes().get(1).getInsertionNumber());
    }

    @Test
    public void test6ShapesAdded6thShapeFitsOn2ndShelf(){
        List<Shape> shapes = new ArrayList<>();
        Shape shape1 = new Shape(20, 50);

        Shape shape2 = new Shape(20, 10);
        Shape shape3 = new Shape(20, 100);
        Shape shape4 = new Shape(290, 20);
        Shape shape5 = new Shape(20, 10);
        Shape shape6 = new Shape(20, 60);

        shapes.add(shape1);
        shapes.add(shape2);
        shapes.add(shape3);
        shapes.add(shape4);
        shapes.add(shape5);
        shapes.add(shape6);

        List<Sheet> sheets = firstFitGlassCuttingService.putShapesOnShelvesOnSheets(shapes);

        Shelf shelf = sheets.get(0).getShelves().get(0);

        Shelf shelf2 = sheets.get(0).getShelves().get(1);

        Shelf shelf3 = sheets.get(0).getShelves().get(2);


        assertEquals(0, shelf.getColumns().get(0).getShapes().get(0).getXLocation());
        assertEquals(0, shelf.getColumns().get(0).getShapes().get(0).getYLocation());
        assertEquals(1, shelf.getColumns().get(0).getShapes().get(0).getInsertionNumber());

        assertEquals(20, shelf.getColumns().get(1).getShapes().get(0).getXLocation());
        assertEquals(0, shelf.getColumns().get(1).getShapes().get(0).getYLocation());
        assertEquals(2, shelf.getColumns().get(1).getShapes().get(0).getInsertionNumber());

        assertEquals(0, shelf2.getColumns().get(0).getShapes().get(0).getXLocation());
        assertEquals(0, shelf2.getColumns().get(0).getShapes().get(0).getYLocation());
        assertEquals(3, shelf2.getColumns().get(0).getShapes().get(0).getInsertionNumber());

        assertEquals(0, shelf3.getColumns().get(0).getShapes().get(0).getXLocation());
        assertEquals(0, shelf3.getColumns().get(0).getShapes().get(0).getYLocation());
        assertEquals(4, shelf3.getColumns().get(0).getShapes().get(0).getInsertionNumber());

        assertEquals(20, shelf.getColumns().get(1).getShapes().get(1).getXLocation());
        assertEquals(10, shelf.getColumns().get(1).getShapes().get(1).getYLocation());
        assertEquals(5, shelf.getColumns().get(1).getShapes().get(1).getInsertionNumber());

        assertEquals(20, shelf2.getColumns().get(1).getShapes().get(0).getXLocation());
        assertEquals(0, shelf2.getColumns().get(1).getShapes().get(0).getYLocation());
        assertEquals(6, shelf2.getColumns().get(1).getShapes().get(0).getInsertionNumber());
    }

    @Test
    public void testListOf21Shapes20FitOnOneShelfNextToEachOther1FitsOnNewShelfAsTooManyShapes(){
        List<Shape> shapes = new ArrayList<>();

        Shape shape2 = new Shape(10, 20);
        for(int i = 0; i < 20; i++){
            shapes.add(new Shape(10, 20));
        }
        shapes.add(shape2);
        System.out.println(shapes.size());
        List<Sheet> sheets = firstFitGlassCuttingService.putShapesOnShelvesOnSheets(shapes);

        Shelf shelf2 = sheets.get(0).getShelves().get(1);

        assertEquals(2, sheets.get(0).getShelves().size());

        assertEquals(0, shelf2.getColumns().get(0).getShapes().get(0).getXLocation());
        assertEquals(0, shelf2.getColumns().get(0).getShapes().get(0).getYLocation());
    }

}
