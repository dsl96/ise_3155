package scene;

import geometries.Intersectable;
import lighting.AmbientLight;
import geometries.Geometries;
import lighting.LightSource;
import primitives.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Scene class represents scene on 3D space
 * contains geometries and lights
 */
public class Scene {

    public String name;
    public Color background = Color.BLACK;
    public AmbientLight ambientLight =  new AmbientLight();
    public Geometries geometries;



    //list of all the lights in scene
    public List<LightSource> lights = new LinkedList<>();

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
     *
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
     *
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
     *
     * @param geometries
     * @return this
     */
    public Scene setGeometries(Intersectable... geometries) {
        this.geometries.add(geometries);
        return this;
    }

    /**
     * set lights
     * return this for builder
     *
     * @param lights
     * @return this
     */
    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }
}
