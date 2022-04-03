package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * this class contains list of geometries
 */
public class Geometries implements Intersectable {

    List<Intersectable> geometries;

    // ***************** Constructors ********************** //

    /**
     * default constructor
     */
    public Geometries() {
        this.geometries = new ArrayList<>();
    }

    /**
     * get geometries and add to the list
     * @param geometries
     */
    public Geometries(Intersectable... geometries) {
        this();
        add(geometries);
    }

    // ***************** Operations ******************** //

    /**
     * add geometries to the list of geometries
     *
     * @param geometries
     */
    public void add(Intersectable... geometries) {
        for (Intersectable geo : geometries) {
            this.geometries.add(geo);
        }
    }

    @Override
    public List<Point> findIntersections(Ray ray) {

        List<Point> intersections = null;

        //find intersections in all the list of geometries
        for (Intersectable geo : this.geometries) {
            List<Point> tempIntersections = geo.findIntersections(ray);
            if (tempIntersections != null) {
                if (intersections == null)
                    intersections = new LinkedList<>();
                intersections.addAll(tempIntersections);
            }
        }
        return intersections;
    }
}

