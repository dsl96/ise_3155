package lighting;

import primitives.*;

/**
 * class PointLight in the space
 */
public class PointLight extends Light implements LightSource {

    private  Point position;

    private double Kc =1;
    private double Kl =0;
    private double Kq =0;

    /**
     * ctor
     *
     * @param intensity
     */

    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    @Override
    public Color getIntensity(Point p) {
        double dSquared = p.distanceSquared(position);
        double d = p.distance(position);

        return (getIntensity().reduce(Kc + Kl * d + Kq * dSquared));
    }

    @Override
    public Vector getL(Point p) {
        if (p.equals(position)) {
            return null;
        }
        return p.subtract(position).normalize();
    }

    // ***************** setters ********************** //

    /**
     * return this for builder
     * @param kc
     * @return
     */
    public PointLight setKc(double kc) {
        this.Kc = kc;
        return this;
    }

    /**
     * return this for builder
     * @param kq
     * @return
     */
    public PointLight setKq(double kq) {
        this.Kq = kq;
        return this;
    }

    /**
     * return this for builder
     * @param kl
     * @return
     */
    public PointLight setKl(double kl) {
        this.Kl = kl;
        return this;
    }
}
