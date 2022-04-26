package lighting;

import primitives.*;

public class DirectionalLight extends Light implements LightSource {

    private Vector direction;

    /**
     * ctor
     *
     * @param intensity
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction;
    }

    @Override
    public Color getIntensity(Point p) {
        return super.getIntensity();
    }

    @Override
    public Vector getL(Point p) {
        return direction;
    }
}
