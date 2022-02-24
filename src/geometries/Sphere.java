package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Sphere = ball 3D Sphere represent by point and radius vector args:
 * center ,radius
 */
public class Sphere implements Geometry {

    private Point center;
    private double radius;

    /**
     * Constructor that initialize the center and radius.
     * @param center
     * @param radius
     */
    public Sphere(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    /**
     * @return the details: center, radius.
     */
    @Override
    public String toString() {
        return "center=" + center +
                ", radius=" + radius;
    }

    /**
     * @return the center of Sphere.
     */
    public Point getCenter() {
        return center;
    }

    /**
     * @return the radius of Sphere.
     */
    public double getRadius() {
        return radius;
    }

    /**
     * @param p
     * @return the normal of Sphere.
     */
    @Override
    public Vector getNormal(Point p) {
        return null;
    }

}
