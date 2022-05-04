package lighting;
import primitives.*;

/**
 *
 */
public interface LightSource {

    /**
     * Get Intensity
     * @param p
     * @return
     */
    public Color getIntensity(Point p);

    /**
     * Get L
     * @param p
     * @return
     */
    public Vector getL(Point p);
}
