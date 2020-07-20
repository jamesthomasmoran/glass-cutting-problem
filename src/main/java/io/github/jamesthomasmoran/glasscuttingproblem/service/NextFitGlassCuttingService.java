package io.github.jamesthomasmoran.glasscuttingproblem.service;

import io.github.jamesthomasmoran.glasscuttingproblem.model.Shape;
import io.github.jamesthomasmoran.glasscuttingproblem.model.Sheet;
import io.github.jamesthomasmoran.glasscuttingproblem.model.Shelf;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NextFitGlassCuttingService extends GlassCuttingService {

    public NextFitGlassCuttingService(){
        super();
    }

    @Override
    public List<Sheet> putShapesOnShelvesOnSheets(List<Shape> shapes) {

        List<Sheet> usedSheets = new ArrayList<Sheet>();

        Sheet sheet=new Sheet();

        Shelf shelf= new Shelf();

        sheet.addShelf(shelf);

        for(Shape shape : shapes) {

            if(shapeFitsOnShelfAbovePreviousShape(shelf, shape).isValid()) {

                shape = shapeFitsOnShelfAbovePreviousShape(shelf, shape);
                shelf.place(shape);

            }
            else if(shapeFitsOnShelfRightOfPreviousShape(shelf, shape).isValid()) {

                shape = shapeFitsOnShelfRightOfPreviousShape(shelf, shape);
                shelf.place(shape);
            }
            else if(shapeFitsOnNewShelfOnCurrentSheet(sheet, shape).isValid()) {

                shelf=new Shelf();
                sheet.addShelf(shelf);
                shape = shapeFitsOnNewShelfOnCurrentSheet(sheet, shape);
                shelf.place(shape);
            }
            else {

                usedSheets.add(sheet);

                sheet=new Sheet();
                shelf=new Shelf();

                shape = setShapeCoordinatesToZero(shape);
                shelf.place(shape);
                sheet.addShelf(shelf);
            }

        }

        sheet.addShelf(shelf);
        usedSheets.add(sheet);

        return usedSheets;
    }

    private Shape shapeFitsOnShelfAbovePreviousShape(Shelf shelf, Shape shape){
        if(shelf.getShapes().size() > 0) {
            Shape placedShape = shelf.getShapes().get(shelf.getShapes().size() - 1);

            shape = fitsHeight(shelf, shape, placedShape);
            shape = fitsWidth(shelf, shape, placedShape, null);
            shape = lessThanMaxShapesPerShelf(shelf, shape);
            if(shape.isValidHeight() && shape.isValidWidth() && shape.isValidShapeNumber()){
                shape.setValid(true);
            }
        }

        return shape;
    }

    private Shape shapeFitsOnShelfRightOfPreviousShape(Shelf shelf, Shape shape){
        shape = fitsHeight(shelf, shape, null);
        shape = fitsWidth(shelf, shape, null, null);
        shape = lessThanMaxShapesPerShelf(shelf, shape);
        if(shape.isValidHeight() && shape.isValidWidth() && shape.isValidShapeNumber()){
            shape.setValid(true);
        }

        return shape;
    }
}
