package geometries;

import java.util.List;

import primitives.*;

import static primitives.Util.*;

/**
 * Polygon class represents two-dimensional polygon in 3D Cartesian coordinate
 * system
 *
 * @author Dan
 */
public class Polygon extends Geometry {
    /**
     * List of polygon's vertices
     */
    protected List<Point> vertices;
    /**
     * Associated plane in which the polygon lays
     */
    protected Plane plane;
    private int size;

    /**
     * Polygon constructor based on vertices list. The list must be ordered by edge
     * path. The polygon must be convex.
     *
     * @param vertices list of vertices according to their order by edge path
     * @throws IllegalArgumentException in any case of illegal combination of
     *                                  vertices:
     *                                  <ul>
     *                                  <li>Less than 3 vertices</li>
     *                                  <li>Consequent vertices are in the same
     *                                  point
     *                                  <li>The vertices are not in the same
     *                                  plane</li>
     *                                  <li>The order of vertices is not according
     *                                  to edge path</li>
     *                                  <li>Three consequent vertices lay in the
     *                                  same line (180&#176; angle between two
     *                                  consequent edges)
     *                                  <li>The polygon is concave (not convex)</li>
     *                                  </ul>
     */
    public Polygon(Point... vertices) {
        if (vertices.length < 3)
            throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
        this.vertices = List.of(vertices);
        // Generate the plane according to the first three vertices and associate the
        // polygon with this plane.
        // The plane holds the invariant normal (orthogonal unit) vector to the polygon
        plane = new Plane(vertices[0], vertices[1], vertices[2]);
        if (vertices.length == 3)
            return; // no need for more tests for a Triangle

        Vector n = plane.getNormal();

        // Subtracting any subsequent points will throw an IllegalArgumentException
        // because of Zero Vector if they are in the same point
        Vector edge1 = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
        Vector edge2 = vertices[0].subtract(vertices[vertices.length - 1]);

        // Cross Product of any subsequent edges will throw an IllegalArgumentException
        // because of Zero Vector if they connect three vertices that lay in the same
        // line.
        // Generate the direction of the polygon according to the angle between last and
        // first edge being less than 180 deg. It is hold by the sign of its dot product
        // with
        // the normal. If all the rest consequent edges will generate the same sign -
        // the
        // polygon is convex ("kamur" in Hebrew).
        boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
        for (var i = 1; i < vertices.length; ++i) {
            // Test that the point is in the same plane as calculated originally
            if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
                throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
            // Test the consequent edges have
            edge1 = edge2;
            edge2 = vertices[i].subtract(vertices[i - 1]);
            if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
                throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
        }
        size = vertices.length;
    }

    /**
     * Return the normal of p (Point).
     *
     * @param point
     * @return the normal.
     */
    @Override
    public Vector getNormal(Point point) {
        return plane.getNormal();
    }

    /**
     * @param ray
     * @return list of intersection points between ray and polygon.
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        List<Point> result = plane.findIntersections(ray);
        int verticesSize = vertices.size();
        if (result == null) {
            return null;
        }

        /**
         * arr_Subtract= arr of the vector to the vertices
         */
        Vector[] arr_Subtract = new Vector[verticesSize];
        /**
         * arr_crossProduct_normalize=normal form Sides
         */
        Vector[] arr_crossProduct_normalize = new Vector[verticesSize];
        // Create vector to the vertices
        for (int i = 0; i < verticesSize; i++) {
            arr_Subtract[i] = vertices.get(i).subtract(ray.getP0());
        }
        // Create normal form Sides
        for (int i = 0; i < (verticesSize - 1); i++) {
            arr_crossProduct_normalize[i] = arr_Subtract[i].crossProduct(arr_Subtract[i + 1]).normalize();
        }
        // for the lest crossProduct with the first
        arr_crossProduct_normalize[verticesSize - 1] = arr_Subtract[verticesSize - 1].crossProduct(arr_Subtract[0])
                .normalize();
        // arr_NdotProductDir= normal * direction of ray
        double[] arr_NdotProductDir = new double[verticesSize];
        for (int i = 0; i < verticesSize; i++) {
            arr_NdotProductDir[i] = arr_crossProduct_normalize[i].dotProduct(ray.getDir());
        }

        for (int i = 0; i < (verticesSize - 1); i++) {
            // if one or more are 0.0 – no intersection
            if (alignZero(arr_NdotProductDir[i]) == 0)
                return null;
            // The point is inside if all 𝒗 ∙ 𝑵𝒊 have the same sign (+/-)
            if (!(checkSign(arr_NdotProductDir[i], arr_NdotProductDir[i + 1])))
                return null;
        }

        //distance between point to intersection.
        double distance = result.get(0).distance(ray.getP0());

        return Util.alignZero( distance - maxDistance) <= 0 ? List.of(new GeoPoint(this, result.get(0))) : null;
    }
}
