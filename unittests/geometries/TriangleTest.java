package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {


    @Test
    void getNormal() {
        Triangle t = new Triangle(new Point(2,7,1),new Point(1,12,3),new Point(3,6,9));
        assertEquals(t.getNormal(new Point(2, 7, 1)), new Vector(42,10,-4).normalize(),
                "The method getNormal return wrong normal.");
    }
}