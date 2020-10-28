package uk.co.jimbodev.glasscuttingproblem.service;

import uk.co.jimbodev.glasscuttingproblem.to.Shape;
import uk.co.jimbodev.glasscuttingproblem.to.Sheet;
import uk.co.jimbodev.glasscuttingproblem.to.Space;

import java.util.ArrayList;
import java.util.List;

public class BestFitGlassCuttingService extends GlassCuttingService {

    public static final int FIRST_INSERTION_NUMBER = 1;
    public static final int START_INDEX = 0;

    @Override
    public List<Sheet> putShapesOnShelvesOnSheets(List<Shape> shapes) {

        List<Sheet> sheets = new ArrayList();
        sheets.add(new Sheet());

        int insertionNumber = FIRST_INSERTION_NUMBER;
        for(Shape shape : shapes){
            shape.setInsertionNumber(insertionNumber);
            insertionNumber++;
            Sheet sheet = fitShape(shape, sheets);

            if(sheet == null){
                sheet = new Sheet();
                shape.setX2(shape.getWidth());
                shape.setY2(shape.getHeight());
                sheet.addShape(shape);
                sheets.add(sheet);
            }
                sheet.setSpaces(updateSpacesOnPlacedSheet(sheet, shape));
        }
        return sheets;
    }

    private Sheet fitShape(Shape shape, List<Sheet> sheets){
        int bestFitSheetIndex = START_INDEX;
        Shape bestFitShape = null;
        int bestFitAreaRemaining = Sheet.SHEET_HEIGHT * Sheet.SHEET_WIDTH + 1;
        for(int i = 0; i < sheets.size(); i++){
            for(Space space : sheets.get(i).getSpaces()) {
                if (shapeFitsInSpace(shape, space)) {

                    int areaLeftInSpace = areaRemainingAfterShapePlacedInSpace(shape, space);
                    if(areaLeftInSpace < bestFitAreaRemaining){
                        bestFitAreaRemaining = areaLeftInSpace;
                        bestFitSheetIndex = i;
                        setShapeXYCoordinates(shape, space);
                        bestFitShape = shape;
                    }
                }
            }
        }
        if(bestFitShape != null){
            sheets.get(bestFitSheetIndex).addShape(bestFitShape);
            return sheets.get(bestFitSheetIndex);
        }
        return null;
    }

    private int areaRemainingAfterShapePlacedInSpace(Shape shape, Space space){
        return ((space.getWidth() - shape.getWidth()) * shape.getHeight())
                + ((space.getHeight() - shape.getHeight()) * shape.getWidth());
    }

}
