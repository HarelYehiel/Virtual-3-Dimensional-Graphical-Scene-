package renderer;

import primitives.Point;
import primitives.Ray;
import scene.Scene;
import java.util.List;

public class RayTracerBasic extends RayTracerBase {

    public RayTracerBasic(Scene scene){
        super(scene);
    }

    /**
     * Calculator of the closest point intersection.
     * @param ray from the pixel.
     * @return Color.
     */
    @Override
    public Color traceRay(Ray ray){
        List<Point> intersections = scene.geometries.findIntersections(ray);
        if (intersections == null) return scene.background;
        Point closestPoint = ray.findClosestPoint(intersections);
        return calcColor(closestPoint);
    }

    /**
     * calculate color
     *
     * @param point for calculate is color for her
     * @return return the intensity of the ambientLight of scene
     */
    private Color calcColor(Point point) {
        return scene.ambientLight.getIntensity();
    }
}
