package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for primitives.Point class
 * @author
 */
class PointTest {

    //Point p1 = new Point(1, 2, 3);






		//if (!(p1.add(new Vector(-1, -2, -3)).equals(new Point(0, 0, 0))))
        //    out.println("ERROR: Point + Vector does not work correctly");



    @Test
    // TC01: Test that distance squared is proper (orthogonal vectors taken
    void distanceSquared() {
    }

    @Test
    void distance() {
    }

    @Test
    void subtract() {
        assertEquals(new Vector (1,1,1) ,new Point(2, 3, 4).subtract(new Point(1, 2, 3)),"Point - Point does not work correctly");
    }

    /**
     * Test method for {@link primitives.Point(primitives.Point)}.
     */
    @Test
    void testAdd() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void testHashCode() {
    }

    @Test
    void testToString() {
    }
}