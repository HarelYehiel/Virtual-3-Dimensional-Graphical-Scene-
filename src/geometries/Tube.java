package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Tube with unlimited height tube represent by direction and radius
 */
public class Tube extends Geometry{

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
     * @return the normal.
     */
    @Override
    public Vector getNormal(Point p) {
        // t is the distance from p0 to o.
        double t = axisRay.getDir().dotProduct(p.subtract(axisRay.getP0()));

        // o is thr point on the ray and under p (point).
        Point o = axisRay.getP0().add(axisRay.getDir().scale(t));
        return  p.subtract(o).normalize();
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) { return null;  }
}
