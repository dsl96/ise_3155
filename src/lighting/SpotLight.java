package lighting;

import primitives.*;

import static primitives.Util.alignZero;


public class SpotLight extends PointLight{

    private Vector direction;

    /**
     * ctor
     *
     * @param intensity
     */
    public SpotLight(Color intensity,Point position, Vector direction) {
        super(intensity,position);
        this.direction= direction.normalize();
    }

    @Override
    public Color getIntensity(Point p) {

        double projection =alignZero( direction.dotProduct(getL(p)));

        double factor = Math.max(0, projection);

        Color pointlightIntensity = super.getIntensity(p);

        return (pointlightIntensity.scale(factor));
    }
}
