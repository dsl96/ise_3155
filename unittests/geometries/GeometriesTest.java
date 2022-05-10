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
    /**
     * test Find Intersections(ray) and nax distance
     */
    @Test
    void testFindIntersectionsMax() {
        Sphere s = new Sphere(new Point(0, 0, 0), 1);
        Triangle t1 = new Triangle(new Point(1, 1, -1), new Point(1, -1, -1), new Point(1, 0, 3));
        Triangle t2 = new Triangle(new Point(-1, 1, -1), new Point(-1, -1, -1), new Point(-1, 0, 3));
        Plane p1 = new Plane(new Point(1, 1, -1), new Point(1, -1, -1), new Point(1, 0, 3));
        Plane p2 = new Plane(new Point(-1, 1, -1), new Point(-1, -1, -1), new Point(-1, 0, 3));
        Geometries geometries = new Geometries(s,t1,t2,p1,p2);
        Ray ray = new Ray(new Point(-50, 0, 0), new Vector(1, 0, 0));

        // ============ Equivalence Partitions Tests ==============

        //when max is too small
        assertNull(geometries.findGeoIntersections(ray,40),"ERROR when max is too small");

        //cross part of the geometries (3 from 6)
        assertEquals(3,geometries.findGeoIntersections(ray,50).size(),
                "ERROR when ray cross part of the geometries (3 from 6)");

        //cross all geometries
        assertEquals(6,geometries.findGeoIntersections(ray,60).size(),
                "ERROR when ray cross all of the geometries (6 from 6)");

        // =============== Boundary Values Tests ==================

        //when max is equals to distance
        assertNull(geometries.findGeoIntersections(ray,49),"ERROR when max is equals to distance");

    }
}