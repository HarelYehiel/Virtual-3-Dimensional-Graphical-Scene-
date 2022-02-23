package primitives;

public class Vector extends Point {
    public Vector(double x, double y, double z){
        super(x,y,z);
        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("Vector must be different from 0");
    }
    public  Vector(Double3 dbl) {
        super(dbl);
        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("Vector must be different from 0");
    }

    public Double3 getVector(){ return xyz;}

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public  Vector Add(Vector v){
        return new Vector(xyz.add(v.xyz));
    }

    public Vector scale(double num){
        return new Vector(xyz.scale(num));
    }

    public Vector crossProduct(Vector v){
        double x = xyz.d2 * v.xyz.d3 - xyz.d3 * v.xyz.d2;
        double y = xyz.d3 * v.xyz.d1 - xyz.d1 * v.xyz.d3;
        double z = xyz.d1 * v.xyz.d2 - xyz.d2 * v.xyz.d1;
        return new Vector(x,y,z);
    }

    public double dotProduct(Vector v){
        Double3 prod = xyz.product(v.xyz);
        return prod.d1 + prod.d2 + prod.d3;
    }

    public double lengthSquared(){
        return dotProduct(this);
    }

    public double length(){
        return Math.sqrt(lengthSquared());
    }

    public Vector normalize(){
        double len = length();
        return new Vector(xyz.d1/len,xyz.d2/len,xyz.d3/len);
    }
}
