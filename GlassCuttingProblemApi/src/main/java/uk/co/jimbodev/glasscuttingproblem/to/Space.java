package uk.co.jimbodev.glasscuttingproblem.to;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Space {
    private int x1;
    private int x2;
    private int y1;
    private int y2;
    private int width;
    private int height;

    public Space(int x1, int x2, int y1, int y2){
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.width = x2 - x1;
        this.height = y2 - y1;
    }
}
