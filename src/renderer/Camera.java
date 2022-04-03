package renderer;

import primitives.*;

import java.util.MissingResourceException;

import static primitives.Util.isZero;

/**
 *A camera in three-dimensional space
 * Contains location and direction of camera and scene
 * The camera produces an image by
 * Creating horns to cut the scene
 * And calculate the appropriate color
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
    public Camera setRayTracer(RayTracerBase rayTracerBase) {
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

    /**
     * render the image
     */
    public void renderImage() {
        //  check all camera properties Initialized
        validProprties();

        //loop on all pixels
        for (int i = 0; i < this.imageWriter.getNy(); ++i) {

            for (int j = 0; j < this.imageWriter.getNx(); ++j) {

                //cunstruct ray and send to ray tracer to get color
                var color = rayTracerBase.traceRay(constructRay(imageWriter.getNx(), imageWriter.getNy(), j, i));

               // write the color in place
                this.imageWriter.writePixel(j, i, color);
            }
        }
    }

    /**
     * print grid on image
     *
     * @param interval
     * @param color
     */
    public void printGrid(int interval, Color color) {

        if (imageWriter == null)
            throw new MissingResourceException("camera class", "imageWriter", "null");

        //print grid on image

        //write lines
        for (int j = 0; j < this.imageWriter.getNx(); j += interval) {
            for (int i = 0; i < this.imageWriter.getNy(); ++i)
                imageWriter.writePixel(j, i, color);
        }

        // writes columns
        for (int i = 0; i < this.imageWriter.getNy(); i += interval) {
            for (int j = 0; j < this.imageWriter.getNx(); ++j)
                imageWriter.writePixel(j, i, color);
        }
    }

    /**
     * write to image
     * throw error if imageWriter is null
     */
    public void writeToImage() {

        if (imageWriter == null)
            throw new MissingResourceException("camera class", "imageWriter", "null");

        imageWriter.writeToImage();
    }


    /**
     * check all camera properties Initialized
     * throw error if not
     */
    void validProprties() {
        if (this.vpHeight == 0.0)
            throw new MissingResourceException("camera class", "vpHeight", "0.0");

        if (this.vpWidth == 0.0)
            throw new MissingResourceException("camera class", "vpWidth", "0.0");

        if (this.distance == 0.0)
            throw new MissingResourceException("camera class", "distance", "0.0");

        if (this.imageWriter == null)
            throw new MissingResourceException("camera class", "imageWriter", "null");

        if (this.rayTracerBase == null)
            throw new MissingResourceException("camera class", "rayTracerBase", "null");
    }
}
