package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry {

    private Point p0;
    private Vector normal;

    /**
     * ctor get normal and point
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
        p0 = null;
        normal = null;//////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    /**
     * get the normal
     * @return
     */
    public Vector getNormal() {
        return normal;
    }

    /**
     * get point
     * @return
     */
    public Point getPoint(){
        return p0;
    }

    @Override
    public Vector getNormal(Point point) {
        return getNormal();
    }

    @Override
    public String toString() {
        return "Plane{" +
                "p0=" + p0 +
                ", normal=" + normal +
                '}';
    }
}
