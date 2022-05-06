/**
 * Represents a resistor apart of a circuit.
 */
public class Resistor extends Circuit{
    /**
     * Class constructor.
     * @param resistance A double representing the resistance of the resistor.
     */
    public Resistor(double resistance) {
        super(resistance);
    }

    /**
     * Retrieves the resistor's resistance.
     * @return A double representing the resistance of the resistor.
     */
    public double getResistance(){
        return resistance;
    }
}
