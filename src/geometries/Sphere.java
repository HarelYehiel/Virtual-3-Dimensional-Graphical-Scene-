package geometries;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;
import static primitives.Util.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Sphere = ball 3D Sphere represent by point and radius vector args:
 * center ,radius
 */
public class Sphere extends Geometry {

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
        return p.subtract(center).normalize();
    }

    /**
     *
     * @param ray
     * @return list os intersection points between ray and sphere
     */
    @Override
    public List<Point> findIntersections(Ray ray)  {
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
        List<Point> result = new LinkedList<Point>();
        // check if ray begins in center of sphere if so then t = radius
        if (isZero(this.center.distanceSquared(ray.getP0()))) {
            result.add(ray.getPoint(this.radius));
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
        if (t1 > 0)
            result.add(ray.getPoint(t1));
        if (t2 > 0)
            result.add(ray.getPoint(t2));
        //check if there is intersections
        if (!result.isEmpty())
            return result;
        return null;
    };

}
