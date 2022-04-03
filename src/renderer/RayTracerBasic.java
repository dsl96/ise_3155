package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

public class RayTracerBasic extends RayTracerBase {

    public RayTracerBasic(Scene scene) {
        super(scene);
    }

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
     * get point and calculate the color of the point
     *
     * @param point
     * @return
     */
    private Color calcColor(Point point) {
        return scene.ambientLight.getIntensity();
    }
}
