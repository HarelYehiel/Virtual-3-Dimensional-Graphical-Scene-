package primitives;

import java.util.Objects;

public class Ray {

    Point p0;
    Vector dir;

    public Ray(Point p,Vector v){
        p0 = p;
        dir = v.normalize();
    }
    public Point getP0(){ return p0; }
    public Vector getDir(){ return dir;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return Objects.equals(p0, ray.p0) && Objects.equals(dir, ray.dir);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }
}
