package scene;

import geometries.Intersectable;
import lighting.AmbientLight;
import geometries.Geometries;
import primitives.*;

/**
 * Scene class represents scene on 3D space
 * contains geometries and lights
 */
public class Scene {

    public String name;
    public Color background= Color.BLACK;
    public AmbientLight ambientLight;
    public Geometries geometries;

    // ***************** Constructor ********************** //

    /**
     * constructor that get the name of scene
     * this constructor builds a new empty geometries
     *
     * @param name
     */
    public Scene(String name) {
        this.name = name;
        geometries = new Geometries();
    }

    // ***************** Setters ********************** //

    /**
     * set ambient Light
     * return this for builder
     * @param ambientLight
     * @return this
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * set Background
     * return this for builder
     * @param background
     * @return this
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * set Geometries
     * return this for builder
     * @param geometries
     * @return this
     */
    public Scene setGeometries(Intersectable... geometries) {
        this.geometries.add(geometries);
        return this;
    }
}
