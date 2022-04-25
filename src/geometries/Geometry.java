package geometries;

import primitives.*;


/**
 * interface to all geometry classes
 */
public abstract class Geometry extends Intersectable{


    //emission of the geometry
   protected Color emission  = Color.BLACK;

    /**
     * get the normal in given point
     * @param point
     * @return
     */
    public abstract Vector getNormal(Point point);

    // ***************** Getters ********************** //

    public Color getEmission() {
        return emission;
    }

    // ***************** Setters ********************** //

    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }
}
