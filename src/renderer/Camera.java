package renderer;

import primitives.*;

import java.util.MissingResourceException;

import static primitives.Util.isZero;

/**
 * ////////////////////////////////////////////////////////////
 */
public class Camera {
    private Point p0;
    private Vector vUp;
    private Vector vTo;
    private Vector vRight;
    private double vpHeight;
    private double vpWidth;
    private double distance;
    private ImageWriter imageWriter;
    private RayTracerBase rayTracerBase;

    public Camera(Point p0, Vector vTo, Vector vUp) {
        if (!isZero(vUp.dotProduct(vTo)))
            throw new IllegalArgumentException("ERROR: vUp isn't orthogonal to vTo");

        this.vUp = vUp.normalize();
        this.vTo = vTo.normalize();
        this.vRight = vTo.crossProduct(vUp).normalize();
        this.p0 = p0;
    }

    /******************** setters *********************/

    /**
     * set width and height of the screen
     * return this for builder
     *
     * @param vpWidth
     * @param vpHeight
     * @return
     */
    public Camera setVPSize(Double vpWidth, double vpHeight) {

        if (vpHeight <= 0 || vpWidth <= 0)
            throw new IllegalArgumentException("ERROR argument <=0");

        this.vpWidth = vpWidth;
        this.vpHeight = vpHeight;
        return this;
    }

    /**
     * set distance from camera to screen
     * return this for builder
     *
     * @param distance
     * @return
     */
    public Camera setVPDistance(double distance) {
        if (distance <= 0)
            throw new IllegalArgumentException("ERROR argument <=0");
        this.distance = distance;

        return this;
    }

    /**
     * set Ray Tracer Base
     * return this for builder
     *
     * @param rayTracerBase
     * @return
     */
    public Camera setRayTracerBase(RayTracerBase rayTracerBase) {
        this.rayTracerBase = rayTracerBase;
        return this;
    }

    /**
     * set Image Writer
     * return this for builder
     *
     * @param imageWriter
     * @return
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }


    /************************** getters *******************/

    public double getVpHeight() {
        return vpHeight;
    }

    public double getVpWidth() {
        return vpWidth;
    }

    public double getDistance() {
        return distance;
    }


    public Ray constructRay(int nX, int nY, int j, int i) {

        Point Pij = p0.add(vTo.scale(distance));
        double Ry = vpHeight / nY;
        double Rx = vpWidth / nX;

        double xJ = Rx * (j - ((nX - 1) / 2.0));
        double yI = -Ry * (i - ((nY - 1) / 2.0));

        if (!isZero(xJ)) Pij = Pij.add(vRight.scale(xJ));
        if (!isZero(yI)) Pij = Pij.add(vUp.scale(yI));

        return new Ray(p0, Pij.subtract(p0));
    }


}
