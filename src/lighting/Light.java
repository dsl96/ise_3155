package lighting;


import primitives.Color;

/**
 * Light class represent Light
 */
public abstract class Light {

   private Color intensity;

    /******************** constructor *********************/

    /**
     * ctor
     *
     * @param intensity
     */
    protected Light(Color intensity) {

        this.intensity = intensity;
    }

    // ***************** getters ********************** //

    public Color getIntensity() {
        return intensity;
    }
}
