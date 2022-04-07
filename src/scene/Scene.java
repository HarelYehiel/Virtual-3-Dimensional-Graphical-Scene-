package scene;
import primitives.*;
import geometries.*;
import lighting.*;

import java.util.LinkedList;
import java.util.List;

public class Scene {
    public String name;
    public Color background;
    public AmbientLight ambientLight;
    public Geometries geometries;

    void Scene(String name1) {
        name = name1;
        background = new Color(0,0,0);

//         ????????????
        List<Geometries> geometries1 = new LinkedList<>();



        ambientLight = new AmbientLight();
        geometries = new Geometries();
    }

    void Scane(){

    }

    Scene Builder(){


        return this;
    }


}
