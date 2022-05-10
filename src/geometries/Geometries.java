package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Geometries extends Intersectable{

    private List<Intersectable> geometries;

    /**
     * Initialization the list 'geometries'.
     */
    public Geometries(){
        geometries = new LinkedList<>();
    }

    /**
     * Initialization the list 'this.geometries' with
     * the value in array 'geometries'.
     * @param geometries: list of geometries.
     */
    public Geometries(Intersectable... geometries){
        this.geometries = new LinkedList<>();

        int geometriesLength = geometries.length;
        for (var i = 0; i < geometriesLength; ++i){
            this.geometries.add(geometries[i]);
        }
    }

    /**
     * Add to list 'this.geometries'
     * the value in array 'geometries'.
     * @param geometries: list of geometries.
     */
    public  void add(Intersectable... geometries){
        int geometriesLength = geometries.length;
        for (var i = 0; i < geometriesLength; ++i){
            this.geometries.add(geometries[i]);
        }
    }

    /**
     *
     * @param ray
     * @return the intersection GeoPoints with the geometries.
     * in list 'this.geometries'.
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        List<GeoPoint> pointIntersections = new LinkedList<>();
        int sizeOfGeometries = geometries.size();
        for (int i = 0; i < sizeOfGeometries; ++i){
            List<GeoPoint> help = geometries.get(i).findGeoIntersections(ray, maxDistance);
            if(help != null)
                pointIntersections.addAll(help);
        }

        if(pointIntersections.size() == 0)
            return null;

        return pointIntersections;
    }
}
