package geometries;

import primitives.Point;
import primitives.Ray;
import java.util.List;

/**
 * interface of Intersectable by ray
 */
public interface Intersectable {

    /**
     * get ray for intersect
     * @param ray
     * @return all Intersect points or null if no intersect
     */
    public List<Point> findIntersections(Ray ray);
}
