package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest {

    /**
     * Test method for {@link geometries.Geometries#findIntersections(primitives.Ray)}.
     */
    @Test
    void findIntersections() {
        Geometries geometries = new Geometries();
        Triangle triangle = new Triangle(new Point(0.0, 1.0, 0.0)
                , new Point(1.0, 0.0, 0.0), new Point(0.0, 0.0, 1.0));
        Sphere sphere = new Sphere( new Point (1, 0, 0),1d);
        Plane plane = new Plane(new Point(1,0,0),new Point(0,0,0),
                new Point(0,1,0));
        Polygon polygon = new Polygon(new Point(0, 0, 0), new Point(0, 3, 0),
                new Point(2, 2, 0),  new Point(2, -1, 0));

        // =============== Boundary Values Tests ==================

        // TC01: Ray not intersects the zero geometries.
        assertNull(geometries.findIntersections(new Ray(new Point(1,2,3),
                new Vector(6,6,6))), "TC01:Wrong number of points");

        // TC02: Ray not intersects the any geometries (have 4 geometries in scene).
        geometries.add(triangle,sphere,plane,polygon);
        assertNull(geometries.findIntersections(new Ray(new Point(-1,-1,-1),
                new Vector(-2,-4,-8))), "TC02: Wrong number of points");

        // TC03: Ray intersects all the geometries.
        assertEquals(geometries.findIntersections(new Ray(new Point(-1,0,0.5d),
                new Vector(1.69, 0.54, -0.48))).size(), 5, "TC03: Wrong number of points");

        // TC04: Ray intersects the one geometry (have 4 geometries in scene).
        geometries.add(new Sphere( new Point (-3, 0, 0),1d));
        assertEquals(geometries.findIntersections(new Ray(new Point(-1,0,0),
                new Vector(-4,-1,0))).size(), 2, "TC04: Wrong number of points");

        // ============ Equivalence Partitions Tests ==============

        // TC05: Ray intersects part of geometries.
        assertEquals(geometries.findIntersections(new Ray(new Point(-1,0,0.5),
                new Vector(1.69, 0.54, -0.48))).size(), 5, "TC05: Wrong number of points");
    }





}