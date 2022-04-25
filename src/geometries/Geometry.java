package geometries;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * interface for Geometry Shapes
 */
public abstract class Geometry extends Intersectable{

    protected Color emission = Color.BLACK;

    /**
     * @param p
     * @return the normal of p.
     */
    public abstract Vector getNormal(Point p);

    /**
     *
     * @return the color of geomtry(emmission)
     */
    public Color getEmission(){
        return emission;
    }

    /**
     *
     * @param clr(new color)
     * @return the geometry wiht updeted emission
     */
    public Geometry setEmission(Color clr){
        this.emission = clr;
        return this;
    }
}
