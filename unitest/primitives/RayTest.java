package primitives;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    @Test
    void TestFindClosestPoint() {

        Ray ray = new Ray(new Point(1, 1, 1), new Vector(1, 1, 1));
        Point p1 = new Point(0.5, 0.5, 0.5);
        Point p2 = new Point(0, 0.5, 0.5);
        Point p3 = new Point(-1, 0.5, 0.5);

        // ============ Equivalence Partitions Tests ==============

        // TC01: closest point in the list
        assertEquals(p1, ray.findClosestPoint(List.of(p2, p1, p3)), "closest point in the list");

        // =============== Boundary Values Tests ==================

        // TC02: closest point in the top of the list
        assertEquals(p1, ray.findClosestPoint(List.of(p1, p2, p3)), "closest point in the top of the list");

        // TC03: closest point in the end of the list
        assertEquals(p1, ray.findClosestPoint(List.of(p2, p3, p1)), "closest point in the end of the list");

        // TC04: no point in the list
        assertNull(ray.findClosestPoint(List.of()), "no point in the list");
    }
}