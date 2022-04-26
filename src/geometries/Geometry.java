package geometries;

import primitives.*;


/**
 * interface to all geometry classes
 */
public abstract class Geometry extends Intersectable{

    //emission of the geometry
   protected Color emission  = Color.BLACK;

    private  Material material = new Material();

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

    public Material getMaterial() {
        return material;
    }

    // ***************** Setters ********************** //

    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }
}
