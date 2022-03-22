package renderer;

import primitives.*;


public class Camera {
    private Point p0;
    private Vector vUp;
    private Vector vTo;
    private Vector vRight;
    private double vpHeight;
    private double vpWidth;
    private double distance;

    public Camera(Point p0,Vector vUp,Vector vTo) {
          if(!Util.isZero(vUp.dotProduct(vTo)))
              throw new IllegalArgumentException("ERROR: vUp isn't orthogonal to vTo");

          this.vUp=vUp;
          this.vTo=vTo;
          this.vRight=vTo.crossProduct(vUp);
          this.p0=p0;
    }

    /**
     * set width and height of the screen
     * @param vpWidth
     * @param vpHeight
     * @return
     */
    public Camera setVPSize(Double vpWidth,double vpHeight){

        if(vpHeight<=0||vpWidth<=0)
            throw new IllegalArgumentException("ERROR argument <=0");

        this.vpWidth =vpWidth;
        this.vpHeight=vpHeight;
        return this;
    }

    /**
     * set distance from camera to screen
     * @param distance
     * @return
     */
    public Camera setVPDistance(double distance){
        if(distance<=0)
            throw new IllegalArgumentException("ERROR argument <=0");
        this.distance=distance;

        return this;
    }

    public Ray constructRay(int nx,int ny,int i,int j){
        return null;
    }
    public double getVpHeight() {
        return vpHeight;
    }

    public double getVpWidth() {
        return vpWidth;
    }

    public double getDistance() {
        return distance;
    }
}
