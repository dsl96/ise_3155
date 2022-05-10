package renderer;

import lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;
import java.util.List;
import static primitives.Util.*;

public class RayTracerBasic extends RayTracerBase {

    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;

    /******************** constructor *********************/

    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /***************** Operations ********************/

    @Override
    Color traceRay(Ray ray) {
        //get the closet intersection point
        var intersection = findClosestIntersection(ray);

        //no intersection points
        if (intersection == null) return scene.background;

        //return the color of the point
        return calcColor(intersection,ray);
    }

    /**
     * get point in scene and calculate its color
     * @param  geoPoint
     * @return
     */
    private Color calcColor( GeoPoint geoPoint,Ray ray) {

        return scene.ambientLight.getIntensity()
                .add(calcColor(geoPoint,ray,MAX_CALC_COLOR_LEVEL,new Double3(1)));
    }

    /**
     * get point in scene and calculate its color
     * @param gp
     * @param ray
     * @param level
     * @param k
     * @return
     */
    private Color calcColor(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(gp, ray,k).add(gp.geometry.getEmission());

        return 1 == level ? color : color.add(calcGlobalEffects(gp, ray, level, k));
    }

    /**
     * Calculate the effect of different light sources on point in scene
     * according to the "Phong model"
     * @param gp point on geometry in  scene
     * @param ray ray from camera to the gp
     * @return
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray,Double3 k) {
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

            if (nl * nv > 0  ) { // sign(nl) == sing(nv)
                Double3 ktr = transparency(gp,lightSource,l,n);
                if (!ktr .product(k).lowerThan(MIN_CALC_COLOR_K)   ) {
                    Color lightIntensity = lightSource.getIntensity(gp.point).scale(ktr);
                    color = color.add(calcDiffusive(kd, nl, lightIntensity),
                            calcSpecular(ks, l, n, nl, v, nShininess, lightIntensity));
                }
            }
        }
        return color;
    }

    /**
     * Calculate global effect reflected and refracted
     * @param gp
     * @param inRay
     * @param level
     * @param k
     * @return
     */
    private Color calcGlobalEffects(GeoPoint gp, Ray inRay, int level, Double3 k) {
        Color color = Color.BLACK;

        Double3 kr = gp.geometry.getMaterial().Kr, kkr = kr.product(k);
        if ( !kkr.lowerThan( MIN_CALC_COLOR_K)){
            Ray reflectedRay = constructReflectedRay(gp.point,inRay , gp.geometry.getNormal(gp.point)  );
            GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);

            color =reflectedPoint==null?color: color.add(calcColor(reflectedPoint, reflectedRay,level-1,kkr)
                    .scale(kr));
        }

        Double3 kt = gp.geometry.getMaterial().Kt, kkt = kt.product(k);
        if ( !kkt.lowerThan( MIN_CALC_COLOR_K)) {
            Ray refractedRay = constructRefractedRay(gp.point,inRay , gp.geometry.getNormal(gp.point));

            GeoPoint refractedPoint = findClosestIntersection(refractedRay);
            color = refractedPoint==null ? color: color.add(calcColor(refractedPoint, refractedRay,level-1,kkt)
                .scale(kt));
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

    /**
     *  get light and gp and chexk if ther is element between them
     * @param gp
     * @param light
     * @param l
     * @param n
     * @return
     */
    private boolean unshaded(GeoPoint gp, LightSource light, Vector l, Vector n) {
        Vector lightDirection = l.scale(-1); // from point to light source

        Ray lightRay = new Ray(gp.point, lightDirection,n);

        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay, light.getDistance(gp.point));
        return intersections==null ||!intersections.stream().anyMatch (g -> g.geometry.getMaterial().Kt== Double3.ZERO);
    }

    /**
     * get light and gp and move ao all the objects between them
     * and calculate the transparency
     * @param gp
     * @param light
     * @param l
     * @param n
     * @return
     */
    private Double3 transparency(GeoPoint gp, LightSource light, Vector l, Vector n) {

        Vector lightDirection = l.scale(-1); // from point to light source

        Ray lightRay = new Ray(gp.point, lightDirection,n);

        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay, light.getDistance(gp.point));
        Double3 ktr = new Double3(1d);
        if (intersections == null) return ktr;

        for(GeoPoint p :intersections)
            ktr = ktr.product(p.geometry.getMaterial().Kt);
        return ktr;
    }

    /**
     * Calculate refracted ray
     * @param pointGeo
     * @param inRay
     * @param n
     * @return
     */
    private Ray constructRefractedRay(Point pointGeo, Ray inRay, Vector n) {
        return new Ray(pointGeo, inRay.getDir(), n);
    }

    /**
     * Calculate Reflected ray
     * @param pointGeo
     * @param inRay
     * @param n
     * @return
     */
    private Ray constructReflectedRay(Point pointGeo, Ray inRay, Vector n) {
        //𝒓=𝒗 −𝟐∙(𝒗∙𝒏)∙𝒏
        Vector v = inRay.getDir();
        double vn = v.dotProduct(n);

        if (vn == 0) {
            return null;
        }

        Vector r = v.subtract(n.scale(2 * vn));
        return new Ray(pointGeo, r, n);
    }

    /**
     * get ray and return the closet intersection geoPoint
     * @param ray
     * @return
     */
    private GeoPoint findClosestIntersection(Ray ray){
        return ray.findClosestGeoPoint(scene.geometries.findGeoIntersections( ray));
    }
}
