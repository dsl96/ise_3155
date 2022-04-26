package renderer;

import lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;
import static primitives.Util.*;


public class RayTracerBasic extends RayTracerBase {

    /******************** constructor *********************/

    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /***************** Operations ********************/

    @Override
    Color traceRay(Ray ray) {
        //get all intersection points
        var IntersectionsLst = scene.geometries.findGeoIntersections(ray);

        //no intersection points
        if (IntersectionsLst == null) return scene.background;

        //return the color of the point
        return calcColor(ray.findClosestGeoPoint(IntersectionsLst),ray);
    }

    /**
     * get point in scene and calculate its color
     * @param  geoPoint
     * @return
     */
    private Color calcColor( GeoPoint geoPoint,Ray ray) {

        return scene.ambientLight.getIntensity().add(geoPoint.geometry.getEmission())
                .add(calcLocalEffects(geoPoint,ray));
    }


    /**
     * Calculate the effect of different light sources on point in scene
     * according to the "Phong model"
     * @param intersection point on geometry in  scene
     * @param ray ray from camera to the intersection
     * @return
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
        Vector v = ray.getDir ();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return Color.BLACK;

        int nShininess = intersection.geometry.getMaterial().nShininess;

        Double3 kd = intersection.geometry.getMaterial().Kd,
                ks = intersection.geometry.getMaterial().Ks;

        Color color = Color.BLACK;
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));

            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Color lightIntensity = lightSource.getIntensity(intersection.point);
                color = color.add(calcDiffusive(kd, nl, lightIntensity),
                        calcSpecular(ks, l, n,nl, v, nShininess, lightIntensity));
            }
        }
        return color;
    }


    private Color calcDiffusive(Double3 kd, double nl, Color ip) {
        return ip.scale(kd.scale(Math.abs(nl) ));
    }


    private  Color calcSpecular(Double3 ks, Vector l, Vector n, double nl, Vector v, int nShininess,  Color ip) {

        Vector R = l.add(n.scale(-2 * nl)); // nl must not be zero!
        double minusVR = -alignZero(R.dotProduct(v));
        if (minusVR <= 0) {
            return new primitives.Color(Color.BLACK.getColor()); // view from direction opposite to r vector
        }
        return ip.scale(ks.scale( Math.pow(minusVR, nShininess)));
    }

}
