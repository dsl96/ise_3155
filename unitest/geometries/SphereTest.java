package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * tests geometries.Sphera class
 */
class SphereTest {

    /**
     * test for ctor
     */
    @Test
    void testCtor() {

        // =============== Boundary Values Tests ==================
        assertThrows(IllegalArgumentException.class, () -> new Sphere(new Point(0, 0, 0), -3.5),
                "ERROR: ctor get negative or zero radius ");
    }

    /**
     * tests for getNormal(Point)
     */
    @Test
    void testGetNormal() {
        Sphere s = new Sphere(new Point(0, 0, 0), 3.5);

        // ============ Equivalence Partitions Tests ==============

        // check correct normal
        assertEquals(new Vector(1, 0, 0), s.getNormal(new Point(3.5, 0, 0))
                , "ERROR: getNormal() worng result");
    }

    @Test
    void testFindIntersections() {
        Sphere s = new Sphere(new Point(1, 0, 0), 1);
        List<Point> points;
        Point p1;
        Point p2;

        // ============ Equivalence Partitions Tests ==============

        //ray line out of the sphere
        assertTrue(s.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(1, 1, 0))).isEmpty(),
                "Error find incorrect intersection");

        //ray cross the sphere
        p1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        p2 = new Point(1.53484692283495, 0.844948974278318, 0);
        points = s.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(3, 1, 0)));

        //check get two points
        assertEquals(2, points.size(), "Error findIntersections ned to get two points");
        //check correct points
        assertTrue(points.contains(p1) && points.contains(p2),
                "Error find incorrect intersection");

        //ray stars inside the sphere
        p1 = new Point(1, 1, 0);
        points = s.findIntersections(new Ray(new Point(1, 0, 0), new Vector(0, 1, 0)));

        //check get one point
        assertEquals(1, points.size(), "Error findIntersections ned to get one point");
        //check correct points
        assertTrue(points.contains(p1),
                "Error find incorrect intersection");

    }
}