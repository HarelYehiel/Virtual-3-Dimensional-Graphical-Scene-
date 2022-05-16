package lighting;

import primitives.Color;

/**
 * abstract class for light Mutations ,Types of light
 *
 * @author yosefHaim
 *
 */
abstract class Light {

    /**
     * intensity: the Light intensity of a light source
     */
    private Color intensity;

    /**
     * ctor for light
     *
     * @param intens Light intensity by Color
     */
    protected Light(Color color) {
        this.intensity = color;
    }

    /**
     * getter for intensity
     *
     * @return intensity Color of Ambient Light
     */
    public Color getIntensity() {
        return intensity;
    }


}
