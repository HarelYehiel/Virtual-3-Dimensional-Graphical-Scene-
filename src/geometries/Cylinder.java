package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Tube with limited height
 */
public class Cylinder extends Tube{

    private double height;

    /**
     * @param p
     * @return the normal of p.
     */
    @Override
    public Vector getNormal(Point p) {
        return null;
    }

    /**
     * @return the details: radius, axisRay, height.
     */
    @Override
    public String toString() {
        return super.toString()+
                ", height=" + height;
    }

    /**
     * @return the height.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Constructor initialize axisRay, radius and height.
     * @param axisRay
     * @param radius
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }
}
