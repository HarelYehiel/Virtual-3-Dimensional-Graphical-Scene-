package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    /**
     * Test method for {@link Polygon#Polygon(Point...)}.
     */
    @Test
    public void testConstructor() {
        assertThrows(IllegalArgumentException.class,
                ()-> new Plane(new Point(1,2,3),new Point(1,2,3),new Point(3,6,9))
                ,"Constructed a plane with vertices equals.");

        assertThrows(IllegalArgumentException.class,
                ()-> new Plane(new Point(1,2,3),new Point(2,4,6),new Point(3,6,9))
                ,"Constructed a plane with vertices on the same line.");
    }

    @Test
    void getNormal() {

    }
}