package lighting;

import primitives.Color;
import primitives.Double3;
import primitives.Point;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource{
        private Vector direction;

    /**
     *
     * @param color
     * @param direction
     */
    public DirectionalLight(Color color, Vector direction) {
        super(color);
        this.direction = direction;
    }

    @Override
    public Color getIntensity(Point p) {
        return getIntensity();
    }

    @Override
    public Vector getL(Point p) {
        return direction;
    }
}
