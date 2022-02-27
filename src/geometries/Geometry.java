package geometries;

import primitives.Vector;
import primitives.Point;

/**
 * interface to all geometry classes
 */
public interface Geometry {
    /**
     * get the normal in given point
     * @param point
     * @return
     */
    public Vector getNormal(Point point);
}
