package uk.co.jimbodev.glasscuttingproblem.to;

import com.google.gson.annotations.Expose;
import lombok.Data;

import java.util.List;

@Data
public class GlassCuttingResponseBody {
    @Expose
    private String title;
    @Expose
    private int numberOfSheetsUsed;
    @Expose
    private double areaUsageEfficiency;
    @Expose
    private List<Sheet> sheets;
    @Expose
    private List<Shape> shapes;
}
