package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class PointLight extends Light implements LightSource{
    private Point position;
    private double kC = 1, kL = 0, kQ = 0;

    /**
     * Set the kC.
     * @param kC
     * @return this
     */
    public PointLight setkC(double kC) {
        this.kC = kC;
        return this;
    }

    /**
     * Set the kL.
     * @param kL
     * @return this
     */
    public PointLight setKl(double kL) {
        this.kL = kL;
        return this;
    }

    /**
     * Set the kQ.
     * @param kQ
     * @return this
     */
    public PointLight setKq(double kQ) {
        this.kQ = kQ;
        return this;
    }

    /**
     *
     * @param color
     * @param position
     */
    public PointLight(Color color, Point position) {
        super(color);
        this.position = position;
    }

    @Override
    public Color getIntensity(Point p) {
        double d = position.distanceSquared(p);
        Color iL = this.getIntensity().reduce(kC + kL * Math.sqrt(d) + kQ * d);
        return iL;
    }

    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }
}
