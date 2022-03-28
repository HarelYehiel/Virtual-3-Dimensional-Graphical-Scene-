package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class  PlaneTest {

    /**
     * Test method for {@link Polygon#Polygon(Point...)}.
     */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Checks if the vertices are not equal
        assertThrows(IllegalArgumentException.class,
                ()-> new Plane(new Point(1,2,3),new Point(1,2,3),new Point(3,6,9))
                ,"Constructed a plane with vertices equals.");

        // TC01: Checks if the vertices are on the same line.
        assertThrows(IllegalArgumentException.class,
                ()-> new Plane(new Point(1,2,3),new Point(2,4,6),new Point(3,6,9))
                ,"Constructed a plane with vertices on the same line.");
    }

    /**
     * Test method for {@link Plane#getNormal()}.
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Checks whether the returned normal is correct.
        double denominator = Math.sqrt(1880);
        Plane p =  new Plane(new Point(2,7,1),new Point(1,12,3),new Point(3,6,9));
        assertEquals(p.getNormal(), new Vector(42/denominator,10/denominator,-4/denominator),
                "The method getNormal return wrong normal.");
    }

    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections(){
        Plane plane = new Plane(new Point(1,0,0),new Point(0,0,0),new Point(0,1,0));
        // ============ Equivalence Partitions Tests ==============

        // **** EP: The Ray must be neither orthogonal nor parallel to the plane
        // TC01: Ray intersects the plane
        Point p = new Point(-0.6666666666666667,-0.33333333333333337,0);
        List<Point> result = plane.findIntersections(new Ray(new Point(-1, -1, -1),
                new Vector(1, 2, 3)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(List.of(p), result, "Ray intersect the plane");

        // TC02: Ray does not intersect the plane
        assertNull(plane.findIntersections(new Ray(new Point(-1, -1, -1), new Vector(-2, -3, -4))),
                "Ray does not intersect the plane");

        // =============== Boundary Values Tests ==================

        // **** EP: Ray is parallel to the plane
        // TC03: The ray  included in the plane
        assertNull(plane.findIntersections(new Ray(new Point(1, 1, 0), new Vector(3, 3, 0))),
                "The ray  included in the plane");

        // TC04: The ray not included in the plane
        assertNull(plane.findIntersections(new Ray(new Point(1, 1, 1), new Vector(3, 3, 1))),
                "Ray is parallel to the plane");

        //**** EP: Ray is orthogonal to the plane
        // TC05: P0 is in the plane
        assertNull(plane.findIntersections(new Ray(new Point(1, 1, 0), new Vector(0, 0, -3))),
                "Ray is orthogonal to the plane and P0 is in the plane");

        // TC06: P0 is after the plane
        assertNull(plane.findIntersections(new Ray(new Point(1, 1, 1), new Vector(0, 0, 3))),
                "Ray is orthogonal to the plane and P0 is after the plane");

        // TC07: P0 is before the plane
        p = new Point(1,1,0);
        result = plane.findIntersections(new Ray(new Point(1, 1, -1),
                new Vector(0, 0, 3)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(List.of(p), result, "Ray intersect the plane");

        //**** EP: Ray is neither orthogonal nor parallel to the plane
        // TC08: Ray begins at the plane
        assertNull(plane.findIntersections(new Ray(new Point(1, 1, 0), new Vector(2, 3, 4))),
                "Ray begins at the plane");

        // TC09: Ray begins at the plane
        assertNull(plane.findIntersections(new Ray(new Point(0, 0, 0), new Vector(2, 3, 4))),
                "Ray begins at the plane");
    }
}