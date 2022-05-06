import java.util.Objects;
import java.util.Random;
import java.util.ArrayList;

/**
 * Represents a resistor.
 */
public class Resistor {
    private final double nominalResistance;
    private final double tolerance;
    private final double actualResistance;

    /**
     * Class constructor.
     * @param nominalResistance A double representing the resistance of the resistor. Cannot be null.
     * @param tolerance A double representing the tolerance of the resistor. Cannot be null.
     */
    public Resistor(double nominalResistance, double tolerance) {
        this.nominalResistance = nominalResistance;
        this.tolerance = tolerance;
        this.actualResistance = calculateActualResistance();
    }

    /**
     * Calculates the resistor's actual resistance.
     * @return A double representing the actual resistance of the resistor.
     */
    public double calculateActualResistance() {
        double pct = tolerance / 100;
        double toleranceDiff = nominalResistance * pct;
        double low = nominalResistance - toleranceDiff;
        double high = nominalResistance + toleranceDiff;

        Random rand = new Random();

        // Adding .001 due to non-inclusion on upper bounds
        return rand.nextDouble(low, high + .001);
    }

    /**
     * Retrieves the resistor's nominal resistance.
     * @return A double representing the nominal resistance of the resistor.
     */
    public double getNominalResistance() { return this.nominalResistance; }

    /**
     * Retrieves the resistor's tolerance.
     * @return A double representing the tolerance of the resistor.
     */
    public double getTolerance() { return this.tolerance; }

    /**
     * Retrieves the resistor's actual resistance.
     * @return A double representing the actual resistance of the resistor.
     */
    public double getActualResistance() { return this.actualResistance; }

    /**
     * Calculates the resistor's band colors and order.
     * @return A string representing the band colors and order of the resistor.
     */
    public String colorBands() {
        String[] colorBands = new String[4];
        ArrayList<Band> bandTable = Band.createBandArray();

        // Convert to char array for individual int comparison
        int resistance = (int)this.nominalResistance;
        String resistStr = String.valueOf(resistance);
        char[] resistCharArr = resistStr.toCharArray();
        // Assume zeros after first 2 digits
        int multiplier = resistCharArr.length - 2;

        // Find band colors using forEach loop
        for (Band band : bandTable) {
            // First band
            if (band.digit != null && band.digit == Integer.parseInt(String.valueOf(resistCharArr[0]))) {
                colorBands[0] = band.color;
            }

            // Second band
            if (band.digit != null && band.digit == Integer.parseInt(String.valueOf(resistCharArr[1]))) {
                colorBands[1] = band.color;
            }

            // Third band
            if (band.multiplier != null && band.multiplier == multiplier) {
                colorBands[2] = band.color;
            }

            // Fourth band
            if (band.tolerance != null && band.tolerance == this.tolerance) {
                colorBands[3] = band.color;
            }
        }

        // Prep colorBands array for output
        String delimiter = " ";
        return String.join(delimiter, colorBands);
    }

    /**
     * Generates a hash code for the resistor.
     * @return An integer representing the hash code of the resistor.
     */
    @Override
    public int hashCode() { return Objects.hash(this.nominalResistance, this.tolerance, this.actualResistance); }

    /**
     * Checks if the current resistor is equal to the resistor passed in.
     * @param obj The resistor to check against.
     * @return True if both resistors are the same. False if the resistors are different.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof Resistor that)) return false;

        return
            this.nominalResistance == that.nominalResistance &&
            this.tolerance == that.tolerance &&
            this.actualResistance == that.actualResistance;
    }

    /**
     * Converts the resistor to a string.
     * @return A string representing the resistor.
     */
    @Override
    public String toString() {
        return
            String.format(
                "Nominal Resistance = %.3f, Tolerance = %.3f, Actual Resistance = %.3f, Bands = %s",
                nominalResistance, tolerance, actualResistance, colorBands()
            );
    }
}
