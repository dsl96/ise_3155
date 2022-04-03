package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

public class RayTracerBasic extends RayTracerBase {

    /******************** constructor *********************/

    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    // ***************** Operations ******************** //

    @Override
    Color traceRay(Ray ray) {
        //get all intersection points
        var IntersectionsLst = scene.geometries.findIntersections(ray);

        //no intersection points
        if (IntersectionsLst == null) return scene.background;

        //return the color of the point
        return calcColor(ray.findClosestPoint(IntersectionsLst));
    }

    /**
     * get point in scene and calculate its color
     * @param point
     * @return
     */
    private Color calcColor(Point point) {
        return scene.ambientLight.getIntensity();
    }
}
