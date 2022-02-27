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
     * Constructor to initialize point 3D
     * @param double3
     */
    Vector(Double3 double3){
        this(double3.d1, double3.d2, double3.d3);
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

    /**
     * cross product
     * @param other other vector
     * @return
     */
    public Vector crossProduct(Vector other) {
        return (new Vector(_xyz.d2 * other._xyz.d3 -_xyz.d3*other._xyz.d2,_xyz.d3*other._xyz.d1-_xyz.d1*other._xyz.d3,_xyz.d1*other._xyz.d2-_xyz.d2*other._xyz.d1));
    }

    /**
     * dot product
     * @param other other vector
     * @return
     */
    public  double dotProduct(Vector other) {
        return  (_xyz.d1 * _xyz.d1) + (_xyz.d2 * _xyz.d2) + (_xyz.d3 * _xyz.d3);
    }

    /**
     * return this vector normalize
     * @return
     */
    public Vector normalize(){
        return new Vector(_xyz.reduce( length() ) );
    }

    /**
     * return the length Squared of the vector
     * @return
     */
    public double lengthSquared(){
        return  (_xyz.d1 * _xyz.d1) + (_xyz.d2 * _xyz.d2) + (_xyz.d3 * _xyz.d3) ;
    }

    /**
     * return thr length of the vector
     * @return
     */
    public double length(){
        return  Math.sqrt(lengthSquared());
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
        return  "head= "+super.toString();
    }
}
