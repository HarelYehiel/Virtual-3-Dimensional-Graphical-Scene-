package geometries;

import primitives.Point;
import primitives.Ray;
import java.util.List;

/**
 * interface for Geometry Shapes
 */

public interface Intersectable {
    public List<Point> findIntersections(Ray ray);
}
