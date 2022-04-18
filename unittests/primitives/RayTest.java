package primitives;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    @Test
    void findClosestPoint() {
        Ray ray = new Ray(new Point(0,0,0), new Vector(1,1,1));


        // ============ Equivalence Partitions Tests ==============
        List<Point> listPoints = new LinkedList<Point>();
        listPoints.add(new Point(3,3,3));
        listPoints.add(new Point(2,2,2));
        listPoints.add(new Point(4,4,4));

        // TC01: Test when the closest Point is in the middle of list.
        assertEquals(ray.findClosestPoint(listPoints), new Point(2,2,2),
                "TC01: Error: point must be Point(2,2,2).");

        // =============== Boundary Values Tests ==================

        listPoints = null;
        // TC02: Test when the list is empty.
        assertNull(ray.findClosestPoint(listPoints),"TC02: Error: result must be null.");

        // TC03: Test when the closest Point is first in the middle of list.
        listPoints = new LinkedList<Point>();
        listPoints.add(new Point(2,2,2));
        listPoints.add(new Point(3,3,3));
        listPoints.add(new Point(4,4,4));
        assertEquals(ray.findClosestPoint(listPoints), new Point(2,2,2),
                "TC03: Error: point must be Point(2,2,2).");

        // TC04: Test when the closest Point is last in the middle of list.
        listPoints = new LinkedList<Point>();
        listPoints.add(new Point(3,3,3));
        listPoints.add(new Point(4,4,4));
        listPoints.add(new Point(2,2,2));
        assertEquals(ray.findClosestPoint(listPoints), new Point(2,2,2),
                "TC04: Error: point must be Point(2,2,2).");
    }
}