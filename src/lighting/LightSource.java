package lighting;

import primitives.*;

/**
 * LightSource interface to Light Source operations
 */
public interface LightSource {

    // ***************** getters ********************** //

    /**
     * get point and return the Intensity
     * of this light on this point
     * @param p
     * @return
     */
    public Color getIntensity(Point p);

    /**
     * get point and return the vector
     * from the light to the point
     * @param p
     * @return
     */
    public Vector getL(Point p);

}
