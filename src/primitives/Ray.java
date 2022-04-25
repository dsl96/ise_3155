package primitives;

import geometries.Intersectable;

import java.util.List;
import static primitives.Util.*;
import geometries.Intersectable.GeoPoint;

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
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }


    /**
     * this function get list of Geopoints and return the closest GeoPoint
     * to p0 of the ray
     * @param geoPoints
     * @return closest point to p0
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint>  geoPoints) {
        if (geoPoints == null || geoPoints.isEmpty())
            return null;

        return geoPoints.stream().
                min((p1,p2)-> Double.compare(p0.distanceSquared(p1.point),p0.distanceSquared(p2.point))).get();
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
