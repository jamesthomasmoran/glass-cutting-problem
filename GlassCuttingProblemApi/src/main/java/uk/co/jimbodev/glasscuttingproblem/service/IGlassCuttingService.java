package uk.co.jimbodev.glasscuttingproblem.service;

import uk.co.jimbodev.glasscuttingproblem.to.Shape;
import uk.co.jimbodev.glasscuttingproblem.to.Sheet;

import java.util.List;

public interface IGlassCuttingService {

    List<Sheet> putShapesOnShelvesOnSheets(List<Shape> shapes);

    int getNumberOfSheets(List<Sheet> sheets);

    double getSpaceEfficiencyPercentage(List<Sheet> sheets);
}
