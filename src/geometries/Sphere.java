package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Sphere = ball 3D Sphere represent by point and radius vector args:
 * center ,radius
 */
public class Sphere extends Geometry {

    /**
     * the center of Sphere
     */
    private Point center;

    /**
     * the radius of Sphere
     */
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
        return p.subtract(center).normalize();
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
  /*
        Based on this:
        Ray points: 𝑃 = 𝑃0 + 𝑡∙𝑣, 𝑡>0
        Sphere points: |𝑃 - 𝑂|^2 − 𝑟^2 = 0
        𝑢 = 𝑂 − 𝑃0
        𝑡𝑚 = 𝑣 ∙ 𝑢
        𝑑 = (𝑢^2−𝑡𝑚^2)^0.5 if (d ≥r) there are no intersections
        𝑡ℎ = (𝑟^2−𝑑^2)^0.5
        𝑡1,2 = 𝑡𝑚 +- 𝑡ℎ, 𝑃𝑖=𝑃0+𝑡𝑖∙𝑣, take only t > 0
         */
        List<GeoPoint> result = new LinkedList<>();
        // check if ray begins in center of sphere if so then t = radius
        if (isZero(this.center.distanceSquared(ray.getP0()))) {
            result.add(new GeoPoint(this,ray.getPoint(this.radius)));
            return result;
        }
        Vector u = this.center.subtract(ray.getP0());
        double tm = alignZero(ray.getDir().dotProduct(u));
        double d = alignZero(Math.sqrt(u.lengthSquared() - tm * tm));
        // check if distance > radius if so then there isn't intersections
        if (d >= this.radius)
            return null;
        double th = alignZero(Math.sqrt((this.radius * this.radius) - (d * d)));
        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);

        // check it t > 0 if so then there is intersection and if not there isn't
        if (t1 > 0) {
            double distance1 = ray.getPoint(t1).distance(ray.getP0());
            if (maxDistance == Double.POSITIVE_INFINITY || Util.alignZero(distance1 - maxDistance) <= 0)
                result.add(new GeoPoint(this, ray.getPoint(t1)));
        }
        if (t2 > 0) {
            double distance2 = ray.getPoint(t2).distance(ray.getP0());
            if (maxDistance == Double.POSITIVE_INFINITY || Util.alignZero(distance2 - maxDistance) <= 0)
                result.add(new GeoPoint(this, ray.getPoint(t2)));
        }
        //check if there are intersections
        if (!result.isEmpty())
            return result;
        return null;
    }
}
