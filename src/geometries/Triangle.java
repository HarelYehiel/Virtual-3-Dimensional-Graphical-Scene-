package geometries;

import primitives.Point;
import primitives.Vector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import primitives.Ray;


/**
 * Triangle is a polygon whit only 3 vertices
 */
public class Triangle extends Polygon{
    public Triangle(Point q0,Point q1,Point q2) {
        super(q0,q1,q2);
    }

    public List<Point> findIntsersections(Ray ray){return  null;}
}
