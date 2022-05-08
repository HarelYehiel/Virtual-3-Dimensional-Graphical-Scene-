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

    public List<LightSource> lights = new LinkedList<>();

    /**
     * Constructor with name.
     * @param name1 of scene.
     */
    public Scene(String name1) {
        name = name1;
        background = new Color(0,0,0);
        ambientLight = new AmbientLight();
        geometries = new Geometries();
    }

    /**
     *
     * @param lights
     */
    public void setLights(List<LightSource> lights) {
        this.lights = lights;
    }

    /**
     * Set background.
     * @param background for initialize.
     * @return Scene.
     */
    public Scene setBackground(Color background) {
        this.background = background;

        return this;
    }

    /**
     * Set ambientLight
     * @param ambientLight for initialize.
     * @return Scene.
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;

        return this;

    }

    /**
     * Set geometries.
     * @param geometries for initialize.
     * @return Scene.
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;

        return this;

    }


}
