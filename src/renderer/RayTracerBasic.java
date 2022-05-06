package renderer;

import lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.*;


public class RayTracerBasic extends RayTracerBase {

    /**
     * epsilon to move point from surface
     */
    private static final double DELTA = 0.1;

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
     * @param gp point on geometry in  scene
     * @param ray ray from camera to the gp
     * @return
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
        Vector v = ray.getDir ();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return Color.BLACK;

        int nShininess = gp.geometry.getMaterial().nShininess;

        Double3 kd = gp.geometry.getMaterial().Kd,
                ks = gp.geometry.getMaterial().Ks;

        Color color = Color.BLACK;
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));

            if (nl * nv > 0 && unshaded(gp, lightSource,   l, n, nv) ) { // sign(nl) == sing(nv)
                Color lightIntensity = lightSource.getIntensity(gp.point);
                color = color.add(calcDiffusive(kd, nl, lightIntensity),
                        calcSpecular(ks, l, n,nl, v, nShininess, lightIntensity));
            }
        }
        return color;
    }

    /**
     * Calculate Diffusive component of light reflection.
     * @param kd
     * @param nl
     * @param ip
     * @return
     */
    private Color calcDiffusive(Double3 kd, double nl, Color ip) {
        return ip.scale(kd.scale(Math.abs(nl) ));
    }

    /**
     *  Calculate Specular component of light reflection
     * @param ks
     * @param l
     * @param n
     * @param nl
     * @param v
     * @param nShininess
     * @param ip
     * @return
     */
    private  Color calcSpecular(Double3 ks, Vector l, Vector n, double nl, Vector v, int nShininess,  Color ip) {

        Vector R = l.add(n.scale(-2 * nl)); // nl must not be zero!
        double minusVR = -alignZero(R.dotProduct(v));
        if (minusVR <= 0) {
            return new primitives.Color(Color.BLACK.getColor()); // view from direction opposite to r vector
        }
        return ip.scale(ks.scale( Math.pow(minusVR, nShininess)));
    }



    private boolean unshaded(GeoPoint gp, LightSource light, Vector l, Vector n, double nv) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector epsVector = n.scale(nv < 0 ?  DELTA : -DELTA);
        Point point = gp.point.add(epsVector);
        Ray lightRay = new Ray(point, lightDirection);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay, light.getDistance(gp.point));
        return intersections==null ;
    }










































}
