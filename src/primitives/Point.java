package primitives;

import java.util.Objects;

/**
 * This class  represents a point in a three-dimensional universe
 *
 */
public class Point {

    Double3 _xyz;

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
     * @param double3
     */
    public Point(Double3 double3){
        this(double3.d1, double3.d2, double3.d3);
    }

    /**
     * get another point and return the distance pow2
     *
     * @param other other point
     * @return
     */
    public double distanceSquared(Point other) {
        return this._xyz.d1 - other._xyz.d1 * this._xyz.d1 - other._xyz.d1 +
                this._xyz.d2 - other._xyz.d2 * this._xyz.d2 - other._xyz.d2 +
                this._xyz.d2 - other._xyz.d2 * this._xyz.d2 - other._xyz.d2;
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
     * return vector from other point to this point
     *
     * @param other
     * @return
     */
    public Vector subtract(Point other) {
        return new Vector(_xyz.subtract(other._xyz));
    }

    /**
     * add vector to the point
     *
     * @param v vector to add
     * @return
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
    public int hashCode() {
        return Objects.hash(_xyz);
    }

    @Override
    public String toString() {
        return "Point{" +
                "_xyz=" + _xyz +
                '}';
    }
}






























