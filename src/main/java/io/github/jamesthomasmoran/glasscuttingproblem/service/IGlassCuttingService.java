package io.github.jamesthomasmoran.glasscuttingproblem.service;

import io.github.jamesthomasmoran.glasscuttingproblem.model.Shape;
import io.github.jamesthomasmoran.glasscuttingproblem.model.Sheet;

import java.util.List;

public interface IGlassCuttingService {

    List<Sheet> putShapesOnShelvesOnSheets(List<Shape> shapes);

    int getNumberOfSheets(List<Sheet> sheets);

    double getSpaceEfficiencyPercentage(List<Sheet> sheets);
}
