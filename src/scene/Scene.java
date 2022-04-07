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

    public Scene(String name1) {
        name = name1;
        background = new Color(0,0,0);
        ambientLight = new AmbientLight();
        geometries = new Geometries();
    }

    public Scene setBackground(Color background) {
        this.background = background;

        return this;
    }

    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;

        return this;

    }

    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;

        return this;

    }

}
