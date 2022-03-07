package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
/**
 * tests geometries.Sphera class
 */
class SpheraTest {

    /**
     * test for ctor
     */
    @Test
    void testCtor() {

        // =============== Boundary Values Tests ==================
        assertThrows(IllegalArgumentException.class, ()->new Sphera(new Point(0,0 ,0), -3.5),
                "ERROR: ctor get negative or zero radius ");
    }

    /**
     * tests for getNormal(Point)
     */
    @Test
    void testGetNormal() {
        Sphera s = new Sphera(new Point(0,0 ,0), 3.5 );

        // ============ Equivalence Partitions Tests ==============

        // check correct normal
        assertEquals( new Vector(1, 0 , 0), s.getNormal(new Point(3.5, 0, 0))
                ,"ERROR: getNormal() worng result");
    }
}