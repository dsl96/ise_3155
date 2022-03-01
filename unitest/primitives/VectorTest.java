package primitives;

import org.junit.jupiter.api.Test;

import static primitives.Util.*;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

class VectorTest {

    //Vectors for tests
    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);

    Vector vr = v1.crossProduct(v3);

    @Test
    void testCtor() {
        // test zero vector
        assertThrows(IllegalArgumentException.class,
                () -> new Vector(0, 0, 0),
                "ERROR: zero vector does not throw an exception");

        assertThrows(IllegalArgumentException.class,
                () -> new Vector(new Double3(0, 0, 0)),
                "ERROR: zero vector does not throw an exception");
    }


    @Test
    void scale() {
        //Test for scale()
        assertEquals(v2, v1.scale(-2), "ERROR: scale() wrong result vector");

    }

    @Test
    void add() {
        //Test for add()
        assertEquals(new Vector(1, 5, 1), v1.add(v3), "ERROR: add() wrong result vector");
    }

    @Test
    void crossProduct() {

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

    @Test
    void testDotProduct() {

        // test zero for orthogonal vector
        assertTrue(isZero(v1.dotProduct(v3)), "ERROR: dotProduct() for orthogonal vectors is not zero");


        //
        assertTrue(isZero(v1.dotProduct(v2) + 28), "ERROR: dotProduct() wrong value");


    }

    @Test
    void normalize() {
        Vector u = v1.normalize();

        //test for length of normal
        assertTrue(isZero(u.length() - 1), "ERROR: the normalized vector is not a unit vector");


        // test that the vectors are co-lined
        assertThrows(IllegalArgumentException.class,
                () -> v1.crossProduct(u),
                "ERROR: the normalized vector is not parallel to the original one");

        // test that the vectors are co direction
        assertTrue(v1.dotProduct(u) < 0,
                "ERROR: the normalized vector is opposite to the original one");

    }

    @Test
    void lengthSquared() {
        // test lengthSquared
        assertTrue(isZero(v1.lengthSquared() - 14),
                "ERROR: lengthSquared() wrong value");
    }

    @Test
    void length() {

        // test length
        assertTrue(isZero(new Vector(0, 3, 4).length() - 5),
                "ERROR: length() wrong value");
    }
}