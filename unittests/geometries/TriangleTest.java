package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

    /**
     * Test method for {@link Triangle#getNormal(Point)}.
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Checks whether the returned normal is correct.
        Triangle t = new Triangle(new Point(2,7,1),new Point(1,12,3),new Point(3,6,9));
        assertEquals(t.getNormal(new Point(2, 7, 1)), new Vector(42,10,-4).normalize(),
                "The method getNormal return wrong normal.");
    }

    @Test
    void findIntersections(Ray ray){
        Triangle tria = new Triangle(new Point(0.0, 1.0, 0.0), new Point(1.0, 0.0, 0.0), new Point(0.0, 0.0, 1.0));
        List<Point> intersectionsPoints = new LinkedList<Point>();

        // ============ Equivalence Partitions Tests ==============

        // TC01: Test when the ray intersects inside the triangle(1 points)
        intersectionsPoints = tria.findIntersections(new Ray(new Point(0.0,0.0,0.0),
                new Vector(1.0,1.0,2.0)));
        assertEquals(intersectionsPoints.size(), 1,
                "The number of intersections points is wrong");
        assertEquals(intersectionsPoints.get(0), new Point(0.25,0.25,0.5),
                "The intersection point is wrong");

        // TC02: Test when the ray outside against vertex(0 points)
        assertNull(tria.findIntersections(new Ray(new Point(-1.0,-1.0,-1.0),
                new Vector(0.5,0.5,1.5))),
                "There should be no point of intersection because the ray outside against vertex");

        // TC03: Test when the ray outside against edge(0 points)
        assertNull(tria.findIntersections(new Ray(new Point(-1.0,-2.0,-2.0),
                new Vector(1.0,1.0,2.0))),
                "There should be no point of intersection because the ray outside against edge");


        // =============== Boundary Values Tests ==================

        // TC04: Test when the ray intersects the triangle in vertex(0 points)
        assertNull(tria.findIntersections(new Ray(new Point(0.0,-1.0,0.0),
                new Vector(0.0,1.0,1.0))),
                "There should be no point of intersection because the ray intersects the triangle in vertex");

        // TC05: Test when the ray intersects the triangle on edge's continuation(0 points)
        assertNull(tria.findIntersections(new Ray(new Point(3.0,0.0,0.0),
                new Vector(-3.0,0.0,1.5))),
                "There should be no point of intersection because the ray intersects the triangle on edge's continuation");

        // TC06: Test when the ray intersects the triangle on edge(0 points)
        assertNull(tria.findIntersections(new Ray(new Point(0.5,0.0,0.0),
                new Vector(0.0,0.0,0.5))),
                "There should be no point of intersection because the ray intersects the triangle on edge");


    }
}