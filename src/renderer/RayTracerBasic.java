package renderer;

import primitives.Point;
import primitives.Ray;
import lighting.*;
import scene.Scene;

import java.util.List;

import primitives.*;
import geometries.Intersectable.GeoPoint;

import javax.print.attribute.ResolutionSyntax;

public class RayTracerBasic extends RayTracerBase {

    private static final double DELTA = 0.05;

    /**
     * @param gp
     * @param l
     * @param n
     * @return
     */
    private boolean unshaded(GeoPoint gp, Vector l, Vector n, LightSource lightSource ) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector epsVector = n.scale(DELTA);
        Point point = gp.point.add(epsVector);
        Ray lightRay = new Ray(point, lightDirection);

        double maxDistance = lightSource.getDistance(gp.point);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay, maxDistance);

        return intersections == null  ||  intersections.isEmpty();
    }

    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * Calculator of the closest point intersection.
     *
     * @param ray from the pixel.
     * @return Color.
     */
    @Override
    public Color traceRay(Ray ray) {
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        if (intersections == null) return scene.background;
        GeoPoint closestGeoPoint = ray.findClosestGeoPoint(intersections);
        return calcColor(closestGeoPoint, ray);
    }

    /**
     * compute the specular effect
     *
     * @param ks             specular coefficient
     * @param l              direction of the light
     * @param n              normal to the body
     * @param nl             result of n DotProduct l
     * @param v              direction of the camera
     * @param nShininess     object shininess
     * @param lightIntensity intensity of the light source
     * @return color after applying specular effect
     */
    private Color calcSpecular(Double3 ks, Vector l, Vector n, double nl, Vector v, int nShininess,
                               Color lightIntensity) {
        // r : reflectance vector
        Vector r = l.subtract(n.scale(nl * 2));
        double rDotV = -Util.alignZero(r.dotProduct(v));
        if (rDotV <= 0)
            return Color.BLACK;
        Color spcular = lightIntensity.scale(ks.scale(Math.pow(rDotV, nShininess)));
        return spcular;
    }

    /**
     * compute the diffusive effect - thanks to which objects have colors
     *
     * @param kd             diffusive coefficient
     * @param nl             result of n DotProduct l - where n is the normal to the
     *                       body and l the light direction
     * @param lightIntensity intensity of the light source
     * @return color after applying diffusive effect
     */
    private Color calcDiffusive(Double3 kd, double nl, Color lightIntensity) {
        if (nl < 0)
            nl = -nl;
        return lightIntensity.scale(kd.scale(nl));
    }

    /**
     * calculate color
     *
     * @param intersection for calculate is color for her
     * @return return the intensity of the ambientLight of scene
     */
    private Color calcColor(GeoPoint intersection, Ray ray) {
        Color color = scene.ambientLight.getIntensity().add(intersection.geometry.getEmission());
        color = color.add(calcLocalEffects(intersection, ray));
        return color;
    }

    /**
     * calculate color
     *
     * @param intersection for calculate is color for her
     * @return return the intensity of the ambientLight of scene
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
        Vector v = ray.getDir();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = Util.alignZero(n.dotProduct(v));
        if (nv == 0)
            return Color.BLACK;
        //    Color iP = scene.ambientLight.getIntensity().add(intersection.geometry.getEmission());
        Material material = intersection.geometry.getMaterial();
        int nShininess = material.nShininess;
        Double3 kd = material.kD, ks = material.kS;
        Color color = Color.BLACK;
        /*
         * for every light source in our scene we are checking if it enlightens the body
         * and computing the color at the intersection point; color = functionOf(the
         * diffusive coefficient, the specular coefficient, the light intensity and the
         * shininess)
         */
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = Util.alignZero(n.dotProduct(l));
            if (nl != 0 && Util.checkSign(nl, nv)) {  // sign(nl) == sing(nv)
                if (unshaded(intersection, l, n, lightSource)) {
                    Color lightIntensity = lightSource.getIntensity(intersection.point);
                    color = color.add(calcDiffusive(kd, nl, lightIntensity),
                            calcSpecular(ks, l, n, nl, v, nShininess, lightIntensity));
                }
            }
        }
        return color;
    }


}
