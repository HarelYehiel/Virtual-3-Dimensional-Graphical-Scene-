package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Tube with unlimited height tube represent by direction and radius
 */
public class Tube implements Geometry{

    protected Ray axisRay;
    protected  double radius;

    /**
     * @return the details: axisRay, radius.
     */
    @Override
    public String toString() {
        return  "axisRay=" + axisRay +
                ", radius=" + radius;
    }

    /**
     * Get the value in axisRay.
     * @return Ray
     */
    public Ray getAxisRay() {
        return axisRay;
    }

    /***
     * Get the value in radius.
     * @return double
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Constructor initialize axisRay and radius.
     * @param axisRay
     * @param radius
     */
    public Tube(Ray axisRay, double radius) {
        this.axisRay = axisRay;
        this.radius = radius;
    }

    /**
     * Return the normal of p (Point).
     * @param p
     * @return
     */
    @Override
    public Vector getNormal(Point p) {
        return null;
    }
}
