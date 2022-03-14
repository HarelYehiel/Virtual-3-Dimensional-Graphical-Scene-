package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SphereTest {

    @Test
    void getCenter() {
    }

    @Test
    void getRadius() {
    }

    @Test
    void getNormal() {
        Sphere s = new Sphere(new Point(0,0,0),3);
         Point p = new Point(3,0,0);
        Vector normal   = p.subtract(s.getCenter()).normalize();
        assertEquals(normal, s.getNormal(new Point(3,0,0)),
                "ERROR: Method getNormal of Sphere returns wrong normal.");
    }
}