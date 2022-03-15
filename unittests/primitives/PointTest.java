package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    /**
     * Test method for {@link Point#add(Vector)}.
     */
    @Test
    void add() {
        Point p1 = new Point(1, 2, 3);
        assertEquals(p1.add(new Vector(-1, -2, -3)), (new Point(0, 0, 0)),
                "ERROR: Point + Vector does not work correctly");
    }

    /**
     * Test method for {@link Point#subtract(Point)}.
     */
    @Test
    void subtract() {
        Point p1 = new Point(1, 2, 3);
        assertEquals(new Vector(1, 1, 1), (new Point(2, 3, 4).subtract(p1))
            ,"ERROR: Point - Point does not work correctly");
    }

    /**
     * Test method for {@link Point#distanceSquared(Point)}.
     */
    @Test
    void distanceSquared() {
        Point p1 = new Point(5, 7, 2);
        assertEquals(29, (new Point(2, 3, 4).distanceSquared(p1))
                ,"ERROR: distanceSquared Point to Point does not work correctly");
    }

    /**
     * Test method for {@link Point#distance(Point)}.
     */
    @Test
    void distance() {
        Point p1 = new Point(5, 7, 2);
        assertEquals(5.385164807134504, (new Point(2, 3, 4).distance(p1))
                ,0.0000000000000001  ,"ERROR: distance Point to Point does not work correctly");
    }
}