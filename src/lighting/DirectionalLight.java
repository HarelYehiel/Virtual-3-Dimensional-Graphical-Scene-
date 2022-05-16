package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * light source is far away - like sun
 *
 * @author yosefHaim
 *
 */
public class DirectionalLight extends Light implements LightSource{
        private Vector direction;

    /**
     *
     * @param color
     * @param direction
     */
    public DirectionalLight(Color color, Vector direction) {
        super(color);
        this.direction = direction.normalize();
    }


    @Override
    public Color getIntensity(Point p) {
        return getIntensity();
    }

    @Override
    public Vector getL(Point p) {
        return direction;
    }

    @Override
    public double getDistance(Point point){
        return Double.POSITIVE_INFINITY;
    }
}
