package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends Tube{
    @Override
    public Vector getNormal(Point p) {
        return null;
    }

    @Override
    public String toString() {
        return super.toString()+
                ", height=" + height;
    }

    public double getHeight() {
        return height;
    }

    private double height;

    public Cylinder(Ray axisRay, double radius) {
        super(axisRay, radius);
    }
}
