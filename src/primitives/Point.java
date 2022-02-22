package primitives;
import Math;

/**
 * This class  represents a point in a three-dimensional universe
 *
 */
public class Point {

   final Double3 xyz;

    /**
     *  Constructor to initialize point 3D
     *  get three double coordinates
     * @param x first number value
     * @param y second number value
     * @param z third number value
     */
   public Point(double x, double y, double z)
   {
        xyz= new Double3(x,y,z);
   }

    /**
     * get another point and return the distance pow2
     * @param other other point
     * @return
     */
   public  double distanceSquared(Point other){
       return this.xyz.d1-other.xyz.d1*this.xyz.d1-other.xyz.d1+
               this.xyz.d2-other.xyz.d2*this.xyz.d2-other.xyz.d2+
               this.xyz.d2-other.xyz.d2*this.xyz.d2-other.xyz.d2;

   }

    /**
     * get another point and return the distance
     * @param other other point
     * @return
     */
   public double distance( Point other){
       return Math.sqrt(distanceSquared(other));
   }

}
