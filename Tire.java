import java.util.Scanner;
/**
 * A Tire represents the size of a typical car tire and provides basic
 * mathematical operations on that tire size.
 *
 * @author  Thomas Coe
 * @version October 14, 2013
 */
public class Tire {

    private int width;
    private int ratio;
    private int diameter;

    /**
     * Constructor for a Tire using separate int arguments
     *
     * @param width    The width of the tire in mm
     * @param ratio    The ratio of the tire's height to width
     * @param diameter The diameter of the wheel in inches
     */
    public Tire(int width, int ratio, int diameter) {
        this.width = width;
        this.ratio = ratio;
        this.diameter = diameter;
    }

    /**
     * Constructor for a Tire using a single String argument. String should be
     * formatted as "205/40R17" or "205 40 17"
     *
     * @param size A String containing the size of the tire
     */
    public Tire(String size) {
        Scanner tireSize = new Scanner(size);
        tireSize.useDelimiter("[R\\s/]");
        this.width = tireSize.nextInt();
        this.ratio = tireSize.nextInt();
        this.diameter = tireSize.nextInt();
    }

    /**
     * Constructor for a Tire using another Tire object. Primarily used to
     * create a deep copy of a Tire in order to maintain proper encapsulation
     *
     * @param other The Tire this Tire will be based upon
     */
    public Tire (Tire other) {
        this.width = other.width;
        this.ratio = other.ratio;
        this.diameter = other.diameter;
    }

    /**
     * Calculate the total diameter of the tire in inches
     *
     * @return A double containing the diameter of the tire
     */
    private double tireDiameter() {
        double sidewall = width * (ratio / 100.0);
        //convert sidewall thickness from mm to inches
        sidewall *= 0.03937;
        return diameter + 2 * sidewall;
    }

    /**
     * Calculate the circumference of the tire in inches
     *
     * @return A double containing the circumference of the tire
     */
    private double circumference() {
        return Math.PI * tireDiameter();
    }

    /**
     * Calculate the number of revolutions the tire must make to travel one mile
     *
     * @return A double containing the number of revolutions per mile
     */
    public double revsPerMile() {
        return (5280 * 12) / circumference();
    }

    /**
     * Return a String containing the tire size details
     *
     * @return A String representation of the tire
     */
    @Override
    public String toString() {
        return width + "/" + ratio + "R" + diameter;
    }

    /**
     * Checks for equality with another Tire
     *
     * @param other The object to compare this to
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof Tire)) {
            return false;
        }
        Tire o = (Tire) other;
        if (this.width == o.width
                && this.ratio == o.ratio
                && this.diameter == o.diameter) {
            return true;
        }
        return false;
    }

    /**
     * Returns a hashCode for a Tire
     *
     * @return An int hashCode for the Tire
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += width;
        hash += ratio * 13;
        hash += diameter * 17;
        return hash;
    }
}
