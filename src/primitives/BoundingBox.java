package primitives;

import static primitives.Util.isZero;

public class BoundingBox {


    /**
     * @return the x0
     */
    public double getxMin() {
        return xMin;
    }

    /**
     * @return the x1
     */
    public double getxMax() {
        return xMax;
    }

    /**
     * @return the y0
     */
    public double getyMin() {
        return yMin;
    }

    /**
     * @return the y1
     */
    public double getyMax() {
        return yMax;
    }

    /**
     * @return the z0
     */
    public double getzMin() {
        return zMin;
    }

    /**
     * @return the z1
     */
    public double getzMax() {
        return zMax;
    }

    protected final double xMin;
    protected final double xMax;

    protected final double yMin;
    protected final double yMax;

    protected final double zMin;
    protected final double zMax;

    public BoundingBox() {

         this.xMin=Double.NEGATIVE_INFINITY;
          this.xMax= Double.MAX_VALUE;

        this.yMin=Double.NEGATIVE_INFINITY;
        this.yMax= Double.MAX_VALUE;

        this.zMin=Double.NEGATIVE_INFINITY;
        this.zMax= Double.MAX_VALUE;

    }

    /**
     *
     * @param p1
     * @param p2
     */
    public BoundingBox(Point p1, Point p2) {
        if (p1.getX() < p2.getX()) {
            this.xMin = p1.getX();
            this.xMax = p2.getX();

        } else {
            this.xMin = p2.getX();
            this.xMax = p1.getX();

        }

        if (p1.getY() < p2.getY()) {
            this.yMin = p1.getY();
            this.yMax = p2.getY();

        } else {
            this.yMin = p2.getY();
            this.yMax = p1.getY();

        }

        if (p1.getZ() < p2.getZ()) {
            this.zMin = p1.getZ();
            this.zMax = p2.getZ();

        } else {
            this.zMin = p2.getZ();
            this.zMax = p1.getZ();

        }

    }

    public boolean IntersectionBox(Ray r) {
        double tMin, tMax;

        double dirTemp = r.getDir().getX();
        double pTemp = r.getP0().getX();

        if (isZero(dirTemp)) {
            if (xMin > pTemp || xMax < pTemp)
                return false;


            tMin = Double.NEGATIVE_INFINITY;
            tMax = Double.MAX_VALUE;

        } else {
            if (dirTemp > 0) {
                tMin = (xMin - pTemp) / dirTemp;
                tMax = (xMax - pTemp) / dirTemp;
            } else {
                tMax = (xMin - pTemp) / dirTemp;
                tMin = (xMax - pTemp) / dirTemp;
            }
        }


        double tempMin, tempMax;

        dirTemp = r.getDir().getY();
        pTemp = r.getP0().getY();

        if (isZero(dirTemp)) {
            if (yMin > pTemp || yMax < pTemp)
                return false;


            tempMin = Double.NEGATIVE_INFINITY;
            tempMax = Double.MAX_VALUE;

        } else {
            if (dirTemp > 0) {
                tempMin = (yMin - pTemp) / dirTemp;
                tempMax = (yMax - pTemp) / dirTemp;
            } else {
                tempMax = (yMin - pTemp) / dirTemp;
                tempMin = (yMax - pTemp) / dirTemp;
            }
        }

        if ((tMin > tempMax) || (tempMin > tMax))
            return false;

        if (tempMin > tMin)
            tMin = tempMin;

        if (tempMax < tMax)
            tMax = tempMax;


        dirTemp = r.getDir().getZ();
        pTemp = r.getP0().getZ();

        if (isZero(dirTemp)) {
            if (zMin > pTemp || zMax < pTemp)
                return false;

          return true ;

        } else {
            if (dirTemp > 0) {
                tempMin = (zMin - pTemp) / dirTemp;
                tempMax = (zMax - pTemp) / dirTemp;
            } else {

                tempMax = (zMin - pTemp) / dirTemp;
                tempMin = (zMax - pTemp) / dirTemp;
            }
        }

        if ((tMin > tempMax) || (tempMin > tMax))
            return false;

        if (tempMin > tMin)
            tMin = tempMin;

        if (tempMax < tMax)
            tMax = tempMax;

        return true;
    }

}