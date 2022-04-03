package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest {

    /**
     * test Find Intersections(ray)
     */
    @Test
    void testFindIntersections() {
        Sphere s = new Sphere(new Point(0, 2, 2), 1);
        Triangle t = new Triangle(new Point(3, 0, 0), new Point(-3, 0, 0), new Point(0, 0, 3));
        Plane p = new Plane(new Point(2, 4, 0), new Point(-2, 4, 0), new Point(0, 4, 7));
        Geometries geometries = new Geometries(s, t, p);

        // ============ Equivalence Partitions Tests ==============

        //cross part of the geometries (2 from 3)
        Ray ray = new Ray(new Point(0, -2, 4.1), new Vector(0, 4, -1.8));
        assertEquals(3,geometries.findIntersections(ray).size(),"ERROR when ray cross part of the  geometries" );

        // =============== Boundary Values Tests ==================

        //cross all geometries
        ray = new Ray(new Point(0, -2, 0.9), new Vector(0, 4, 1.4));
        assertEquals(4,geometries.findIntersections(ray).size(),"ERROR when ray cross all geometries" );

        //cross only 1
        ray = new Ray(new Point(0, 2.3, 4.1), new Vector(0, -0.3, -1.8));
        assertEquals(2,geometries.findIntersections(ray).size(),"ERROR when ray cross 1 geometry" );

        //cross nothing
        ray = new Ray(new Point(-2, -2.3, 4.1), new Vector(0, -0.2, -0.7));
        assertNull( geometries.findIntersections(ray),"ERROR when ray dont cross" );

        //empty geometry
        assertNull(new Geometries().findIntersections(ray),"ERROR when empty geometry ");
    }
}