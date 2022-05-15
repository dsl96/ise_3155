package primitives;

import geometries.Plane;
import org.junit.jupiter.api.Test;

import java.util.List;

import static primitives.Util.*;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

/**
 * tests for primitives.Vector class
 */
class VectorTest {

    //Vectors for tests
    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);

    Vector vr = v1.crossProduct(v3);

    /**
     * test ctors
     */
    @Test
    void testCtor() {
        // =============== Boundary Values Test ==================

        // test zero vector for ctor get 3 doubles
        assertThrows(IllegalArgumentException.class,
                () -> new Vector(0, 0, 0),
                "ERROR: zero vector does not throw an exception");

        // test zero vector for ctor get Double3
        assertThrows(IllegalArgumentException.class,
                () -> new Vector(new Double3(0, 0, 0)),
                "ERROR: zero vector does not throw an exception");
    }

    /**
     * Test to mult scale with vector
     */
    @Test
    void testScale() {
        // ============ Equivalence Partitions Tests ==============

        //Test for scale()
        assertEquals(v2, v1.scale(-2), "ERROR: scale() wrong result vector");
    }

    /**
     * Test to add vector to vector
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============

        //Test for add()
        assertEquals(new Vector(1, 5, 1), v1.add(v3), "ERROR: add() wrong result vector");
    }

    /**
     * Test for cross product
     */
    @Test
    void testCrossProduct() {
        // ============ Equivalence Partitions Tests ==============

        // test zero vector same direction vector
        assertThrows(IllegalArgumentException.class,
                () -> v1.crossProduct(v2),
                "ERROR: crossProduct() for parallel vectors does not throw an exception");

        //test length of crossProduct()
        assertTrue(isZero(vr.length() - v1.length() * v3.length()),
                "ERROR: crossProduct() wrong result length");

        //test if the vector is orthogonal
        assertTrue(isZero(vr.dotProduct(v1)) && isZero(vr.dotProduct(v3)),
                "ERROR: crossProduct() result is not orthogonal to its operands");
    }

    /**
     * Test for dot product
     */
    @Test
    void testDotProduct() {
        // ============ Equivalence Partitions Tests ==============

        // test value of dotProduct()
        assertTrue(isZero(v1.dotProduct(v2) + 28), "ERROR: dotProduct() wrong value");

        // =============== Boundary Values Test ==================

        // test zero for orthogonal vector
        assertTrue(isZero(v1.dotProduct(v3)), "ERROR: dotProduct() for orthogonal vectors is not zero");
    }

    /**
     * Test to normalize vector
     */
    @Test
    void testNormalize() {
        Vector u = v1.normalize();
        // ============ Equivalence Partitions Tests ==============

        //test for length of normal
        assertTrue(isZero(u.length() - 1), "ERROR: the normalized vector is not a unit vector");

        // test that the vectors are co direction
        assertTrue(v1.dotProduct(u) > 0,
                "ERROR: the normalized vector is opposite to the original one");

        // test that the vectors are co-lined
        assertThrows(IllegalArgumentException.class,
                () -> v1.crossProduct(u),
                "ERROR: the normalized vector is not parallel to the original one");
    }

    /**
     * Test to get the length square of vector
     */
    @Test
    void testLengthSquared() {
        // ============ Equivalence Partitions Tests ==============

        // test lengthSquared()
        assertTrue(isZero(v1.lengthSquared() - 14),
                "ERROR: lengthSquared() wrong value");
    }

    /**
     * Test to get the length of vector
     */
    @Test
    void testLength() {
        // ============ Equivalence Partitions Tests ==============

        // test length()
        assertTrue(isZero(new Vector(0, 3, 4).length() - 5),
                "ERROR: length() wrong value");
    }


    /**
     * test create normal
     */
    @Test
    void testCreateNormal() {

        // ============ Equivalence Partitions Tests ==============
        v1 = new Vector(1, 85.22, 12.36);
        assertTrue(isZero(v1.dotProduct(v1.createNormal())), "ERROR:create normal");

        // =============== Boundary Values Test ==================
        v1 = new Vector(2025, 0, 0);
        assertTrue(isZero(v1.dotProduct(v1.createNormal())), "ERROR:create normal 1,0,0");

        v1 = new Vector(0, 1, 0);
        assertTrue(isZero(v1.dotProduct(v1.createNormal())), "ERROR:create normal 0,1,0");

        v1 = new Vector(0, 0, 1);
        assertTrue(isZero(v1.dotProduct(v1.createNormal())), "ERROR:create normal 0,0,1");

    }

    @Test
    void testGenerateBeam() {

        Plane p =new Plane(new Point(0,1,0),new Point(0,0,1),new Point(0,90,7));
        Ray ray=new Ray(new Point(-50,0,0),new Vector(1,0,0));

        for (Ray r:ray.generateBeam(p.getNormal(),1,50,900)){
            List<Point> intersection=p.findIntersections(r);
           // System.out.println(intersection.get(0).toString()+ intersection.get(0).distance(Point.ZERO));
            assertTrue(intersection.get(0).distance(Point.ZERO)<=1,"Eror");
        }

    }
}