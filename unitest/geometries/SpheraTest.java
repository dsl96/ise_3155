package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class SpheraTest {
    @Test
    void testGetNormal() {
        Sphera s = new Sphera(new Point(0,0 ,0), 3.5 );

        // ============ Equivalence Partitions Tests ==============

        // check correct normal
        assertEquals( new Vector(1, 0 , 0), s.getNormal(new Point(3.5, 0, 0))
                ,"correct normal");
    }
}