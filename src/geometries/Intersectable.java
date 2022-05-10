package geometries;

import primitives.Point;
import primitives.Ray;
import java.util.List;

/**
 * interface for Geometry Shapes
 */

public abstract class Intersectable {

    /**
     * The  point on geometry
     */
    public static class GeoPoint {
        //The geometry that contain the follow point
        public  Geometry geometry;
        //The  point on geometry
        public  Point point;

        /**
         * Constructor with initialize of the geometry and point.
         * @param geo
         * @param poi
         */
        public GeoPoint(Geometry geo, Point poi) {
            geometry = geo;
            point = poi;
        }

        /**
         * equals between two geoPoints.
         * @param o
         * @return true if the points and geometries are equals.
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;

            if(geoPoint.geometry.getClass() != geometry.getClass()) return false;
            //if           מה עושים עם זה.
            //for ()
            return point.equals(geoPoint.point);
        }

        /**
         *
         * @return
         */
        @Override
        public String toString() {
            return "geometry=" + geometry +
                    ", point=" + point.toString() +
                    '}';
        }
    }

    /**
     *
     * @param ray
     * @return list of intersection points between ray and geometry.
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream().map(gp -> gp.point).toList();
    }

    public final List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
    }
    public final List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        return findGeoIntersectionsHelper(ray, maxDistance);
    }
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance);

}
