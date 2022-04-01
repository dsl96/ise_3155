package renderer;

import primitives.*;
import scene.Scene;

abstract class RayTracerBase {
    protected Scene scene;

    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

     abstract Color traceRay(Ray ray);
}
