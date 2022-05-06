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
        this.direction = direction.normalize();
    }

    @Override
    public Color getIntensity(Point p) {
        return super.getIntensity();
    }

    @Override
    public Vector getL(Point p) {
        return direction;
    }

    @Override
    public double getDistance(Point point) {
        //Directional Light had no location
        return Double.POSITIVE_INFINITY;
    }
}
