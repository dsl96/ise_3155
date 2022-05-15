package primitives;

import geometries.Intersectable;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.*;

import geometries.Intersectable.GeoPoint;

/**
 * Ray class represents 3D ray
 */
public class Ray {
    /**
     * epsilon to move point from surface
     */
    private static final double DELTA = 0.1;

    private Point p0;
    private Vector dir;

    /******************** constructor *********************/

    /**
     * constructor
     *
     * @param p0  start point
     * @param dir direction
     */
    public Ray(Point p0, Vector dir) {
        this.dir = dir.normalize();
        this.p0 = new Point(p0._xyz);
    }

    /**
     * constructor move start point of ray by delta
     *
     * @param point
     * @param direction
     * @param normal
     */
    public Ray(Point point, Vector direction, Vector normal) {
        //point + normal.scale(±DELTA)
        dir = direction.normalize();

        double nv = normal.dotProduct(direction);

        Vector normalDelta = normal.scale((nv > 0 ? DELTA : -DELTA));
        p0 = point.add(normalDelta);
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
     * to p0 of the ray
     *
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
     *
     * @param geoPoints
     * @return closest point to p0
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> geoPoints) {
        if (geoPoints == null || geoPoints.isEmpty())
            return null;

        return geoPoints.stream().
                min((p1, p2) -> Double.compare(p0.distanceSquared(p1.point), p0.distanceSquared(p2.point))).get();
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


    public List<Ray> generateBeam(Vector n, double radius, double distance, int numOfRays) {
        List<Ray> rays = new LinkedList<Ray>();
        rays.add(this);// Including the main ray
        if (numOfRays == 1 || isZero(radius))// The component (glossy surface /diffuse glass) is turned off
            return rays;

        // the 2 vectors that create the virtual grid for the beam
        Vector nX = dir.createNormal();
        Vector nY = dir.crossProduct(nX);

        Point centerCircle = this.getTargetPoint(distance);
        Point randomPoint;
        Vector v12 ;


        double rand_x, rand_y,delta_radius=radius/(numOfRays-1);
        double nv = n.dotProduct(dir);

        for (int i = 1; i < numOfRays; i++) {
            randomPoint=centerCircle;
            rand_x=random(-radius,radius);
            rand_y= Math.sqrt(radius*radius-rand_x*rand_x);

            try {
                randomPoint = randomPoint.add(nX.scale(rand_x));
            }
            catch (Exception ex){}

            try {
                randomPoint = randomPoint.add(nY.scale(rand_y));
            }
            catch (Exception ex){}



            v12 = randomPoint.subtract(p0).normalize();

            double nt = alignZero(n.dotProduct(v12));

            if (nv * nt > 0) {
                rays.add(new Ray(p0, v12));
            }
            radius-=delta_radius;
        }

        return rays;
    }



}
