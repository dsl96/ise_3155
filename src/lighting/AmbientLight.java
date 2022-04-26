package lighting;

import primitives.*;

/**
 * This class  represents an ambient light
 */
public class AmbientLight extends  Light {

    // ***************** Constructors ********************** //

    /**
     * get color and Ka for definition of intensity
     * @param iA Color
     * @param kA intensity
     */
    public AmbientLight(Color iA , Double3 kA) {
         super( iA.scale(kA) );
    }

    /**
     * default constructor set the intensity to black
     */
    public AmbientLight(){
            super( Color.BLACK);
    }
}
