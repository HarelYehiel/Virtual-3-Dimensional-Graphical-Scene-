package geometries;

import primitives.*;

/**
 * interface for Geometry Shapes
 */
public abstract class Geometry extends Intersectable{
    private Material material = new Material();
    protected Color emission = Color.BLACK;

    /**
     * Get material.
     * @return Material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Set material.
     * @param material
     * @return Material.
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

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
