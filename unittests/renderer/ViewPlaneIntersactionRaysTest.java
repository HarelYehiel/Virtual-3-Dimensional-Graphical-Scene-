package renderer;

import geometries.Intersectable;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ViewPlaneIntersactionRaysTest {
    int intersectionCounter(Intersectable geometry, Camera camera) {
        int count = 0;

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                List<Point> l = geometry.findIntersections(camera.constructRay(3, 3, j, i));

                if (l != null)
                    count += l.size();
            }
        }

        return count;
    }

    void triangleTest() {

    }

    @Test
    void constructRay() {
        Camera camera = new Camera(new Point(0, 0, 0),
                new Vector(0, 0, -1), new Vector(0, 1, 0)).
                setVPSize(3, 3).setVPDistance(1);
        // ============ Triangle Tests ==============

        //Test 01: Intersection with one point in triangle.
        Triangle triangle = new Triangle(new Point(0, 1, -2),
                new Point(1, -1, -2), new Point(-1, -1, -2));
        assertEquals(intersectionCounter(triangle, camera), 1,
                "TC01: number of intersections must be one.");

        //Test 02: Intersection with two point in triangle.
        triangle = new Triangle(new Point(0, 20, -2),
                new Point(1, -1, -2), new Point(-1, -1, -2));
        assertEquals(intersectionCounter(triangle, camera), 2, "TC02: number of intersections must be two.");

        // ============ Plane Tests ==============

        //Test 03: Intersection with 9 point in parallel plane .
        Plane plane = new Plane(new Point(0, 0, -9), new Vector(0, 0, -1));
        assertEquals(intersectionCounter(plane, camera), 9, "TC03: number of intersections must be 9.");

        //Test 04: Intersection with 9 point in not parallel plane.
        plane = new Plane(new Point(0, 0, -9), new Vector(0, -0.5, 1));
        assertEquals(intersectionCounter(plane, camera), 9, "TC04: number of intersections must be 9.");

        //Test 05: Intersection with 6 point in not parallel plane.
        plane = new Plane(new Point(0, 0, -9), new Vector(0, -1, 1));
        assertEquals(intersectionCounter(plane, camera), 6, "TC05: number of intersections must be 6.");

        // ============ Sphere Tests ==============

        //Test 06: Intersection with 2 point in sphere.
        Sphere sphere = new Sphere(new Point(0, 0, -3), 1);
        assertEquals(intersectionCounter(sphere, camera), 2, "TC06: number of intersections must be 2.");

        camera = new Camera(new Point(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, 1, 0)).
                setVPSize(3, 3).setVPDistance(1);

        //Test 07: Intersection with 18 point in sphere.
        sphere = new Sphere(new Point(0, 0, -2.5), 2.5);
        assertEquals(intersectionCounter(sphere, camera), 18, "TC07: number of intersections must be 18.");

        //Test 08: Intersection with 10 point in sphere.
        sphere = new Sphere(new Point(0, 0, -2), 2);
        assertEquals(intersectionCounter(sphere, camera), 10, "TC08: number of intersections must be 10.");

        //Test 09: Intersection with 9 point in sphere.
        sphere = new Sphere(new Point(0, 0, -2), 4);
        assertEquals(intersectionCounter(sphere, camera), 9, "TC09: number of intersections must be 9.");

        //Test 10: Intersection with 0 point in sphere.
        sphere = new Sphere(new Point(0, 0, 1), 0.5);
        assertEquals(intersectionCounter(sphere, camera), 0, "TC10: number of intersections must be 0.");
    }
}