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

    /**
     * recursion stop condition - the maximum number of colors
     */
    private static final int MAX_CALC_COLOR_LEVEL = 10;

    /**
     * recursion stop condition -
     */
    private static final double MIN_CALC_COLOR_K = 0.001;

    /**
     * default coefficient
     */
    private static final Double3 INITIAL_K = new Double3(1.0);

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
    private Color calcColor(GeoPoint intersection, Ray ray, int level, Double3 k) {
        Color color = scene.ambientLight.getIntensity().add(intersection.geometry.getEmission());
        color = color.add(calcLocalEffects(intersection, ray, k));
        return (1 == level) ? color : color.add(calcGlobalEffects(intersection, ray, level, k));
    }

    /**
     * calcColor for point
     *
     * @param intersection point for calc color for her
     * @param ray          the ray are intersect whit the point
     * @return return the intensity of the ambientLight of scene
     */
    private Color calcColor(GeoPoint intersection, Ray ray) {
        return calcColor(intersection, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K).add(scene.ambientLight.getIntensity());
    }

    /**
     * calculate color
     *
     * @param intersection for calculate is color for her
     * @return return the intensity of the ambientLight of scene
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray, Double3 k) {
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
                Double3 ktr = transparency(lightSource, l, n, intersection);
                if (ktr.scale(k).double3TestBetweenDoubles(MIN_CALC_COLOR_K)) {
                    Color lightIntensity = lightSource.getIntensity(intersection.point).scale(ktr);
                    color = color.add(calcDiffusive(kd, nl, lightIntensity),
                            calcSpecular(ks, l, n, nl, v, nShininess, lightIntensity));
                }
            }
        }
        return color;
    }

    /**
     * construct the refracted ray
     *
     * @param n     vector "on" the solid
     * @param point pointed by the vector n, on the solid to
     * @param inRay direction of the refracted vector
     * @return refracted ray
     */
    private Ray constructRefractedRay(Vector n, Point point, Ray inRay) {
        return new Ray(point, inRay.getDir(),n);
    }

    /**
     * compute reflections and refractions effects in a point
     *
     * @param geopoint point on the body
     * @param ray      direction of the light
     * @param level    level of recursion
     * @param k        coefficient
     * @return color of the point
     */
    private Color calcGlobalEffects(GeoPoint geopoint, Ray ray, int level, Double3 k) {
        Color color = Color.BLACK;
        Material material = geopoint.geometry.getMaterial();
        Double3 kr = material.kR, kkr = kr.scale(k);
        Vector normal = geopoint.geometry.getNormal(geopoint.point);
        if (kkr.double3TestBetweenDoubles(MIN_CALC_COLOR_K)) {
            color = calcGlobalEffect(constructReflectedRay(normal, geopoint.point, ray), level - 1, kr, kkr);
        }
        Double3 kt = material.kT, kkt = kt.scale(k);
        if (kkt.double3TestBetweenDoubles(MIN_CALC_COLOR_K)) {
            color = calcGlobalEffect(constructRefractedRay(normal, geopoint.point, ray), level - 1, kt, kkt);
        }
        return color;
    }

    /**
     * compute reflections and refractions effects in a point
     *
     * @param ray   direction of the light
     * @param level level of recursion
     * @param kx    coefficient
     * @param kkx   coefficient
     * @return color of the point
     */
    private Color calcGlobalEffect(Ray ray, int level, Double3 kx, Double3 kkx) {
        GeoPoint gp = findClosestIntersection(ray);
        return ((gp == null) ? scene.background : calcColor(gp, ray, level - 1, kkx)).scale(kx);
    }

    /**
     * find the closest intersection point between the ray and the body
     *
     * @param ray
     * @return closest point from the geometry bodies and the ray
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        return (ray.findClosestGeoPoint(scene.geometries.findGeoIntersections(ray)));
    }

    /**
     * construct the reflected ray
     *
     * @param n     vector "on" the solid
     * @param point pointed by the vector n, on the solid to
     * @param inRay direction of the reflected vector
     * @return reflected ray
     */
    private Ray constructReflectedRay(Vector n, Point point, Ray inRay) {
        Vector v = inRay.getDir();
        double nv = n.dotProduct(v);
        if (nv == 0)
            return null;
        Vector direction = v.subtract(n.scale(2 * nv)).normalize();

        return  new Ray(point, direction,n);
    }

    /**
     * @param gp
     * @param l
     * @param n
     * @return
     */
    private boolean unshaded(GeoPoint gp, Vector l, Vector n, LightSource lightSource) {
        Ray lightRay = new Ray(gp.point, l.scale(-1), n);
        double maxDistance = lightSource.getDistance(gp.point);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay, maxDistance);

        return intersections == null ? true : false;
    }

    /**
     * compute the transparency coefficient
     *
     * @param light    the light source
     * @param l        direction of the light
     * @param n        normal to the body
     * @param geopoint intersection point
     * @return transparency coefficient
     */
    private Double3 transparency(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
        Ray lightRay = new Ray(geopoint.point, l.scale(-1), n);
        double lightDistance = light.getDistance(geopoint.point);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay, lightDistance);
        if (intersections == null)
            return new Double3(1.0);
        Double3 ktr = new Double3(1.0);
        for (GeoPoint gp : intersections) {
            if (Util.alignZero(gp.point.distance(geopoint.point) - lightDistance) <= 0) {
                ktr = ktr.scale(gp.geometry.getMaterial().kT);
                if (!(ktr.double3TestBetweenDoubles(MIN_CALC_COLOR_K)))
                    return new Double3(0.0);
            }

        }
        return ktr;
    }
}
