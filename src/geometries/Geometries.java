package geometries;

import primitives.BoundingBox;
import primitives.Point;
import primitives.Ray;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * this class contains list of geometries
 */
public class Geometries extends Intersectable {

    List<Intersectable> geometries;

    // ***************** Constructors ********************** //

    /**
     * default constructor
     */
    public Geometries() {
        this.geometries = new ArrayList<>();
        this.boundingBox = new BoundingBox();
    }

    /**
     * get geometries and add to the list
     *
     * @param geometries
     */
    public Geometries(Intersectable... geometries) {
        this();
        add(geometries);
    }

    // ***************** Operations ******************** //
    public Geometries setBoundingBox(Point p1, Point p2) {
        boundingBox = new BoundingBox(p1, p2);
        return this;
    }

    /**
     * add geometries to the list of geometries
     *
     * @param geometries
     */
    public void add(Intersectable... geometries) {
        for (Intersectable geo : geometries) {
            this.geometries.add(geo);
        }

        //build a bounding box
        //search in all new geometries
        //for the min and max X,Y,Z (if they bigger then the current x,y,z bounding box)
        double xMax = Double.NEGATIVE_INFINITY;
        double xMin =  Double.MAX_VALUE;

        double yMax =  Double.NEGATIVE_INFINITY;
        double yMin = Double.MAX_VALUE;

        double zMax =  Double.NEGATIVE_INFINITY;
        double zMin = Double.MAX_VALUE;


        for (Intersectable g : this.geometries) {

            //check x
            if (g.boundingBox.getxMin() < xMin)
                xMin = g.boundingBox.getxMin();

            if (g.boundingBox.getxMax() > xMax)
                xMax = g.boundingBox.getxMax();

            //check y
            if (g.boundingBox.getyMin() < yMin)
                yMin = g.boundingBox.getyMin();

            if (g.boundingBox.getyMax() > yMax)
                yMax = g.boundingBox.getyMax();

            //check z
            if (g.boundingBox.getzMin() < zMin)
                zMin = g.boundingBox.getzMin();

            if (g.boundingBox.getzMax() > zMax)
                zMax = g.boundingBox.getzMax();
        }
        boundingBox = new BoundingBox(new Point(xMin,yMin,zMin), new Point(xMax,yMax,zMax));
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {

        if (this.boundingBox!=null && !this.boundingBox.IntersectionBox(ray))
            return null;


        List<GeoPoint> intersections = null;

        //find intersections in all the list of geometries
        for (Intersectable geo : this.geometries) {
            List<GeoPoint> tempIntersections = geo.findGeoIntersections(ray, maxDistance);
            if (tempIntersections != null) {
                if (intersections == null)
                    intersections = new LinkedList<>();
                intersections.addAll(tempIntersections);
            }
        }
        return intersections;
    }
}

