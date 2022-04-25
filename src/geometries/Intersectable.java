package geometries;

import primitives.Point;
import primitives.Ray;
import java.util.List;

/**
 * interface for Geometry Shapes
 */

public abstract class Intersectable {

    /**
     * The  point on geometry
     */
    public static class GeoPoint {
        //The geometry that contain the follow point
        public  Geometry geometry;
        //The  point on geometry
        public  Point point;
    }

    /**
     *
     * @param ray
     * @return list of intersection points between ray and geometry.
     */
    public abstract List<Point> findIntersections(Ray ray);
}
