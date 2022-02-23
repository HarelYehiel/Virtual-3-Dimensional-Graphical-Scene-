package primitives;

import java.util.Objects;

public class Point {
    final Double3 xyz;

    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Objects.equals(xyz, point.xyz);
    }

    @Override
    public String toString() {
        return "xyz=" + xyz;
    }

    public Point add(Vector v) {
        Double3 d = xyz.add(v.xyz);
        return new Point(d.d1, d.d2, d.d3);
    }

    /**
     * @param p
     * @return
     */
    public Vector subtact(Point p) {
        Double3 d = xyz.subtract(p.xyz);
        return new Vector(d.d1, d.d2, d.d3);
    }

    public double distanceSquered(Point p) {
        return (xyz.d1 - p.xyz.d1) * (xyz.d1 - p.xyz.d1) +
                (xyz.d2 - p.xyz.d2) * (xyz.d2 - p.xyz.d2) +
                (xyz.d3 - p.xyz.d3) * (xyz.d3 - p.xyz.d3);
    }

    public double distance(Point p) {
        return Math.sqrt(distanceSquered(p));
    }


}
