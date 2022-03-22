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
        // ============ Equivalence Partitions Tests ==============

        // TC01: Tests add between a Vector and Vector.
        assertEquals(v1.add(new Vector(-1, -2, -3)), (new Point(0, 0, 0)),
                "ERROR: Vector + Vector does not work correctly");

    }

    /**
     * Test method for {@link Vector#subtract(Vector)}.
     */
    @Test
    void subtract() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Tests subtract between a Vector and Vector
        Vector v1 = new Vector(1, 2, 3);
        assertEquals(new Vector(1, 1, 1), (new Vector(2, 3, 4).subtract(v1))
                ,"ERROR: Vector - Vector does not work correctly");
    }

    /**
     * Test method for {@link Vector#scale(double)}.
     */
    @Test
    void scale() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Tests scale between a Vector and double
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
        // ============ Equivalence Partitions Tests ==============

        // TC01: Product of two vertical vectors should give 0.
        assertTrue(isZero(v1.dotProduct(v3)),
                "ERROR: dotProduct() for orthogonal vectors is not zero");

        // TC02: Checks that multiplication gives a correct scalar.
        assertTrue(isZero(v1.dotProduct(v2) + 28),
                "ERROR: dotProduct() wrong value");

    }

    /**
     * Test method for {@link Vector#lengthSquared()}.
     */
    @Test
    void lengthSquared() {

        // ============ Equivalence Partitions Tests ==============

        // TC01: Tests lengthSquared of a Vector.
        assertTrue(isZero(v1.lengthSquared() - 14), "ERROR: lengthSquared() wrong value");
    }

    /**
     * Test method for {@link Vector#length()}.
     */
    @Test
    void length() {

        // ============ Equivalence Partitions Tests ==============

        // TC01: Tests length of a Vector.
        assertTrue(isZero(new Vector(0, 3, 4).length() - 5), "ERROR: length() wrong value");
    }

    /**
     * Test method for {@link Vector#normalize()}.
     */
    @Test
    void normalize() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Tests normalize    Vector.
        Vector u = v1.normalize();
        double denomanator = Math.sqrt(14);
        assertTrue(isZero(u.length() - 1), "ERROR: the normalized vector is not a unit vector");
        assertEquals(u,new Vector(1/denomanator,2/denomanator,3/denomanator),"ERROR: the method normalize giv wrong vector");
    }

}