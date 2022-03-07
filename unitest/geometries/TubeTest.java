package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {

    @Test
    void getNormal() {
        Tube tube = new Tube(  new Ray(new Point(0, 0, 1), new Vector(0, 1, 0)),1.0);

        assertEquals( new Vector(0, 0, 1), tube.getNormal(new Point(0, 0.5, 2)),"ERROR: Bad normal to tube");

        // =============== Boundary Values Tests ==================
        assertEquals(tube.getNormal(new Point(0,0,2)),new Vector(0,0,1),
                "ERROR: wrong result getNormal()" +
                " When connecting the point to the head of the horn of the cylinder axis" +
                " produces a right angle with the axis");
    }
}