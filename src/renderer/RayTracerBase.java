package renderer;

import primitives.*;
import scene.Scene;

/**
 * RayTracerBase class is the base model for ray tracer
 */
public abstract class RayTracerBase {

    /**
     * scene with geometries.
     */
    protected Scene scene;

    /**
     * Constructor with Scene.
     * @param s
     */
    public RayTracerBase(Scene s){
        scene = s;
    }

    /**
     * trace a ray and return the color of the intersection point
     *
     * @param ray to trace after in the scene
     * @return color of the closest point of the ray;<br>or
     *         if there is no intersections return the background color
     */
    public abstract Color traceRay(Ray ray);
}
