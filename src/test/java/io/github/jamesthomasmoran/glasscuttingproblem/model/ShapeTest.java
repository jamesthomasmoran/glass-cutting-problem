package io.github.jamesthomasmoran.glasscuttingproblem.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShapeTest {

    @Test
    public void testConstructorThrowsExceptionWhenWidthGreaterThanMaximum(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Shape(Sheet.SHEET_WIDTH + 1, Sheet.SHEET_HEIGHT);
        });
    }

    @Test
    public void testConstructorThrowsExceptionWhenHeightGreaterThanMaximum(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Shape(Sheet.SHEET_WIDTH, Sheet.SHEET_HEIGHT + 1);
        });
    }

    @Test
    public void testConstructorSetsCombinedWidthToWidthWithValidParameters(){
        Shape shape = new Shape(20, 20);

        assertEquals(20, shape.getCombinedWidth());
    }

}
