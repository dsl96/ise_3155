package primitives;

/**
 * Vector class represents 3D vector
 */
public class Vector extends Point {

    /******************** constructor *********************/

    /**
     * Constructor to initialize point 3D
     * get three double coordinates
     *
     * @param x first number value
     * @param y second number value
     * @param z third number value
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (_xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("vector cant be zero vector");
        }
    }

    /**
     * Constructor to initialize point 3D
     *
     * @param double3
     */
    Vector(Double3 double3) {
        super(double3);
        if (_xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("vector cant be zero vector");
        }
    }

    // ***************** Operations ******************** //

    /**
     * scale the vector by scalar
     *
     * @param rhs scalar
     * @return
     */
    public Vector scale(double rhs) {
        return new Vector(_xyz.scale(rhs));
    }

    /**
     * @return this vector plus the given one
     */
    public Vector add(Vector other) {
        return new Vector(_xyz.add(other._xyz));
    }

    /**
     * calculate cross product with other vector
     *
     * @param other other vector
     * @return cross product
     */
    public Vector crossProduct(Vector other) {
        return (new Vector(_xyz.d2 * other._xyz.d3 - _xyz.d3 * other._xyz.d2,
                _xyz.d3 * other._xyz.d1 - _xyz.d1 * other._xyz.d3,
                _xyz.d1 * other._xyz.d2 - _xyz.d2 * other._xyz.d1));
    }

    /**
     * calculate dot product with other vector
     *
     * @param other other vector
     * @return dot product
     */
    public double dotProduct(Vector other) {
        return (_xyz.d1 * other._xyz.d1) + (_xyz.d2 * other._xyz.d2) + (_xyz.d3 * other._xyz.d3);
    }

    /**
     * return this vector normalize
     *
     * @return
     */
    public Vector normalize() {
        return new Vector(_xyz.reduce(length()));
    }

    /**
     * calculate the length Squared of the vector
     *
     * @return length Squared of the vector
     */
    public double lengthSquared() {
        return (_xyz.d1 * _xyz.d1) + (_xyz.d2 * _xyz.d2) + (_xyz.d3 * _xyz.d3);
    }

    /**
     * calculate the length of the vector
     *
     * @return length of the vector
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Vector)) return false;
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "head= " + super.toString();
    }
}
