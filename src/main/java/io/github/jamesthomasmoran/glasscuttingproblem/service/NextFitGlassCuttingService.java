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

        int currentColumn = 0;
        for(int i = 0; i < shapes.size(); i++) {
            Shape shape = shapes.get(i);
            shape.setInsertionNumber(i + 1);
            System.out.println( i + " - Shelves: " + sheet.getShelves().size());
            if(shelf.getColumns().get(0).getShapes().size() == 0 ){
                shelf.place(shape, currentColumn);
            }
            else if(shapeFitsOnShelfAbovePreviousShape(shelf, shape).isValid()) {
                shape = shapeFitsOnShelfAbovePreviousShape(shelf, shape);
                shelf.place(shape, currentColumn);
                System.out.println("Point 1: " + sheet.getShelves().size());
            }
            else if(shapeFitsOnShelfRightOfPreviousShape(shelf, shape).isValid()) {

                shelf.addColumn();
                currentColumn++;
                shape = shapeFitsOnShelfRightOfPreviousShape(shelf, shape);
                shelf.place(shape, currentColumn);

                System.out.println(sheet.getShelves().size());
            }
            else if(shapeFitsOnNewShelfOnCurrentSheet(sheet, shape).isValid()) {

                shelf=new Shelf();

                sheet.addShelf(shelf);
                shape = shapeFitsOnNewShelfOnCurrentSheet(sheet, shape);
                currentColumn = 0;
                shelf.place(shape, currentColumn);

                System.out.println("Point 3: " + sheet.getShelves().size());
            }
            else {

                usedSheets.add(sheet);

                sheet=new Sheet();
                shelf=new Shelf();

                shape = setShapeCoordinatesToZero(shape);
                currentColumn = 0;
                shelf.place(shape, currentColumn);
                sheet.addShelf(shelf);

                System.out.println("Point 4: " + usedSheets.get(0).getShelves().size());
            }

        }
        
        System.out.println("Point 5: " + sheet.getShelves().size());
        usedSheets.add(sheet);

        return usedSheets;
    }

    private Shape shapeFitsOnShelfAbovePreviousShape(Shelf shelf, Shape shape){
        if(shelf.getColumns().get(0).getShapes().size() > 0) {
            Shape placedShape = shelf.getColumns().get(shelf.getColumns().size() - 1).getShapes().get(shelf.getColumns().get(shelf.getColumns().size() - 1).getShapes().size() - 1);
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
        System.out.println("Height: " + shape.isValidHeight() + " Width: " + shape.isValidWidth() + " Shapes:" + shape.isValidShapeNumber());
        if(shape.isValidHeight() && shape.isValidWidth() && shape.isValidShapeNumber()){
            shape.setValid(true);
        }

        return shape;
    }
}
