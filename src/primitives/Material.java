package primitives;


/**
 * PDS Class - hold attenuation and shininess factors
 *
 * @author Alexandre
 *
 */
public class Material {

    /**
     * kD - the diffusive coefficient<br>
     * kS - the specular coefficient
     * kT - transparency coefficient
     * kR - reflection coefficient
     */
    public Double3 kD = new Double3(0,0,0), kS = new Double3(0,0,0);
    public Double3 kR = new Double3(0,0,0), kT = new Double3(0,0,0);
    public int nShininess = 0;

    /**
     * set the kR coef.
     *
     * @param kR the reflection coefficient
     * @return this - Builder pattern
     */
    public Material setkR(Double3 kR) {
        this.kR = kR;
        return this;
    }

    /**
     * set the kT coef.
     *
     * @param kT the transparency coefficient
     * @return this - Builder pattern
     */
    public Material setkT(Double3 kT) {
        this.kT = kT;

        return this;
    }

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
