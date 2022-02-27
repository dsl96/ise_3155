package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Tube implements Geometry {

    private Ray axisRay;
    private double radius;

    /**
     * ctor get axis ray and positive radius
     *
     * @param axisRay
     * @param radius
     */
    public Tube(Ray axisRay, double radius) {
        if (radius <= 0)
            throw new IllegalArgumentException("radius cannt be negative or zero");

        this.radius = radius;
        this.axisRay = axisRay;
    }
    public Ray getAxisRay(){
        return axisRay;
    }
    public double getRadius(){
        return radius;
    }
    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}
