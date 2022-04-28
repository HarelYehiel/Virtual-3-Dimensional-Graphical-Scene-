package renderer;

import primitives.Point;
import primitives.Ray;
import scene.Scene;
import java.util.List;
import primitives.*;
import geometries.Intersectable.GeoPoint;

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
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        if (intersections == null) return scene.background;
        GeoPoint closestGeoPoint = ray.findClosestGeoPoint(intersections);
        return calcColor(closestGeoPoint);
    }

    /**
     * calculate color
     *
     * @param geoPoint for calculate is color for her
     * @return return the intensity of the ambientLight of scene
     */
    private Color calcColor(GeoPoint geoPoint) {
        return geoPoint.geometry.getEmission();
    }
}
