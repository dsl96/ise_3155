package primitives;

/**
 * pds class represent metrial of geometry
 */
public class Material {


    public Double3 Kd = Double3.ZERO;
    public Double3 Ks = Double3.ZERO;

    /**
     *  refracted index
     */
    public Double3 Kt = Double3.ZERO;
    /**
     *  reflected index
     */
    public Double3 Kr = Double3.ZERO;

    public int nShininess = 0;




    //blur glass
    //all parameters for the beam
    public int numOfRays=1;
    public double blurGlassDistance=1,blurGlassRadius=1;


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

    public Material setKt(Double3 kt) {
        this.Kt = kt;
        return this;
    }

    public Material setKr(double kr) {
        this.Kr = new Double3(kr);
        return this;
    }

    public Material setKt(double kt) {
        this.Kt = new Double3(kt);
        return this;
    }

    public Material setKr(Double3 kr) {
        this.Kr = kr;
        return this;
    }

    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

    public Material setBlurGlass(int numOfRays, double distance ,double radius) {

        if(numOfRays<1 || distance<=0 ||  radius<=0)
            throw new IllegalArgumentException("illegal argument in setBlurGlass ");

        this.numOfRays = numOfRays;
        this.blurGlassDistance=distance;
        this.blurGlassRadius = radius;

        return this;
    }

}
