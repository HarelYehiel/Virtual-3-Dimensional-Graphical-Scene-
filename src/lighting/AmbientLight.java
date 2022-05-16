package lighting;
//import primitives.Color;
import primitives.*;

/**
 * Ambient Light= like sun in cloudy day Lighting without<br>
 * specific direction or source
 *
 * @author yosefHaim
 *
 */
public class AmbientLight extends Light {

    /**
     * Constructor with color and double3 for initialize the intensity.
     * @param color
     * @param double3
     */
     public AmbientLight(Color color, Double3 double3){
         super(color.scale(double3));
    }



    /**
     * Constructor with initialize the intensity with black color.
     */
    public AmbientLight(){
        super(Color.BLACK);
    }

}
