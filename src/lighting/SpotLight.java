package lighting;

import primitives.*;
import primitives.Point;
import primitives.Vector;

public class SpotLight extends PointLight{
    /**
     * direction of the spot light
     */
    private Vector direction;
    /**
     * For a narrower beam of light,<br>
     * The larger it is, the narrower the beam of light
     *
     */
    private int sharp = 1;

    public SpotLight(Color color, Point position, Vector direction) {
        super(color, position);
        this.direction = direction.normalize();
    }

    /**
     * @param sharp the sharp to set
     */
    public SpotLight setSharp(int sharp) {
        this.sharp = sharp;
        return this;
    }

    @Override
    public Color getIntensity(Point p) {
        double dirDotL = Util.alignZero(direction.dotProduct(getL(p)));
        if (dirDotL < 0)
            return Color.BLACK;

        if (sharp != 1)
            dirDotL = Math.pow(dirDotL, sharp);

        Color iL = super.getIntensity(p);
        return iL.scale(dirDotL);
    }
}
