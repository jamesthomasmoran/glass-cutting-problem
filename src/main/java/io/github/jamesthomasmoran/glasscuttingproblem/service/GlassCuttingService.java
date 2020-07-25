package io.github.jamesthomasmoran.glasscuttingproblem.service;

import io.github.jamesthomasmoran.glasscuttingproblem.model.Column;
import io.github.jamesthomasmoran.glasscuttingproblem.model.Shape;
import io.github.jamesthomasmoran.glasscuttingproblem.model.Sheet;
import io.github.jamesthomasmoran.glasscuttingproblem.model.Shelf;
import org.decimal4j.util.DoubleRounder;

import java.util.List;

public abstract class GlassCuttingService implements IGlassCuttingService {

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
            for(Shelf shelf : sheet.getShelves()){
                for(Column column : shelf.getColumns()){
                    for(Shape shape : column.getShapes()) {
                        double shapeArea = shape.getHeight() * shape.getWidth();
                        totalFilledArea += shapeArea;
                    }
                }
            }
        }
        return totalFilledArea;
    }


    Shape fitsHeight(Shelf shelf, Shape newShape, Shape placedShape){
        int combinedHeight = newShape.getHeight();
        int placedShapeTop = 0;

        if(placedShape != null){
            placedShapeTop = placedShape.getYLocation() + placedShape.getHeight();
            combinedHeight = newShape.getHeight() + placedShapeTop;
        }

        if(shelf.getHeight() - combinedHeight < 0 && shelf.getHeight() != 0){
            newShape.setValidHeight(false);
            return newShape;
        }
        newShape.setYLocation(placedShapeTop);
        newShape.setValidHeight(true);


         return newShape;
    }

    Shape fitsWidth(Shelf shelf, Shape shape, Shape placedShape, Shape nextPlacedShape){


        int combinedWidth = shape.getWidth();
        int xLocation = shelf.getWidth();

        if(placedShape != null){
            int difference = shape.getWidth() - placedShape.getWidth();
            if(difference > 0){
                if(nextPlacedShape == null){
                    xLocation = placedShape.getXLocation();
                }
                combinedWidth = difference;
            }
            else{
                combinedWidth = 0;
                xLocation = placedShape.getXLocation();
            }
        }

        if(nextPlacedShape != null){
            if(combinedWidth != 0){
                shape.setValidWidth(false);
                return shape;
            }
            else{
                xLocation = placedShape.getXLocation();
            }
        }

        if(shelf.getWidth() + combinedWidth > Sheet.SHEET_WIDTH){
            shape.setValidWidth(false);
            return shape;
        }

        shape.setCombinedWidth(combinedWidth);
        shape.setXLocation(xLocation);
        shape.setValidWidth(true);

        return shape;
    }

    Shape lessThanMaxShapesPerShelf(Shelf shelf, Shape shape){
        if (getNumberOfShapesOnShelf(shelf) < Sheet.SHAPE_LIMIT){
            shape.setValidShapeNumber(true);
        }

        return shape;
    }

    private int getNumberOfShapesOnShelf(Shelf shelf){
        int total = 0;

        for(Column column : shelf.getColumns()){
            total += column.getShapes().size();
        }
        return total;
    }

    Shape shapeFitsOnNewShelfOnCurrentSheet(Sheet sheet, Shape shape){
        if(sheet.allShelvesHeight() + shape.getHeight() <= Sheet.SHEET_HEIGHT){
            setShapeCoordinatesToZero(shape);
            shape.setValid(true);
        }
        return shape;
    }

    Shape setShapeCoordinatesToZero(Shape shape){
        shape.setXLocation(0);
        shape.setYLocation(0);
        shape.setValid(true);
        return shape;
    }
}
