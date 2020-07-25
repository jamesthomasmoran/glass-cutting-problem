package io.github.jamesthomasmoran.glasscuttingproblem.service;

import io.github.jamesthomasmoran.glasscuttingproblem.model.Column;
import io.github.jamesthomasmoran.glasscuttingproblem.model.Shape;
import io.github.jamesthomasmoran.glasscuttingproblem.model.Sheet;
import io.github.jamesthomasmoran.glasscuttingproblem.model.Shelf;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;

@Service
public class FirstFitGlassCuttingService extends GlassCuttingService {

    private static final int START_INDEX = 0;

    private int columnIndex = 0;

    @Override
    public List<Sheet> putShapesOnShelvesOnSheets(List<Shape> shapes) {
        List<Sheet> sheets = new ArrayList<Sheet>();
        Sheet currentSheet = new Sheet();
        currentSheet.addShelf(new Shelf());


        sheets.add(currentSheet);


        for(int i = 0; i < shapes.size(); i++) {
            boolean placed = false;
            Shape shape = shapes.get(i);
            shape.setInsertionNumber(i + 1);

            for(Sheet sheet : sheets){
                if(placed){
                    break;
                }
                if (sheet.getShelves().get(0).getColumns().get(0).getShapes().size() < 1) {
                    currentSheet.getShelves().get(0).place(shape, START_INDEX);
                    placed = true;
                    break;
                }
                else{
                    for(Shelf shelf : sheet.getShelves()){

                        if(shapeFitsOnShelfAboveShape(shelf, shape).isValid()){
                            System.out.println(i+1 + " shape ShapeFitsAbove at column " + columnIndex );
                            columnIndex = 0;
                            shape = shapeFitsOnShelfAboveShape(shelf, shape);
                            shelf.place(shape, columnIndex);
                            placed = true;

                            columnIndex = 0;
                            break;

                        }

                        else if(shapeFitsOnShelfRightOfPreviousShape(shelf, shape).isValid()){

                            if(shelf.getColumns().get(0).getShapes().size() > 0) {
                                shelf.addColumn();
                            }
                            shape = shapeFitsOnShelfRightOfPreviousShape(shelf, shape);
                            shelf.place(shape, shelf.getColumns().size() -1);
                            placed = true;
                            columnIndex = 0;
                            break;
                        }
                    }
                    if(!placed) {

                        if (shapeFitsOnNewShelfOnCurrentSheet(sheet, shape).isValid()) {
                            Shelf newShelf = new Shelf();
                            sheet.addShelf(newShelf);
                            shape = shapeFitsOnNewShelfOnCurrentSheet(sheet, shape);
                            newShelf.place(shape, START_INDEX);
                            placed = true;
                        }
                    }
                }
            }
            if(!placed) {

               Sheet sheet = new Sheet();
                Shelf newShelf=new Shelf();

                shape = setShapeCoordinatesToZero(shape);

                newShelf.place(shape, START_INDEX);
                sheet.addShelf(newShelf);
                sheets.add(sheet);
            }
        }
        return sheets;
    }

    private Shape shapeFitsOnShelfAboveShape(Shelf shelf, Shape shape){


            for (int i = 0; i < shelf.getColumns().size(); i++) {
                Column column = shelf.getColumns().get(i);
                Shape placedShape = column.getShapes().get(column.getShapes().size() - 1);
                Shape nextPlacedShape = null;
                if(i < shelf.getColumns().size()-1){
                    nextPlacedShape = shelf.getColumns().get(i + 1).getShapes().get(0);
                }

                shape = fitsHeight(shelf, shape, placedShape);
                shape = fitsWidth(shelf, shape, placedShape, nextPlacedShape);
                shape = lessThanMaxShapesPerShelf(shelf, shape);
                if (shape.isValidHeight() && shape.isValidWidth() && shape.isValidShapeNumber()) {
                    shape.setValid(true);
                    break;
                }
                columnIndex++;
            }


        return shape;
    }

    private Shape shapeFitsOnShelfRightOfPreviousShape(Shelf shelf, Shape shape){


            shape = fitsHeight(shelf, shape, null);
            shape = fitsWidth(shelf, shape, null, null);
            shape = lessThanMaxShapesPerShelf(shelf, shape);
            if (shape.isValidHeight() && shape.isValidWidth() && shape.isValidShapeNumber()) {
                shape.setValid(true);
            }

        return shape;
    }

    }
