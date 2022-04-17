package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

public abstract class RayTracerBase {
    protected Scene scene;

    /**
     * Constructor with Scene.
     * @param s
     */
    public RayTracerBase(Scene s){
        scene = s;
    }

    public abstract Color traceRay(Ray ray);

}
