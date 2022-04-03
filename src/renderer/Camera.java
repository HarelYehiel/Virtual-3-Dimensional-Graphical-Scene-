package renderer;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;


public class Camera {
    private Point p0; // Point of camera's center.
    private Vector vTo; // vector of axis Z.
    private Vector vUp; // vector of axis Y.
    private Vector vRight; // vector of axis X.
    private double distanceFromVP; // distance from View Plane.
    private double heightVP; // height View Plane.
    private double widthVP; // width View Plane.

    /**
     * get the distance from camera to the view plane.
     * @return double
     */
    public double getDistanceFromVP() {
        return distanceFromVP;
    }

    /**
     * get the height of view plane.
     * @return double
     */
    public double getHeightVP() {
        return heightVP;
    }

    /**
     * get the width of view plane.
     * @return double
     */
    public double getWidthVP() {
        return widthVP;
    }

    /**
     * constructor with point p0 and vectors up and down.
     * @param p the point = point p0.
     * @param vTo Vector of camera.
     * @param vUp Vector of camera.
     */
    public Camera(Point p, Vector vTo, Vector vUp) {
        this.p0 = p;

        // check if the vTo vUp
        if(vUp.dotProduct(vTo) != 0)
            throw new IllegalArgumentException("The vectors vTo and vUp must be orthogonal.");

        this.vTo = vTo.normalize();
        this.vUp = vUp.normalize();
        this.vRight = vTo.crossProduct(vUp).normalize();

    }

    /**
     * set width and height of view plane
     * @param width of view plane
     * @param height of view plane
     * @return
     */
    public Camera setVPSize(double width, double height){
        this.widthVP = width;
        this.heightVP = height;

        return this;
    }

    /**
     *  set the distance from camera to the view plane.
     * @param distance from camera to the view plane.
     * @return Camera
     */
    public Camera setVPDistance(double distance){
        distanceFromVP = distance;

        return this;
    }

    /**
     * Make ray from camera's center to pixel.
     * @param nX sum of columns (row width).
     * @param nY sum of rows (column height).
     * @param j column of pixel.
     * @param i row of pixel.
     * @return Ray
     */
    public Ray constructRay(int nX, int nY, int j, int i){
        Point pc = p0.add(vTo.scale(distanceFromVP));
        double rY = heightVP/nY;
        double rX = widthVP/nX;
        double yI = (i - (double)(nY-1)/2) * rY;
        double xJ = (j - (double)(nX-1)/2) * rX;

        Point pIJ = pc;
        if(xJ != 0)
            pIJ = pIJ.add(vRight.scale(xJ));
        if(yI != 0)
            pIJ = pIJ.add(vUp.scale(-yI));
        Vector vIJ = pIJ.subtract(p0);

        return new Ray(p0, new Vector(vIJ.getX(), vIJ.getY(), vIJ.getZ()));
    }


}
