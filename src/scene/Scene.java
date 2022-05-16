package scene;
import primitives.*;
import geometries.*;
import lighting.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Scene is Contains all the components that <br>
 * exist in the space we want to photograph
 *
 * @author yosefHaim
 */
public class Scene {

    /**
     * name of Scene
     */
    public String name;

    /**
     * background of the scene (all the ray how don't <br>
     * intersect whit body will get the color of background)<br>
     * Default=black color
     */
   public Color background;

    /**
     * ambient Light of scene <br>
     * Default=black color
     */
   public AmbientLight ambientLight;

    /**
     * collection of geometries shape
     */
   public Geometries geometries;

    /**
     * list of source light in scene <br>
     * Default= empty list
     */
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
     * setter for LightSourceList
     *
     * @param lights List of light
     * @return this (scene)
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
