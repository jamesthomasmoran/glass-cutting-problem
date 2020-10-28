package uk.co.jimbodev.glasscuttingproblem.to;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpaceTest {

    @Test
    public void testWidthCalculatedFromX1andX2(){
        Space space = new Space(5, 10, 0, 0);

        assertEquals(5, space.getWidth());
    }

    @Test
    public void testHeightCalculatedFromY1andY2(){
        Space space = new Space(5, 10, 10, 25);

        assertEquals(15, space.getHeight());
    }
}
