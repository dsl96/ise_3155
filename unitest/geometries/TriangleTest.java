package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

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

    @Test
    void testFindIntersections() {

        Triangle triangle =new Triangle(new Point(0,0,1),new Point(1,0,0), new Point(-1,0,0));
        List<Point> points ;
        // ============ Equivalence Partitions Tests ==============

        //TC01: Inside triangle
        points = triangle.findIntersections((new Ray(new Point(0, 2, 0.5), new Vector(0, -1, 0))));

        assertEquals(List.of(new Point(0,0,0.5)),points,"ERROR: Ray Inside when the triangle");

        //TC02: Outside against edge
        assertNull(triangle.findIntersections((new Ray(new Point(0.5, -2, -1), new Vector(0, 1, 0)))),
                "Ray starts outside against edge");

        //TC03: Outside against vertex
        assertNull(triangle.findIntersections((new Ray(new Point(1.5, -2, -0.2), new Vector(0, 1, 0)))),
                "Ray starts outside against vertex");

        // =============== Boundary Values Tests ==================

        //TC11: the point is on edge
        assertNull( triangle.findIntersections((new Ray(new Point(0.5, -2, 0), new Vector(0, 1, 0)))),
                "Ray's point is on the edge");

        //TC12: the point is in vertex
        assertNull( triangle.findIntersections((new Ray(new Point(1, -1, 0), new Vector(0, 1, 0)))),
                "Ray's point is in vertex");

        //TC13: the point is On edge's continuation
        assertNull(triangle.findIntersections((new Ray(new Point(2, -2, 0), new Vector(0, 1, 0)))),
                "Ray's point is On edge's continuation");
    }
}