package primitives;

/**
 * pds class represent metrial of geometry
 */
public class Material {

    public Double3 Kd = Double3.ZERO;
    public Double3 Ks = Double3.ZERO;

    public int nShininess = 0;


    // ***************** setters ********************** //

    public Material setKd(double kd) {
        this.Kd = new Double3(kd);
        return this;
    }

    public Material setKs(double ks) {
        this.Ks = new Double3(ks);
        return this;
    }

    public Material setKd(Double3 kD) {
        this.Kd = kD;
        return this;
    }

    public Material setKs(Double3 kS) {
        this.Ks = kS;
        return this;
    }

    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

}
