package renderer;

import primitives.*;

import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.stream.*;
import java.util.Random;


/**
 * present camera, point of view in three dimension
 *
 * @author yosefHaim <br>
 * JavaDocs edited by Alexandre
 */
public class Camera {

    /**
     * location of the Camera
     */
    private Point p0; // Point of camera's center.

    /**
     * vector Right Hand Coordinate System
     */
    private Vector vTo; // vector of axis Z.

    /**
     * vector Right Hand Coordinate System
     */
    private Vector vUp; // vector of axis Y.

    /**
     * vector Right Hand Coordinate System
     */
    private Vector vRight; // vector of axis X.

    /**
     * distance between camera and view plane width of view plane height of view
     * plane
     */
    private double distanceFromVP; // distance from View Plane.

    /**
     * distance between camera and view plane width of view plane height of view
     * plane
     */
    private double heightVP; // height View Plane.

    /**
     * distance between camera and view plane width of view plane height of view
     * plane
     */
    private double widthVP; // width View Plane.

    /**
     * Num rays in pixel.
     */
    private int numRaysInPixel = 1;

    /**
     * Num of threads count
     */
    boolean trueOnMultipleThreads = true;

    /**
     * How often time to print interval (in second).
     */
    int printInterval = 0;

    /**
     *
     */
    int maxDeepInPixel = 4;

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
     * set how much num rays in pixel
     * the num bigger more 1.
     *
     * @param numRaysInPixel get the num rays in each pixel
     * @return this Camera, Build
     */
    public Camera setnumRaysInPixel(int numRaysInPixel) {

        if (numRaysInPixel > 1) this.numRaysInPixel = numRaysInPixel;
        return this;
    }

    /**
     * Changing the direction of camera Head it mean the vto vector
     *
     * @param target point to direct the vTo of camera<br>
     *               In fact, this is the point you want the camera to take
     * @return this the camera itself for builder design
     */
    public Camera setCameraHead(Point target) {
        Vector v1 = target.subtract(p0).normalize();
        try {
            vRight = v1.crossProduct(new Vector(0, 0, 1)).normalize();
        } catch (Exception e) {
            vRight = new Vector(0, -1, 0);
        }
        vUp = vRight.crossProduct(v1).normalize();
        vTo = v1;
        return this;
    }

    /**
     * set for Threads Count.
     *
     * @param threadsCount
     */
    public Camera setTrueOnMultipleThreads(boolean threadsCount) {
        this.trueOnMultipleThreads = threadsCount;
        return this;
    }

    /**
     * set for Print Interval.
     *
     * @param printInterval
     */
    public Camera setPrintInterval(int printInterval) {
        this.printInterval = printInterval;
        return this;
    }

    /**
     * setter for location Point of camera Without
     * changing the state of the vectors
     *
     * @param posi point to replace whit the current location point
     * @return this (camera itself )
     */
    public Camera setPosition(Point posi) {
        this.p0 = posi;
        return this;
    }

    /**
     * Rotate the camera Horizontally Builder pattern This <br>
     * function change the angle of vRight & vUp along vTo direction the angle
     * change Counterclockwise by the vTo axis!
     *
     * @param angle the angle to change by Degrees,for Rotate the camera
     *              Horizontally
     * @return this the camera itself for builder design
     */
    public Camera rotateHorizontally(double angle) {
        // change to degrees
        double cosAngle = Math.cos((angle * Math.PI) / 180);
        double sinAngle = Math.sin((angle * Math.PI) / 180);
        vRight = rotateHorizontallyHelp(vRight, cosAngle, sinAngle);
        vUp = rotateHorizontallyHelp(vUp, cosAngle, sinAngle);
        return this;
    }

    /**
     * rotate Horizontally Help function
     * for shortcut the code
     *
     * @param v        vector for return rotated by the degree
     * @param cosAngle cos Angle in radian
     * @param sinAngle sin Angle in radian
     * @return vector rotated by the degree
     */
    private Vector rotateHorizontallyHelp(Vector v, double cosAngle, double sinAngle) {
        // formula :
        // Vfinal = V * cos(angle) + (K x V) * sin(angle) + K * (K dot V) * (1 -
        // cos(angle))
        // vfinal = the vector we want to turn by tta degree
        // K= the vector Axis of rotation(vector how don't change)
        // V= vector are supusd to rotate and finely will changed

        boolean cosZero = Util.isZero(cosAngle);
        boolean sinZero = Util.isZero(sinAngle);
        Vector vFinal;
        if (cosZero) {
            vFinal = vTo.crossProduct(v).scale(sinAngle);
        } else {
            vFinal = v.scale(cosAngle);
            if (!sinZero) {
                Point p = vFinal.add(vTo.crossProduct(v).scale(sinAngle));
                vFinal = new Vector(new Double3(p.getX(), p.getY(), p.getZ()));
            }
        }
        return vFinal.normalize();

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

        if (trueOnMultipleThreads) {

            Pixel.initialize(nY, nX, printInterval);
            IntStream.range(0, nY).parallel().forEach(i -> {
                IntStream.range(0, nX).parallel().forEach(j -> {
                    imageWriter.writePixel(j, i, castRay(i, j));
                    Pixel.pixelDone();
                    Pixel.printPixel();

                });
            });


//            Pixel.initialize(nY, nX, printInterval);
//            while (threadsCount-- > 0) {
//                new Thread(() -> {
//                    for (Pixel pixel = new Pixel(); pixel.nextPixel(); Pixel.pixelDone())
//                        imageWriter.writePixel(pixel.row, pixel.col, castRay(pixel.col, pixel.row));
//                }).start();
//            }

            Pixel.waitToFinish();
        } else {

            for (int i = 0; i < nX; ++i)
                for (int j = 0; j < nY; ++j) {
                    imageWriter.writePixel(j, i, castRay(i, j));
                }
        }

        writeToImage();

        return this;
    }

    /**
     * Make rays through the pixel (i,j)
     * and return the calculator average color in the closest points intersection.
     *
     * @param i of the pixel.
     * @param j of the pixel.
     * @return Color.
     */
    public Color castRay(int i, int j) {
        List<Ray> rays = constructRay(imageWriter.getNx(), imageWriter.getNy(), j, i);

        Color rangeColor = rayTracerBase.traceRay(rays.get(0));

        int len = rays.size();
        for (int k = 1; k < len; ++k) {
            rangeColor = rangeColor.add(rayTracerBase.traceRay(rays.get(k)));
        }
        if (len > 1)
            rangeColor = rangeColor.scale((double) 1 / len);

        return rangeColor;
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
     * @param topNumber
     * @param lowerNumber
     * @return
     */
    private double randomNumber(double lowerNumber, double topNumber) {
        if (lowerNumber > topNumber) {
            double save = lowerNumber;
            lowerNumber = topNumber;
            topNumber = save;
        }

        Random random = new Random();
        double res = 1;
        do {
            res = random.nextDouble(lowerNumber, topNumber);
        } while (res == 0);

        return res;

    }

    /**
     * Make rays from each pixel.
     *
     * @param nX sum of columns (row width).
     * @param nY sum of rows (column height).
     * @param j  column of pixel.
     * @param i  row of pixel.
     * @return Ray
     */
    public List<Ray> constructRay(int nX, int nY, int j, int i) {
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

        List<Ray> listOfRaysInPixel = new LinkedList<>();
        // The ray through the center of the pixel be in the first var in the list.
        listOfRaysInPixel.add(new Ray(p0, new Vector(vIJ.getX(), vIJ.getY(), vIJ.getZ())));


        if (numRaysInPixel > 1) {
            double halfNx = rX / 2;
            double halfNy = rY / 2;
            Point saveCenterOfPixel = pIJ;

            for (int k = 1; k < numRaysInPixel; ++k) {
                pIJ = pIJ.add(vRight.scale(randomNumber(-halfNx, halfNx)));
                pIJ = pIJ.add(vUp.scale(randomNumber(-halfNy, halfNy)));
                vIJ = pIJ.subtract(p0);
                listOfRaysInPixel.add(new Ray(p0, new Vector(vIJ.getX(), vIJ.getY(), vIJ.getZ())));
                pIJ = saveCenterOfPixel;
            }
        }
        return listOfRaysInPixel;
    }

    public Camera setMultithreading(int i) {
        return this;
    }

    public Camera setDebugPrint(double v) {
        return this;
    }

    /**
     * @param centerPixel
     * @param width
     * @param length
     */
    private Color DeepInPixel(int deep, Point centerPixel, double width, double length) {

        width = width / 2;
        length = length / 2;
        List<Point> points = new LinkedList<>();
        points.add(new Point(centerPixel.getX() + width, centerPixel.getY() + length, centerPixel.getZ()));
        points.add(new Point(centerPixel.getX() + width, centerPixel.getY() - length, centerPixel.getZ()));
        points.add(new Point(centerPixel.getX() - width, centerPixel.getY() + length, centerPixel.getZ()));
        points.add(new Point(centerPixel.getX() - width, centerPixel.getY() - length, centerPixel.getZ()));

        Vector vIJ = centerPixel.subtract(p0);
        Color colorCenterPixel = rayTracerBase.traceRay(new Ray(p0, new Vector(vIJ.getX(), vIJ.getY(), vIJ.getZ())));

        boolean differentColor = false;
        for (Point point : points) {
            vIJ = point.subtract(p0);
            Color colorSecond = rayTracerBase.traceRay(new Ray(p0, new Vector(vIJ.getX(), vIJ.getY(), vIJ.getZ())));

            if (!colorCenterPixel.equals(colorSecond)) {
                differentColor = true;
                break;
            }
        }

        Color sumColor = Color.BLACK;
        if (differentColor && deep < maxDeepInPixel) {
            sumColor.add(DeepInPixel(deep + 1,
                    new Point(centerPixel.getX() - width / 2, centerPixel.getY() + length / 2, centerPixel.getZ()),
                    width, length));

            sumColor.add(DeepInPixel(deep + 1,
                    new Point(centerPixel.getX() + width / 2, centerPixel.getY() + length / 2, centerPixel.getZ()),
                    width, length));

            sumColor.add(DeepInPixel(deep + 1,
                    new Point(centerPixel.getX() - width / 2, centerPixel.getY() - length / 2, centerPixel.getZ()),
                    width, length));

            sumColor.add(DeepInPixel(deep + 1,
                    new Point(centerPixel.getX() + width / 2, centerPixel.getY() - length / 2, centerPixel.getZ()),
                    width, length));
        }
        else if(differentColor && deep == maxDeepInPixel){
            List<Ray> listOfRaysInPixel = new LinkedList<>();
            Point saveCenterOfPixel = centerPixel;

            for (int k = 1; k < numRaysInPixel/Math.pow(4,deep); ++k) {
                centerPixel = centerPixel.add(vRight.scale(randomNumber(-width, width)));
                centerPixel = centerPixel.add(vUp.scale(randomNumber(-length, length)));
                vIJ = centerPixel.subtract(p0);
                listOfRaysInPixel.add(new Ray(p0, new Vector(vIJ.getX(), vIJ.getY(), vIJ.getZ())));
                centerPixel = saveCenterOfPixel;
            }
            for(Ray ray : listOfRaysInPixel)
                sumColor.add(rayTracerBase.traceRay(ray));

        }
        else
            return colorCenterPixel.scale(numRaysInPixel/Math.pow(4,deep));

        return sumColor;
    }
}
