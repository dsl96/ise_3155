package lighting;

import primitives.*;

/**
 * This class  represents an ambient light
 */
public class AmbientLight {

    Color intensity;

    // ***************** Constructors ********************** //

    /**
     * get color and Ka for definition of intensity
     * @param iA Color
     * @param kA intensity
     */
    public AmbientLight(Color iA , Double3 kA) {
        this.intensity = iA.scale(kA);
    }

    /**
     * default constructor set the intensity to black
     */
    public AmbientLight(){
            this.intensity = Color.BLACK;
    }
    // ***************** getter ********************** //
    public Color getIntensity() {
        return intensity;
    }
}
