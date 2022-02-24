package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Tube implements Geometry{

   protected Ray axisRay;
    protected  double radius;

    @Override
    public String toString() {
        return  "axisRay=" + axisRay +
                ", radius=" + radius;
    }

    public Ray getAxisRay() {
        return axisRay;
    }

    public double getRadius() {
        return radius;
    }

    public Tube(Ray axisRay, double radius) {
        this.axisRay = axisRay;
        this.radius = radius;
    }


    @Override
    public Vector getNormal(Point p) {
        return null;
    }
}
