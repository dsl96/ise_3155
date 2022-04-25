package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.*;

/**
 * Cylinder class represents cylinder in 3D Cartesian coordinate
 * system
 */
public class Cylinder extends Tube {

    private double height;

    // ***************** Constructors ********************** //
    /**
     * constructor get axis ray and positive radius
     *
     * @param axisRay
     * @param radius
     * @param height
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        if (height <= 0)
            throw new IllegalArgumentException("height can't be negative or zero");
        this.height = height;
    }

    // ***************** Getters ********************** //

    public double getHeight() {
        return height;
    }

    // ***************** Operations ******************** //

    @Override
    public Vector getNormal(Point point) {

        Point p0 = getAxisRay().getP0();
        Vector dir = getAxisRay().getDir();
        Point pTop = p0.add(dir.scale(getHeight()));

        //if the point is at the top of the cylinder
        if (point.equals(pTop) || isZero(dir.dotProduct(point.subtract(pTop))))
            return dir;

        //if the point is at the base of the cylinder
        if (point.equals(p0) || isZero(dir.dotProduct(point.subtract(p0))))
            return dir.scale(-1);

        return super.getNormal(point);
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        return null;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + height +
                '}';
    }
}
