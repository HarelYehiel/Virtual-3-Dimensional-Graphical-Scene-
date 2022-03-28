package geometries;

import primitives.Point;
import primitives.Ray;
import java.util.List;

/**
 * interface for Geometry Shapes
 */

public interface Intersectable {
    /**
     *
     * @param ray
     * @return list of intersection points between ray and geometry.
     */
    public List<Point> findIntersections(Ray ray);
}
