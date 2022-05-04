package primitives;


import scene.Scene;

public class Material {
    public Double3 kD = new Double3(0,0,0), kS = new Double3(0,0,0);
    public int nShininess = 0;

    /**
     * Set kD
     * @param kD Double3
     * @return Material
     */
    public Material setkD(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * Set kS
     * @param kS Double3
     * @return Material
     */
    public Material setKs(Double3 kS) {
        this.kS = kS;
        return this;
    }

   /**
     * Set kD
     * @param kD double
     * @return Material
     */
    public Material setKd(double kD) {
        this.kD = new Double3(kD);
        return this;
    }

    /**
     * Set kS
     * @param kS double
     * @return Material
     */
    public Material setKs(double kS) {
        this.kS = new Double3(kS);
        return this;
    }

    /**
     * Set nShininess
     * @param nShininess
     * @return Material
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
   }


}
