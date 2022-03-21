package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class  PlaneTest {

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

    /**
     * Test method for {@link Plane#getNormal()}.
     */
    @Test
    void getNormal() {
        double denominator = Math.sqrt(1880);
        Plane p =  new Plane(new Point(2,7,1),new Point(1,12,3),new Point(3,6,9));
        assertEquals(p.getNormal(), new Vector(42/denominator,10/denominator,-4/denominator),
                "The method getNormal return wrong normal.");
    }
}