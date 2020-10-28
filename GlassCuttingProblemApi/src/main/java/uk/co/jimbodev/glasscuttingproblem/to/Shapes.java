package uk.co.jimbodev.glasscuttingproblem.to;

import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class Shapes {
    @Expose
    private List<Shape> shapes;
    public Shapes(List<Shape> shapes){
        this.shapes = shapes;
    }
}
