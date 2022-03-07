package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test method for  geometries.Triangle
 */
class TriangleTest {


    /**
     * Test method for  getNormal(Point)
     */
    @Test
    public void testGetNormal() {
        Triangle tr = new Triangle(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));

        // ============ Equivalence Partitions Tests ==============

        //check getNormal(Point) result

        //the correct normal
        double sqrt3 = Math.sqrt(1d / 3);
        Vector normal = new Vector(sqrt3, sqrt3, sqrt3);
        //point on the triangle
        Point pointOn = new Point(0, 0, 1);

        // check if the get normal return correct result (there is 2 options)
        boolean bool = tr.getNormal(pointOn).equals(normal) ||
                tr.getNormal(pointOn).equals(normal.scale(-1));

        assertTrue(bool, " ERROR: getNormal() wrong result");
    }

}