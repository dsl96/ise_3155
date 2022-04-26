package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.isZero;

/**
 * class Triangle represents two-dimensional triangle in 3D space
 */
public class Triangle extends Polygon {

    // ***************** Constructors ********************** //

    /**
     * get 3 points and summon the constructor of polygon
     * @param p1
     * @param p2
     * @param p3
     */
    public Triangle(Point p1, Point p2, Point p3){
        super (p1,p2,p3);
    }

    // ***************** Operations ******************** //

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> planeIntersections = plane.findGeoIntersections(ray);
        if (planeIntersections == null) return null;

        Point p0 = ray.getP0();
        Vector v = ray.getDir();

        Vector v1 = vertices.get(0).subtract(p0);
        Vector v2 = vertices.get(1).subtract(p0);
        Vector v3 = vertices.get(2).subtract(p0);

        double s1 = v.dotProduct(v1.crossProduct(v2));
        if (isZero(s1)) return null;
        double s2 = v.dotProduct(v2.crossProduct(v3));
        if (isZero(s2)) return null;
        double s3 = v.dotProduct(v3.crossProduct(v1));
        if (isZero(s3)) return null;

        //if all has the same sign
        if (s1  * s2 > 0 && s2 *s3> 0 )
            return planeIntersections.stream().map(gp->new GeoPoint(this, gp.point)).toList();

        return null;
    }
}
