import java.util.Objects;

/**
 * Represents a circuit.
 */
public abstract class Circuit {
    public double resistance;

    /**
     * Default class constructor.
     */
    public Circuit() { }

    /**
     * Class constructor.
     * @param resistance A double representing the resistance of the circuit.
     */
    public Circuit(double resistance) {
        this.resistance = resistance;
    }

    /**
     * Retrieves the circuit's resistance.
     * @return A double representing the resistance of the circuit.
     */
    abstract public double getResistance();

    /**
     * Generates a hash code for the circuit.
     * @return An integer representing the hash code of the circuit.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.resistance);
    }

    /**
     * Checks if the current circuit is equal to the circuit passed in.
     * @param obj The circuit to check against.
     * @return True if both circuits are the same. False if the circuits are different.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Resistor that)) return false;
        return this.resistance == that.resistance;
    }

    /**
     * Converts the circuit to a string.
     * @return A string representing the circuit.
     */
    @Override
    public String toString() {
        return String.format("%.1f", this.getResistance());
    }
}
