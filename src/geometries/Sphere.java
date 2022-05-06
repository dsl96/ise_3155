package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;

import java.util.List;

/**
 * lass Sphere represents sphere in 3D space
 */
public class Sphere extends Geometry {

    private Point center;
    private double radius;

    // ***************** Constructors ********************** //

    /**
     *  constructor get center and radius and create sphere
     * @param center
     * @param radius
     */
    public Sphere(Point center, double radius) {
        if (radius <= 0)
            throw new IllegalArgumentException("radius cannt be negative or zero");

        this.radius = radius;
        this.center = center;
    }

    // ***************** Getters ********************** //

    public Point getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    // ***************** Operations ******************** //

    @Override
    public Vector getNormal(Point point) {
        return point.subtract(getCenter()).normalize();
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        Point p0 = ray.getP0();
        Vector v = ray.getDir();
        Vector u;

        try {
            u = center.subtract(p0);   // p0 == center the ray start from the center of the sphere
        } catch (IllegalArgumentException e) {

            if (alignZero(  maxDistance-this.radius) <= 0) return null;

            return List.of( new GeoPoint(this, ray.getTargetPoint(this.radius) ));
        }

        double tm = alignZero(v.dotProduct(u));
        double dSquared = u.lengthSquared() - tm * tm;
        double thSquared = alignZero(this.radius * this.radius - dSquared);

        if (thSquared <= 0) return null;//no intersections

        double th = alignZero(Math.sqrt(thSquared));
        if (th == 0) return null;//ray tangent to sphere

        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);

        //bool parameter if the distance from the rey source less than max distance and  the ray not start after the point
        boolean t1InDistance = alignZero(maxDistance - t1) > 0  && t1 > 0 ;
        boolean t2InDistance = alignZero(maxDistance - t2) > 0 && t2 > 0;

        //ray starts after sphere or far then max distance
        if(   !t1InDistance  &&   !t2InDistance ) return null;

        //2 intersections
        if (  t1InDistance  && t2InDistance)   {
            return List.of(
                    new GeoPoint (this,ray.getTargetPoint(t1) )
                    ,new GeoPoint (this,ray.getTargetPoint(t2) )); //P1 , P2
        }

        //1 intersection
        if ( t1InDistance)
            return List.of(new GeoPoint (this,ray.getTargetPoint(t1) ));
        else
            return List.of(new GeoPoint (this,ray.getTargetPoint(t2) ));
    }

    @Override
    public String toString() {
        return "Sphera{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }
}
