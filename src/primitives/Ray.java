package primitives;
import java.util.List;
import geometries.Intersectable.GeoPoint;

/**
 * (ray) - A fundamental object in geometry - the group of points on a straight
 * line that are on one relative side To a given point on the line called the
 * beginning of the ray. Defined by point and direction (unit vector) ...
 */
public class Ray {

    final Point p0;
    final Vector dir;

    /**
     * Constructor that initialize the p0 and dir.
     *
     * @param p
     * @param v
     */
    public Ray(Point p, Vector v) {
        p0 = p;
        dir = v.normalize();
    }

    /**
     * Get the value in p0.
     *
     * @return Point
     */
    public Point getP0() {
        return p0;
    }

    /**
     * Get the value in dir.
     *
     * @return Vector
     */
    public Vector getDir() {
        return dir;
    }

    /**
     * Equals between two Rays.
     *
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

    /**
     * @return the details: p0, dir.
     */
    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }

    public Point getPoint(double t) {
        return this.p0.add(this.dir.scale(t));
    }

    /**
     * Find the closest point from to thr center of camera.
     * @param //listPoints
     * @return Point
     */
//    public Point findClosestPoint(List<Point> listPoints) {
//        if (listPoints == null) return null;
//
//        double minDistance = p0.distance(listPoints.get(0));
//        Point pointRes = listPoints.get(0);
//
//        for (int i = 1; i < listPoints.size(); ++i) {
//            double distance = p0.distance(listPoints.get(i));
//
//            if (distance < minDistance) {
//                minDistance = distance;
//                pointRes = listPoints.get(i);
//            }
//        }
//
//        return pointRes;
//    }

    /**
     * Find the closest point from to thr center of camera.
     * @param points
     * @return Point
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }


    /**
     * Find the closest geoPoint from to thr center of camera.
     * @param listGeoPoints
     * @return GeoPoint
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> listGeoPoints){
        if (listGeoPoints == null) return null;

        double minDistance = p0.distance(listGeoPoints.get(0).point);
        GeoPoint pointRes = listGeoPoints.get(0);

        int len = listGeoPoints.size();
        for (int i = 1; i < len; ++i) {
            double distance = p0.distance(listGeoPoints.get(i).point);

            if (distance < minDistance) {
                minDistance = distance;
                pointRes = listGeoPoints.get(i);
            }
        }

        return pointRes;
    }
}