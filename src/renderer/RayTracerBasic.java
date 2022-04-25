package renderer;

import geometries.Intersectable;
import primitives.Point;
import primitives.Ray;
import scene.Scene;
import primitives.Color ;
import geometries.Intersectable.GeoPoint;


public class RayTracerBasic extends RayTracerBase {

    /******************** constructor *********************/

    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    // ***************** Operations ******************** //

    @Override
    Color traceRay(Ray ray) {
        //get all intersection points
        var IntersectionsLst = scene.geometries.findGeoIntersections(ray);

        //no intersection points
        if (IntersectionsLst == null) return scene.background;

        //return the color of the point
        return calcColor(ray.findClosestGeoPoint(IntersectionsLst));
    }

    /**
     * get point in scene and calculate its color
     * @param  geoPoint
     * @return
     */
    private Color calcColor( GeoPoint geoPoint) {

        return scene.ambientLight.getIntensity().add(geoPoint.geometry.getEmission());
    }
}
