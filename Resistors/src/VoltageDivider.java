import java.util.Objects;

/**
 * Represents a voltage divider.
 */
public class VoltageDivider {
    private final Resistor firstResistor;
    private final Resistor secondResistor;
    private final double nominalGain;
    private final double actualGain;

    /**
     * Class constructor.
     * @param firstResistor A resistor representing the first resistor of the voltage divider. Cannot be null.
     * @param secondResistor A resistor representing the second resistor of the voltage divider. Cannot be null.
     */
    public VoltageDivider(Resistor firstResistor, Resistor secondResistor) {
        Objects.requireNonNull(firstResistor);
        Objects.requireNonNull(secondResistor);
        this.firstResistor = firstResistor;
        this.secondResistor = secondResistor;

        double[] gains = calculateGains();
        this.nominalGain = gains[0];
        this.actualGain = gains[1];
    }

    /**
     * Retrieves the voltage divider's nominal gain.
     * @return A double representing the nominal gain of the voltage divider.
     */
    public double getNominalGain() { return this.nominalGain; }

    /**
     * Retrieves the voltage divider's actual gain.
     * @return A double representing the actual gain of the voltage divider.
     */
    public double getActualGain() { return this.actualGain; }

    /**
     * Calculates the nominal and actual gains of the voltage divider.
     * @return A double array containing the nominal gain at the first index and the actual gain at the second index.
     */
    private double[] calculateGains() {
        double[] gains = new double[2];
        double firstNominalResistance = firstResistor.getNominalResistance();
        double secondNominalResistance = secondResistor.getNominalResistance();
        double firstActualResistance = firstResistor.getActualResistance();
        double secondActualResistance = secondResistor.getActualResistance();

        // Nominal Gain
        gains[0] = secondNominalResistance / (firstNominalResistance + secondNominalResistance);
        // Actual Gain
        gains[1] = secondActualResistance / (firstActualResistance + secondActualResistance);

        return gains;
    }

    /**
     * Generates a hash code for the voltage divider.
     * @return An integer representing the hash code of the voltage divider.
     */
    @Override
    public int hashCode() { return Objects.hash(this.firstResistor, this.secondResistor); }

    /**
     * Checks if the current voltage divider is equal to the voltage divider passed in.
     * @param obj The voltage divider to check against.
     * @return True if both voltage dividers are the same. False if the voltage dividers are different.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof VoltageDivider that)) return false;

        return
            this.firstResistor == that.firstResistor &&
            this.secondResistor == that.secondResistor;
    }

    /**
     * Converts the voltage divider to a string.
     * @return A string representing the voltage divider.
     */
    @Override
    public String toString() {
        return String.format("Nominal Gain : %.5f, Actual Gain : %.5f", nominalGain, actualGain);
    }
}
