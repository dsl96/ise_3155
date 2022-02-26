package primitives;
import Math;

/**
 * This class  represents a point in a three-dimensional universe
 *
 */
public class Point {

    Double3 _xyz;

    /**
     *  Constructor to initialize point 3D
     *  get three double coordinates
     * @param x first number value
     * @param y second number value
     * @param z third number value
     */
   public Point(double x, double y, double z)
   {
        _xyz = new Double3(x,y,z);
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
     * @param other other point
     * @return
     */
   public double distanceSquared(Point other){
       return this._xyz.d1-other._xyz.d1*this._xyz.d1-other._xyz.d1+
               this._xyz.d2-other._xyz.d2*this._xyz.d2-other._xyz.d2+
               this._xyz.d2-other._xyz.d2*this._xyz.d2-other._xyz.d2;

   }

    /**
     * get another point and return the distance
     * @param other other point
     * @return
     */
   public double distance( Point other){
       return Math.sqrt(distanceSquared(other));
   }

   public vector subtract(Point other){///////////////////////////////////////////////////////////////////////////////////////

   }
}
