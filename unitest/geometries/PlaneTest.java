package geometries;

import org.junit.jupiter.api.Test;
import org.junit.platform.engine.support.discovery.SelectorResolver;
import primitives.Point;
import primitives.Vector;

import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.*;

import java.util.regex.Matcher;


class PlaneTest {

    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Plane p = new Plane(new Point(1, 1, 0), new Point(2, 1, 0), new Point(1, 2, 0));
        boolean bool = new Vector(0, 0, 1).equals(p.getNormal()) || new Vector(0, 0, -1).equals(p.getNormal());

        assertEquals(bool, true, "The normal is correct");

        assertNotEquals(new Vector(1,1,2), p.getNormal(), "The normal isn't correct");
    }
}