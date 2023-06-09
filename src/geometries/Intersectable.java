package geometries;

import primitives.BoundingBox;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * interface of Intersectable by ray
 */
public abstract class Intersectable {

    BoundingBox boundingBox = new BoundingBox();

    /**
     * class contain point on
     * geometry and the geometry
     */
    public static class GeoPoint {

        public  Geometry geometry;
        public  Point point;

        /**
         * ctor
         * @param point
         * @param geometry
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (this == obj) return true;
            if (!(obj instanceof GeoPoint)) return false;

            GeoPoint other = (GeoPoint) obj;
            return this.geometry.equals(other.geometry) && this.point.equals(other.point);
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }
    }

    /**
     * get ray for intersect
     * @param ray
     * @return all Intersect points or null if no intersect
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream().map(gp -> gp.point).toList();
    }


    /**
     * get ray for intersect
     * return list of geoPoints
     * @param ray
     * @return all Intersect points or null if no intersect
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray , double maxDistance );

    /**
     * get ray for intersect
     * return list of geoPoints
     * @param ray
     * @return all Intersect points or null if no intersect
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
    }


    /**
     * get  ray and return the intersection in the max distance
     * @param ray
     * @param maxDistance
     * @return
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        return findGeoIntersectionsHelper(ray, maxDistance);
    }


}
