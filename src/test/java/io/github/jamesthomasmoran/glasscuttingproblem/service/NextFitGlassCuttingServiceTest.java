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
public class NextFitGlassCuttingServiceTest {

    @Autowired
    NextFitGlassCuttingService nextFitGlassCuttingService;

    @Test
    public void testListOf3ShapesCanBeStackedOnEachOther(){
        List<Shape> shapes = new ArrayList<>();
        Shape shape1 = new Shape(20, 50);

        Shape shape2 = new Shape(20, 10);
        Shape shape3 = new Shape(20, 15);
        Shape shape4 = new Shape(20, 20);

        shapes.add(shape1);
        shapes.add(shape2);
        shapes.add(shape3);
        shapes.add(shape4);

        List<Sheet> sheets = nextFitGlassCuttingService.putShapesOnShelvesOnSheets(shapes);

        Shelf shelf = sheets.get(0).getShelves().get(0);

        assertEquals(0, shelf.getColumns().get(0).getShapes().get(0).getXLocation());
        assertEquals(0, shelf.getColumns().get(0).getShapes().get(0).getYLocation());

        assertEquals(20, shelf.getColumns().get(1).getShapes().get(0).getXLocation());
        assertEquals(0, shelf.getColumns().get(1).getShapes().get(0).getYLocation());

        assertEquals(20, shelf.getColumns().get(1).getShapes().get(1).getXLocation());
        assertEquals(10, shelf.getColumns().get(1).getShapes().get(1).getYLocation());

        assertEquals(20, shelf.getColumns().get(1).getShapes().get(2).getXLocation());
        assertEquals(25, shelf.getColumns().get(1).getShapes().get(2).getYLocation());
    }

    @Test
    public void testListOf3ShapesFitOnOneShelfNextToEachOther(){
        List<Shape> shapes = new ArrayList<>();
        Shape shape1 = new Shape(20, 20);

        Shape shape2 = new Shape(20, 10);
        Shape shape3 = new Shape(20, 15);
        Shape shape4 = new Shape(20, 20);

        shapes.add(shape1);
        shapes.add(shape2);
        shapes.add(shape3);
        shapes.add(shape4);

        List<Sheet> sheets = nextFitGlassCuttingService.putShapesOnShelvesOnSheets(shapes);
        Shelf shelf = sheets.get(0).getShelves().get(0);

        System.out.println(sheets.get(0).getShelves().get(0).getColumns().get(1).getShapes().size());

        assertEquals(0, shelf.getColumns().get(0).getShapes().get(0).getXLocation());
        assertEquals(0, shelf.getColumns().get(0).getShapes().get(0).getYLocation());

        assertEquals(20,shelf.getColumns().get(1).getShapes().get(0).getXLocation());
        assertEquals(0, shelf.getColumns().get(1).getShapes().get(0).getYLocation());

        assertEquals(0, shelf.getColumns().get(2).getShapes().get(0).getYLocation());
        assertEquals(40, shelf.getColumns().get(2).getShapes().get(0).getXLocation());


        assertEquals(60, shelf.getColumns().get(3).getShapes().get(0).getXLocation());
        assertEquals(0, shelf.getColumns().get(3).getShapes().get(0).getYLocation());
    }

    @Test
    public void testListOf3Shapes2FitOnOneShelfNextToEachOther1FitsOnShelfBelowAsTooTall(){
        List<Shape> shapes = new ArrayList<>();
        Shape shape1 = new Shape(20, 20);

        Shape shape2 = new Shape(20, 10);
        Shape shape3 = new Shape(20, 15);
        Shape shape4 = new Shape(20, 50);

        shapes.add(shape1);
        shapes.add(shape2);
        shapes.add(shape3);
        shapes.add(shape4);

        List<Sheet> sheets = nextFitGlassCuttingService.putShapesOnShelvesOnSheets(shapes);

        Shelf shelf1 = sheets.get(0).getShelves().get(0);
        Shelf shelf2 = sheets.get(0).getShelves().get(1);

        assertEquals(0, shelf1.getColumns().get(0).getShapes().get(0).getXLocation());
        assertEquals(0, shelf1.getColumns().get(0).getShapes().get(0).getYLocation());

        assertEquals(20, shelf1.getColumns().get(1).getShapes().get(0).getXLocation());
        assertEquals(0, shelf1.getColumns().get(1).getShapes().get(0).getYLocation());

        assertEquals(0, shelf1.getColumns().get(2).getShapes().get(0).getYLocation());
        assertEquals(40, shelf1.getColumns().get(2).getShapes().get(0).getXLocation());

        assertEquals(50, shelf2.getHeight());
        assertEquals(0, shelf2.getColumns().get(0).getShapes().get(0).getXLocation());
        assertEquals(0, shelf2.getColumns().get(0).getShapes().get(0).getYLocation());
    }

    @Test
    public void testListOf3Shapes2FitOnOneShelfNextToEachOther1FitsOnShelfBelowAsTooWide(){
        List<Shape> shapes = new ArrayList<>();
        Shape shape1 = new Shape(20, 20);

        Shape shape2 = new Shape(20, 10);
        Shape shape3 = new Shape(20, 15);
        Shape shape4 = new Shape(290, 15);

        shapes.add(shape1);
        shapes.add(shape2);
        shapes.add(shape3);
        shapes.add(shape4);

        List<Sheet> sheets = nextFitGlassCuttingService.putShapesOnShelvesOnSheets(shapes);

        Shelf shelf1 = sheets.get(0).getShelves().get(0);
        Shelf shelf2 = sheets.get(0).getShelves().get(1);

        assertEquals(0, shelf1.getColumns().get(0).getShapes().get(0).getXLocation());
        assertEquals(0, shelf1.getColumns().get(0).getShapes().get(0).getYLocation());

        assertEquals(20, shelf1.getColumns().get(1).getShapes().get(0).getXLocation());
        assertEquals(0, shelf1.getColumns().get(1).getShapes().get(0).getYLocation());

        assertEquals(0, shelf1.getColumns().get(2).getShapes().get(0).getYLocation());
        assertEquals(40, shelf1.getColumns().get(2).getShapes().get(0).getXLocation());

        assertEquals(290, shelf2.getWidth());
        assertEquals(0, shelf2.getColumns().get(0).getShapes().get(0).getXLocation());
        assertEquals(0, shelf2.getColumns().get(0).getShapes().get(0).getYLocation());
    }

    @Test
    public void testListOf3Shapes2FitOnOneShelfNextToEachOther1FitsOnNewSheetAsTooTall(){
        List<Shape> shapes = new ArrayList<>();
        Shape shape1 = new Shape(20, 20);

        Shape shape2 = new Shape(20, 10);
        Shape shape3 = new Shape(20, 15);
        Shape shape4 = new Shape(15, 240);

        shapes.add(shape1);
        shapes.add(shape2);
        shapes.add(shape3);
        shapes.add(shape4);

        List<Sheet> sheets = nextFitGlassCuttingService.putShapesOnShelvesOnSheets(shapes);


        Shelf shelf1 = sheets.get(0).getShelves().get(0);
        Shelf shelf2 = sheets.get(1).getShelves().get(0);

        assertEquals(2, sheets.size());

        assertEquals(0, shelf1.getColumns().get(0).getShapes().get(0).getXLocation());
        assertEquals(0, shelf1.getColumns().get(0).getShapes().get(0).getYLocation());

        assertEquals(20, shelf1.getColumns().get(1).getShapes().get(0).getXLocation());
        assertEquals(0, shelf1.getColumns().get(1).getShapes().get(0).getYLocation());

        assertEquals(0, shelf1.getColumns().get(2).getShapes().get(0).getYLocation());
        assertEquals(40, shelf1.getColumns().get(2).getShapes().get(0).getXLocation());

        assertEquals(240, shelf2.getHeight());
        assertEquals(0, shelf2.getColumns().get(0).getShapes().get(0).getXLocation());
        assertEquals(0, shelf2.getColumns().get(0).getShapes().get(0).getYLocation());
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
        List<Sheet> sheets = nextFitGlassCuttingService.putShapesOnShelvesOnSheets(shapes);

        Shelf shelf2 = sheets.get(0).getShelves().get(1);

        assertEquals(2, sheets.get(0).getShelves().size());

        assertEquals(0, shelf2.getColumns().get(0).getShapes().get(0).getXLocation());
        assertEquals(0, shelf2.getColumns().get(0).getShapes().get(0).getYLocation());
    }
}
