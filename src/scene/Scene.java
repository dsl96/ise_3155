package scene;

import geometries.Intersectable;
import lighting.AmbientLight;
import geometries.Geometries;
import primitives.*;

public class Scene {



    public String name;
    public Color background;
    public AmbientLight ambientLight;
    public Geometries geometries ;

    // ***************** Constructor ********************** //

    /**
     * constructor that get the name of scene
     * this constructor builds a new empty geometries
     * @param name
     */
    public Scene (String name){
        this.name=name;
        geometries = new Geometries();
    }

    // ***************** Setters ********************** //

    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    public Scene setGeometries(Intersectable... geometries) {
        this.geometries.add(geometries);
        return this;
    }

}
