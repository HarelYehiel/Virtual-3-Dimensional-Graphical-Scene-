package lighting;
//import primitives.Color;
import primitives.*;

public class AmbientLight {
       private Color intensity;

    void AmbientLight(Color color, Double3 double3){
            intensity  = color.scale(double3);
    }

    void AmbientLight(){
        intensity = new Color(0,0,0);
    }

    Color getIntensity(){
        return intensity;
    }
}
