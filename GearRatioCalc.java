/**
 * Gear Ratio speed and RPM calculator
 *
 * @author  Thomas Coe
 * @version October 14, 2013
 */
public class GearRatioCalc {

    private double[] gearRatios;
    private double differentialRatio;
    private double[] finalDriveRatio;
    private Tire tireSize;

    /**
     * Constructor for a GearRatioCalc. Needs arguments for an array of
     * transmission gear ratios, differential ratio, and Tire
     *
     * @param gearRatios        A double array of transmission ratios
     * @param differentialRatio A double containing the differential ratio
     * @param tireSize          A Tire representing the tire size
     */
    public GearRatioCalc(double[] gearRatios, double differentialRatio, Tire
            tireSize) {
        this.gearRatios = gearRatios;
        this.differentialRatio = differentialRatio;
        this.tireSize = tireSize;
        finalDriveRatio = new double[gearRatios.length];
        for (int i = 0; i < finalDriveRatio.length; i++) {
            finalDriveRatio[i] = gearRatios[i] * differentialRatio;
        }
    }

    /**
     * Calculate the speeds in each gear at the RPM given
     *
     * @param rpms An int of the RPM to calculate for
     */
    public void RPMcalc(int rpms) {
        double[] mph = new double[finalDriveRatio.length];
        for (int i = 0; i < mph.length; i++) {
            mph[i] = rpms * 60 / (finalDriveRatio[i] * tireSize.revsPerMile());
        }
        System.out.println("Speeds at " + rpms + " rpm:");
        for (int i = 0; i < mph.length; i++) {
            System.out.println("Gear " + (i + 1) + ": " + mph[i] + " mph");
        }
    }

    /**
     * Calculate the RPMs in each gear at the speed given
     *
     * @param mph An int containing the speed in MPH to calculate for
     */
    public void MPHcalc(int mph) {
        double[] rpm = new double [finalDriveRatio.length];
        for (int i = 0; i < rpm.length; i++) {
            rpm[i] = (mph * finalDriveRatio[i] * tireSize.revsPerMile()) / 60.0;
        }
        System.out.println("RPMs at " + mph + " mph:");
        for (int i = 0; i < rpm.length; i++) {
            System.out.println("Gear " + (i + 1) + ": " + rpm[i] + " rpm");
        }
    }

}
