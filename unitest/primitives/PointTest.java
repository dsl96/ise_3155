package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static primitives.Util.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for primitives.Point class
 */
class PointTest {

    //points for tests
    Point p1 = new Point(1, 2, 3);
    Point p2 = new Point(3, 2, 1);


    /**
     * test distance Squared
     */
    void distanceSquared() {
        // ============ Equivalence Partitions Tests ==============

        //test distanceSquared() result
        assertTrue(isZero(p1.distanceSquared(p2) - 8 ),"ERROR: distanceSquared() wrong result");
    }

    /**
     * test distance
     */
    @Test
    void distance() {
        // ============ Equivalence Partitions Tests ==============

        //test distance() result
        assertTrue(isZero(p1.distance(p2) - Math.sqrt(8) ),"ERROR: distance() wrong result");
    }

    /**
     * test substract method
     */
    @Test
    void subtract() {
        // ============ Equivalence Partitions Tests ==============

        //test subtract() result
        assertEquals(new Vector(-2,0,2), p1.subtract(p2), "Point - Point does not work correctly");
    }

    /**
     * test add method
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============

        //test add() result
        assertEquals(p1.add(new Vector(3,2,1)), new Point(4,4,4),"ERROR: add() wrong result");
    }
}