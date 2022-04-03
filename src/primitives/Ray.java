package primitives;

import java.util.List;
import static primitives.Util.*;

/**
 * Ray class represents 3D ray
 */
public class Ray {
    private Point p0;
    private Vector dir;

    /******************** constructor *********************/

    /**
     *  constructor
     *
     * @param p0  start point
     * @param dir direction
     */
    public Ray(Point p0, Vector dir) {
        this.dir = dir.normalize();
        this.p0 = new Point(p0._xyz);
    }
    // ***************** Getters ********************** //

    public Point getP0() {
        return p0;
    }

    public Vector getDir() {
        return dir;
    }

    // ***************** Operations ******************** //

    /**
     * get point on the ray
     *
     * @param length distance from the start of the ray
     * @return new Point3D
     */
    public Point getTargetPoint(double length) {
        return isZero(length) ? p0 : p0.add(dir.scale(length));
    }

    /**
     * this function get list of points and return the closest point
     * to po of the ray
     * @param points
     * @return closest point to p0
     */
    public Point findClosestPoint(List<Point> points) {
        if (points == null || points.isEmpty())
            return null;

         return points.stream().
                 min((p1,p2)-> Double.compare(p0.distanceSquared(p1),p0.distanceSquared(p2))).get();

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Ray)) return false;
        Ray other = (Ray) obj;
        return this.dir.equals(other.dir) && this.p0.equals(other.p0);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }
}
