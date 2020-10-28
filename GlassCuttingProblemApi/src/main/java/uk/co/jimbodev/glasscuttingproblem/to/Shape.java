package uk.co.jimbodev.glasscuttingproblem.to;

import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Shape implements  Comparable<Shape>{
    @Expose
    private int x1;
    private int x2;
    @Expose
    private int y1;
    private int y2;
    @Expose
    private int width;
    @Expose
    private int height;
    @Expose
    private int insertionNumber;

    public Shape(int width, int height){
        this.width = width;
        this.height = height;
    }

    @Override
    public int compareTo(Shape o) {
        int oArea = o.getHeight() * o.getWidth();
        int thisArea = this.getHeight() * this.getWidth();
        if(thisArea > oArea){
            return 1;
        }
        else if(thisArea < oArea){
            return -1;
        }
        else{
            return 0;
        }
    }
}
