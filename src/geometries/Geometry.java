package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * interface for Geometry Shapes
 */
public interface Geometry {

    /**
     * @param p
     * @return the normal of p.
     */
    Vector getNormal(Point p);
}
