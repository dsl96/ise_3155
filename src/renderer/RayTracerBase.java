package renderer;

import primitives.*;
import scene.Scene;

/**
 * Ray Tracer abstract class
 * contains functions to calculate the color of any point in scene
 */
abstract class RayTracerBase {
    protected Scene scene;

    /******************** constructor *********************/

    /**
     * constructor
     * @param scene
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    // ***************** Operations ******************** //

    /**
     * get ray
     * calculate the intersections with the scene
     * and return the color of the closest intersection
     * @param ray
     * @return
     */
     abstract Color traceRay(Ray ray);
}
