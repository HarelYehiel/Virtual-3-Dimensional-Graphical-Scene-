package lighting;
//import primitives.Color;
import primitives.*;

public class AmbientLight {
       private Color intensity;

    /**
     * Constructor with color and double3 for initialize the intensity.
     * @param color
     * @param double3
     */
     public AmbientLight(Color color, Double3 double3){
            intensity  = color.scale(double3);
    }



    /**
     * Constructor with initialize the intensity with black color.
     */
    public AmbientLight(){
        intensity = new Color(0,0,0);
    }

    /**
     * get intensity.
     * @return color (intensity).
     */
     public Color getIntensity(){
        return intensity;
    }
}
