package primitives;

import java.util.Objects;

/**
 * This class  represents a point in a three-dimensional universe
 */
public class Point {

    /**
     * Zero Point (0,0,0)
     */
    public static final Point ZERO = new Point(Double3.ZERO);

    Double3 _xyz;

    /******************** constructor *********************/

    /**
     * Constructor to initialize point 3D
     * get three double coordinates
     *
     * @param x first number value
     * @param y second number value
     * @param z third number value
     */
    public Point(double x, double y, double z) {
        _xyz = new Double3(x, y, z);
    }

    /**
     * Constructor to initialize point 3D
     * get "Double3"
     *
     * @param double3
     */
    public Point(Double3 double3) {
        this(double3.d1, double3.d2, double3.d3);
    }

    // ***************** Getters ********************** //


    public double getX() {
        return _xyz.d1;
    }

    public double getY() {
        return _xyz.d2;
    }

    public double getZ() {
        return _xyz.d3;
    }

    // ***************** Operations ******************** //

    /**
     * get another point and calculate the distance pow2
     *
     * @param other other point
     * @return distance pow2
     */
    public double distanceSquared(Point other) {
        return (this._xyz.d1 - other._xyz.d1) * (this._xyz.d1 - other._xyz.d1) +
                (this._xyz.d2 - other._xyz.d2) * (this._xyz.d2 - other._xyz.d2) +
                (this._xyz.d3 - other._xyz.d3) * (this._xyz.d3 - other._xyz.d3);
    }

    /**
     * get another point and return the distance
     *
     * @param other other point
     * @return
     */
    public double distance(Point other) {
        return Math.sqrt(distanceSquared(other));
    }

    /**
     * calculate vector from other point to this point
     *
     * @param other
     * @return new vector
     */
    public Vector subtract(Point other) {
        return new Vector(_xyz.subtract(other._xyz));
    }

    /**
     * add vector to the point
     *
     * @param v vector to add
     * @return new Point
     */
    public Point add(Vector v) {
        return new Point(_xyz.add(v._xyz));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Point)) return false;
        Point other = (Point) obj;
        return _xyz.equals(other._xyz);
    }

    @Override
    public String toString() {
        return "Point{" +
                "_xyz=" + _xyz +
                '}';
    }
}






























