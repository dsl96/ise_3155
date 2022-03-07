package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.*;

public class Cylinder extends Tube {

    private double height;

    /**
     * ctor get axis ray and positive radius
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

    public double getHeight() {
        return height;
    }

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
    public String toString() {
        return "Cylinder{" +
                "height=" + height +
                '}';
    }
}
