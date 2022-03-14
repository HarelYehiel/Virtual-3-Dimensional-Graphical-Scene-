package geometries;

import primitives.Point;
import primitives.Util;
import primitives.Vector;

/**
 *  Plane is infinite linear 3D surface The class represents Plane entity in our
 *  3D model
 */
public class Plane implements Geometry{

    private Point q0;
    private Vector normal;

    /**
     * @return the details: q0, normal.
     */
    @Override
    public String toString() {
        return "q0=" + q0 +
                ", normal=" + normal;
    }

    /**
     * @return q0.
     */
    public Point getQ0() {
        return q0;
    }

    /**
     * @return normal.
     */
    public Vector getNormal() {
        return normal;
    }

    /**
     * Constructor that builds Plane from 3 Points in the plane
     * @param q0 first point
     * @param q1 second point
     * @param q2 third point
     */
    public Plane(Point q0,Point q1,Point q2) {
        if(q0.equals(q1) || q0.equals(q2) || q1.equals(q2))
            throw new IllegalArgumentException("All vertices of a Plane need to be different");

        {
            Vector v0 = q0.subtract(q1);
            Vector v1 = q0.subtract(q2);
            Vector v2 = v1.crossProduct(v0);
            if(Util.isZero(v2.lengthSquared()))
                throw new IllegalArgumentException("All vertices of a Plane need not to be on the same line");
        }

        this.q0 = q0;
        normal = null;
    }

    /**
     * Constructor that initialize the q0 and normal.
     * normal = vector.
     * @param q0
     * @param vector
     */
    public Plane(Point q0, Vector vector) {
        this.q0 = q0;
        this.normal = vector.normalize();
    }

    /**
     *
     * @param p
     * @return the normal of p.
     */
    @Override
    public Vector getNormal(Point p) {
        return null;
    }
}
