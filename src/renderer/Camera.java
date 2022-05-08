package renderer;

import primitives.*;
import scene.Scene;

import java.util.MissingResourceException;


public class Camera {
    private Point p0; // Point of camera's center.
    private Vector vTo; // vector of axis Z.
    private Vector vUp; // vector of axis Y.
    private Vector vRight; // vector of axis X.
    private double distanceFromVP; // distance from View Plane.
    private double heightVP; // height View Plane.
    private double widthVP; // width View Plane.
    private ImageWriter imageWriter;
    private RayTracerBase rayTracerBase;

    /**
     * constructor with point p0 and vectors up and down.
     *
     * @param p   the point = point p0.
     * @param vTo Vector of camera.
     * @param vUp Vector of camera.
     */
    public Camera(Point p, Vector vTo, Vector vUp) {
        this.p0 = p;

        // check if the vTo vUp
        if (vUp.dotProduct(vTo) != 0)
            throw new IllegalArgumentException("The vectors vTo and vUp must be orthogonal.");

        this.vTo = vTo.normalize();
        this.vUp = vUp.normalize();
        this.vRight = vTo.crossProduct(vUp).normalize();

    }

    /**
     * set width and height of view plane
     *
     * @param width  of view plane
     * @param height of view plane
     * @return
     */
    public Camera setVPSize(double width, double height) {
        this.widthVP = width;
        this.heightVP = height;

        return this;
    }

    /**
     * set the distance from camera to the view plane.
     *
     * @param distance from camera to the view plane.
     * @return Camera
     */
    public Camera setVPDistance(double distance) {
        distanceFromVP = distance;

        return this;
    }

    /**
     * setter for ImageWriter
     *
     * @param imageWriter for change in the Render
     * @return this (Render)
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    /**
     * setter for RayTracerBasic
     *
     * @param rayTracerBase for change in the Render
     * @return this (Render)
     */
    public Camera setRayTracerBase(RayTracerBase rayTracerBase) {
        this.rayTracerBase = rayTracerBase;
        return this;
    }

    /**
     * get the distance from camera to the view plane.
     *
     * @return double
     */
    public double getDistanceFromVP() {
        return distanceFromVP;
    }

    /**
     * get the height of view plane.
     *
     * @return double
     */
    public double getHeightVP() {
        return heightVP;
    }

    /**
     * get the width of view plane.
     *
     * @return double
     */
    public double getWidthVP() {
        return widthVP;
    }

    /**
     * void method for active the writePixel() <br>
     * given for each pixel is color
     */
    public Camera renderImage() {
        if (p0 == null || vTo == null || vUp == null || vRight == null || distanceFromVP == 0 ||
                heightVP == 0 || widthVP == 0 || imageWriter == null || rayTracerBase == null)
            throw new MissingResourceException("Not all parameters are initialized", "", "");

        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();

        for (int i = 0; i < nX; ++i)
            for (int j = 0; j < nY; ++j) {
                imageWriter.writePixel(j, i, castRay(i, j));
            }

        writeToImage();

        return this;
    }

    /**
     * Make ray through the pixel (i,j)
     * and return the calculator color in the closest point intersection.
     *
     * @param i of the pixel.
     * @param j of the pixel.
     * @return Color.
     */
    public Color castRay(int i, int j) {
        Ray ray = constructRay(imageWriter.getNx(), imageWriter.getNy(), j, i);
        return rayTracerBase.traceRay(ray);
    }

    /**
     * Print grid on the image.
     *
     * @param interval from pixel to pixel.
     * @param color    of the grid.
     */
    public void printGrid(int interval, Color color) {
        if (imageWriter == null)
            throw new MissingResourceException("Don't exist image", "Camera"
                    , "Initialize 'imageWriter' with function 'setImageWriter'.");

        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();

        for (int i = 0; i < nX; ++i)
            for (int j = 0; j < nY; ++j) {
                if (i % interval == 0 || j % interval == 0)
                    imageWriter.writePixel(i, j, color);
            }

        writeToImage();
    }

    /**
     * active the writeToImage of the imageWriter <br>
     * (we need that for keep know only your near friend)
     */
    public Camera writeToImage() {
        if (imageWriter == null)
            throw new MissingResourceException("Don't exist image", "Camera"
                    , "Initialize 'imageWriter' with function 'setImageWriter'.");

        imageWriter.writeToImage();

        return this;
    }

    /**
     * Make ray from camera's center to pixel.
     *
     * @param nX sum of columns (row width).
     * @param nY sum of rows (column height).
     * @param j  column of pixel.
     * @param i  row of pixel.
     * @return Ray
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        Point pc = p0.add(vTo.scale(distanceFromVP));

        if (!Util.isZero(distanceFromVP)) {
            pc = p0.add(vTo.scale(distanceFromVP));
        }

        double rY = heightVP / nY;
        double rX = widthVP / nX;
        double yI = (i - (double) (nY - 1) / 2) * rY;
        double xJ = (j - (double) (nX - 1) / 2) * rX;

        Point pIJ = pc;
        if (xJ != 0)
            pIJ = pIJ.add(vRight.scale(xJ));
        if (yI != 0)
            pIJ = pIJ.add(vUp.scale(-yI));
        Vector vIJ = pIJ.subtract(p0);

        return new Ray(p0, new Vector(vIJ.getX(), vIJ.getY(), vIJ.getZ()));
    }


}
