package lighting;

import primitives.Color;
import primitives.Double3;

/**
 *
 */
abstract class Light {
    private Color intensity;

    /**
     *
     * @param color
     */
    protected Light(Color color) {
        this.intensity = color;
    }

    /**
     *
     * @return
     */
    public Color getIntensity() {
        return intensity;
    }


}
