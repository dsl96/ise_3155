package primitives;

public class Vector extends Point {

    /**
     * Constructor to initialize point 3D
     *  get three double coordinates
     * @param x first number value
     * @param y second number value
     * @param z third number value
     */
     public Vector (double x,double y, double z) {
         super(x, y, z);
         if (_xyz.equals(Double3.ZERO)) {
             throw new IllegalArgumentException("vector cant be zero vector");
         }
     }

    /**
     * scale the vector by scalar
     * @param rhs scalar
     * @return
     */
    Vector scale(double rhs) {
        return new Vector(_xyz.scale(rhs));
    }

    /**
     * @return this vector plus the given one
     */
    public Vector add(Vector other) { return new Vector(_xyz.add(other._xyz)) ;}

    public Vector crossProduct(Vector other) {
        return (new Vector(_xyz.d2 * other._xyz.d3 -_xyz.d3*other._xyz.d2,_xyz.d3*other._xyz.d1-_xyz.d1*other._xyz.d3,_xyz.d1*other._xyz.d2-_xyz.d2*other._xyz.d1));
    }

    /**
     * dot product
     * @param other other vector
     * @return
     */
    public  Vector dotProduct(Vector other) {
        return new Vector(_xyz.product(other._xyz));
    }

    public Vector normalize(){
        return new Vector(_xyz.reduce(Math.sqrt()llllllllllllllllllllllllllllllllllllll
    }
    /**
     * Constructor to initialize point 3D
     * @param double3
     */
     Vector(Double3 double3){
       this(double3.d1, double3.d2, double3.d3);
     }


}
