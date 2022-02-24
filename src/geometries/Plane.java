package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry{

   private Point q0;
    private Vector normal;

    @Override
    public String toString() {
        return "q0=" + q0 +
                ", normal=" + normal;
    }

    public Point getQ0() {
        return q0;
    }

    public Vector getNormal() {
        return normal;
    }

    public Plane(Point q0,Point q1,Point q2) {
        this.q0 = q0;
        normal = null;
    }

    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = getNormal( new Point(normal.getVector()));
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }
}
