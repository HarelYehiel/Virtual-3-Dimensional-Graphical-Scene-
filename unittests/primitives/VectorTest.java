package primitives;
import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static primitives.Util.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VectorTest {

    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);

    /**
     * Test method for {@link Vector#add(Vector)}.
     */
    @Test
    void add() {
        assertEquals(v1.add(new Vector(-1, -2, -3)), (new Point(0, 0, 0)),
                "ERROR: Vector + Vector does not work correctly");

    }

    /**
     * Test method for {@link Vector#subtract(Vector)}.
     */
    @Test
    void subtract() {
        Vector v1 = new Vector(1, 2, 3);
        assertEquals(new Vector(1, 1, 1), (new Vector(2, 3, 4).subtract(v1))
                ,"ERROR: Point - Point does not work correctly");
    }

    /**
     * Test method for {@link Vector#scale(double)}.
     */
    @Test
    void scale() {
        assertEquals(v1.scale(5), new Vector(5, 10, 15),
                "ERROR: Vector * double does not work correctly");
    }

    /**
     * Test method for {@link Vector#crossProduct(Vector)}.
     */
    @Test
    public void testCrossProduct() {
        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals( v1.length() * v2.length(), vr.length(), 0.00001,"crossProduct() wrong result length");

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue( isZero(vr.dotProduct(v1)),"crossProduct() result is not orthogonal to 1st operand");


        assertTrue(isZero(vr.dotProduct(v2)),"crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross- product of co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows( IllegalArgumentException.class, () -> v1.crossProduct(v3)
                ,"crossProduct() for parallel vectors does not throw an exception");

    }

    /**
     * Test method for {@link Vector#dotProduct(Vector)}.
     */
    @Test
    void dotProduct() {
        assertTrue(isZero(v1.dotProduct(v3)), "ERROR: dotProduct() for orthogonal vectors is not zero");
        assertTrue(isZero(v1.dotProduct(v2) + 28), "ERROR: dotProduct() wrong value");

    }

    /**
     * Test method for {@link Vector#lengthSquared()}.
     */
    @Test
    void lengthSquared() {
        assertTrue(isZero(v1.lengthSquared() - 14), "ERROR: lengthSquared() wrong value");
    }

    /**
     * Test method for {@link Vector#length()}.
     */
    @Test
    void length() {
        assertTrue(isZero(new Vector(0, 3, 4).length() - 5), "ERROR: length() wrong value");
    }

    /**
     * Test method for {@link Vector#normalize()}.
     */
    @Test
    void normalize() {
        Vector u = v1.normalize();
        assertTrue(isZero(u.length() - 1), "ERROR: the normalized vector is not a unit vector");

    }

}