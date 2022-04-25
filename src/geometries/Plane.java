package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.*;

/**
 * Plane class represents two-dimensional plane in 3D Cartesian coordinate
 * system
 */
public class Plane extends Geometry {

    private Point p0;
    private Vector normal;

    // ***************** Constructors ********************** //

    /**
     * constructor get normal and point
     *
     * @param p0
     * @param normal
     */
    public Plane(Point p0, Vector normal) {
        this.normal = normal.normalize();
        this.p0 = p0;
    }

    /**
     * get b3 point and create a plane
     * @param p1
     * @param p2
     * @param p3
     */
    public Plane(Point p1, Point p2, Point p3) {
        p0 = p1;

        try { // try for case the ctor get all point on the same vector or at least two point are the same
            normal = p1.subtract(p2).crossProduct(p1.subtract(p3)).normalize();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("your points are on the same vector");
        }
    }

    // ***************** Getters ********************** //

    /**
     * get the normal
     *
     * @return normal
     */
    public Vector getNormal() {
        return normal;
    }

    /**
     * get point
     *
     * @return
     */
    public Point getPoint() {
        return p0;
    }

    // ***************** Operations ******************** //

    @Override
    public Vector getNormal(Point point) {
        return getNormal();
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        Vector p0Q;  //p0 - q
        try {
            p0Q = p0.subtract(ray.getP0());
        } catch (IllegalArgumentException e) {
            return null; // ray starts from point Q - no intersections
        }

        double nv = normal.dotProduct(ray.getDir());

        // ray is parallel to the plane - no intersections
        if (isZero(nv))
            return null;

        double t = alignZero(normal.dotProduct(p0Q) / nv);

        //ray starts in or after the plane
        if (t <= 0) {
            return null;
        }

        return List.of( new GeoPoint(this, ray.getTargetPoint(t)));
    }

    @Override
    public String toString() {
        return "Plane{" +
                "p0=" + p0 +
                ", normal=" + normal +
                '}';
    }
}
