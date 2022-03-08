package primitives;
import static primitives.Util.*;

/**
 * A vector is an object with size and direction Vector from the beginning of
 * the axes to a certain point Builders : a) three coordinates, b) three
 * double-digit numbers, c) a Point3D .* Copy builder not exist !
 */
public class Vector extends Point {
    /**
     * Constructor that initialize this.xyz,
     * by three double
     * @param x
     * @param y
     * @param z
     */
    public Vector(double x, double y, double z){
        super(x,y,z);
        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("Vector must be different from 0");
    }

    /**
     * Constructor that initialize this.xyz,
     * by element kind Double3.
     * @param dbl
     */
    public  Vector(Double3 dbl) {
        super(dbl);
        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("Vector must be different from 0");
    }

    /**
     *
     * @return this.xyz.
     */
    public Double3 getVector(){ return xyz;}

    /**
     * Equals between two points.
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    /**
     * @return the details: xyz.
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     *  New Vector = add v to this.xyz.
     * @param v
     * @return new Vector
     */
    public  Vector Add(Vector v){
        return new Vector(xyz.add(v.xyz));
    }

    /**
     * New Vector = v (Vector) * num.
     * @param num
     * @return new vector
     */
    public Vector scale(double num){
        return new Vector(xyz.scale(num));
    }

    public Vector crossProduct(Vector v){
        double x = xyz.d2 * v.xyz.d3 - xyz.d3 * v.xyz.d2;
        double y = xyz.d3 * v.xyz.d1 - xyz.d1 * v.xyz.d3;
        double z = xyz.d1 * v.xyz.d2 - xyz.d2 * v.xyz.d1;

        Vector v1 = new Vector(x,y,z);
        if(isZero(v1.lengthSquared()))
            throw new IllegalArgumentException("ERROR: crossProduct for parallel vectors");

        return v1;
    }

    /**
     * A scalar product of two vectors
     * @param v
     * @return scalar (double)
     */
    public double dotProduct(Vector v){
        Double3 prod = xyz.product(v.xyz);
        return prod.d1 + prod.d2 + prod.d3;
    }

    /**
     * Calculate the length of the vector squared
     * @return scalar (double)
     */
    public double lengthSquared(){
        return dotProduct(this);
    }

    /**
     * Calculate the length of the vector
     * @return scalar (double)
     */
    public double length(){
        return Math.sqrt(lengthSquared());
    }

    /**
     * Normalizes the vector (makes it size 1)
     * @return new Vector normalized
     */
    public Vector normalize(){
        double len = length();
        return new Vector(xyz.d1/len,xyz.d2/len,xyz.d3/len);
    }
}
