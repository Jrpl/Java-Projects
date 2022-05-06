import java.util.ArrayList;

/**
 * Represents a serial apart of a circuit.
 */
public class Serial extends Circuit{
    private double totalResistance;
    private ArrayList<Circuit> serialCircuit = new ArrayList<>();

    /**
     * Class constructor.
     */
    public Serial() {
        super();
    }

    /**
     * Retrieves the serial's resistance.
     * @return A double representing the resistance of the serial.
     */
    public double getResistance(){
        calcTotalResist();
        return this.totalResistance;
    }

    /**
     * Calculates the total resistance of the serial.
     */
    private void calcTotalResist() {
        double combinedResistance = 0;

        // Loop through circuits in serial circuit using forEach
        for (Circuit circuit : serialCircuit) {
            // Get resistance of resistor
            var resistance = circuit.getResistance();

            // Add up resistances to get Rt
            combinedResistance += resistance;
        }

        this.totalResistance = combinedResistance;
    }

    /**
     * Appends the circuit passed in to the end of the serial list.
     * @param circuit An object representing a circuit.
     */
    public void add(Circuit circuit) {
        serialCircuit.add(circuit);
    }
}
