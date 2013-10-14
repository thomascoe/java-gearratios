import java.util.Scanner;
/**
 * Driver for a GearRatioCalc
 *
 * @author Thomas Coe
 * @version October 14, 2013
 */
public class Calculator {
    private static double[] ratios = {3.136, 1.888, 1.330, 1.0, 0.814};
    private static double dRatio = 4.3;
    private static GearRatioCalc gearCalculator;

    public static void main(String[] args) {
        //Prompt for input for tire size
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter your car tire size formatted like "
                +"205/40R17");
        String size = keyboard.nextLine();
        Tire tire1 = new Tire(size);
        //Create a new GearRatioCalc
        gearCalculator = new GearRatioCalc(ratios, dRatio, tire1);
        int next = 0;
        while(next!=3) {
            System.out.println("1. Calculate RPMs at a given speed");
            System.out.println("2. Calculate speeds at a given RPM");
            System.out.println("3. Exit");
            next = Integer.parseInt(keyboard.nextLine());
            if (next==1) {
                promptRPM();
            } else if (next==2) {
                promptMPH();
            }
        }
    }

    public static void promptRPM() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please input the speed in MPH you wish to "
                +"calculate for:");
        int speed = Integer.parseInt(keyboard.nextLine());
        gearCalculator.MPHcalc(speed);
    }

    public static void promptMPH() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please input the RPM you wish to "
                +"calculate for:");
        int revs = Integer.parseInt(keyboard.nextLine());
        gearCalculator.RPMcalc(revs);
    }

}
