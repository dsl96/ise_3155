package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.*;

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

        //get the center of the tube
        Point _0 = axisRay.getP0();
        //get the vector direction of the tube
        Vector _v= axisRay.getDir();

        //(p-p0) * _v
        double _t = point.subtract(_0).dotProduct(_v);

        if(!isZero(_t))//if it's close to 0, we'll get ZERO vector exception
            _0= _0.add(_v.scale(_t));

        return point.subtract(_0).normalize();
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}














