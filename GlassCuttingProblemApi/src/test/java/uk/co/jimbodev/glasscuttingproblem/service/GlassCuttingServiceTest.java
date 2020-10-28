package uk.co.jimbodev.glasscuttingproblem.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import uk.co.jimbodev.glasscuttingproblem.to.Shape;
import uk.co.jimbodev.glasscuttingproblem.to.Sheet;

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
        List<Sheet> sheets = new ArrayList();
        sheets.add(new Sheet());
        sheets.add(new Sheet());
        assertEquals(2, glassCuttingService.getNumberOfSheets(sheets));
    }

    @Test
    public void testGetSpaceEfficiencyPercentage(){
        List<Sheet> sheets = new ArrayList();
        Sheet sheet1 = new Sheet();
        Sheet sheet2 = new Sheet();


        Shape shape1 = new Shape(50, 100);
        Shape shape2 = new Shape(100, 50);
        Shape shape3 = new Shape(50, 50);

        sheet1.addShape(shape1);
        sheet1.addShape(shape2);
        sheet1.addShape(shape3);

        sheet2.addShape(shape3);

        sheets.add(sheet1);
        sheets.add(sheet2);


        assertEquals(0.1, glassCuttingService.getSpaceEfficiencyPercentage(sheets));
    }
}
