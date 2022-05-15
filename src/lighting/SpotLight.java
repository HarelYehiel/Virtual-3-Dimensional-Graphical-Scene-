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
     * Set the kQ.
     * @param kQ
     * @return this
     */
    @Override
    public SpotLight setKq(double kQ) {
        return (SpotLight)super.setKq(kQ);
    }

    /**
     * @param kC the kC to set
     */
    public SpotLight setkC(double kC) {
        return (SpotLight)super.setkC(kC);
    }

    /**
     * @param kL the kL to set
     */
    public SpotLight setkL(double kL) {
        return (SpotLight)super.setKl(kL);
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
