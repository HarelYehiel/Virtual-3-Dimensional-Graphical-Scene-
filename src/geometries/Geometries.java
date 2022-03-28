package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable{

    private List<Intersectable> geometries;

    public Geometries(){
        geometries = new LinkedList<>();
    }

    public Geometries(Intersectable... geometries){
        this.geometries = new LinkedList<>();

        int geometriesLength = geometries.length;
        for (var i = 0; i < geometriesLength; ++i){
            this.geometries.add(geometries[i]);
        }
    }

    public  void add(Intersectable... geometries){
        int geometriesLength = geometries.length;
        for (var i = 0; i < geometriesLength; ++i){
            this.geometries.add(geometries[i]);
        }
    }

    public List<Point> findIntersections(Ray ray){

        List<Point> pointIntersections = new LinkedList<>();
        int sizeOfGeometries = geometries.size();
        for (int i = 0; i < sizeOfGeometries; ++i){
            List<Point> help = geometries.get(i).findIntersections(ray);
            if(help != null)
            pointIntersections.addAll(help);
        }

        if(pointIntersections.size() == 0)
            return null;

        return pointIntersections;
    }

}
