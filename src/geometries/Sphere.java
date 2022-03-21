package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class Sphere implements Geometry {

    private Point center;
    private double radius;

    /**
     *  ctor get center and radius and create sphera
     * @param center
     * @param radius
     */
    Sphere(Point center, double radius) {
        if (radius <= 0)
            throw new IllegalArgumentException("radius cannt be negative or zero");

        this.radius = radius;
        this.center = center;
    }

    public Point getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    /**
     * get point on surface of the sphera
     * and retur the normal
     * @param point
     * @return
     */
    @Override
    public Vector getNormal(Point point) {
        return point.subtract(getCenter()).normalize();
    }

    @Override
    public String toString() {
        return "Sphera{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
