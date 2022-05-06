import java.util.ArrayList;

/**
 * Represents a parallel apart of a circuit.
 */
public class Parallel extends Circuit{
    private double totalResistance;
    private ArrayList<Circuit> parallelCircuit = new ArrayList<>();

    /**
     * Class constructor.
     */
    public Parallel() {
        super();
    }

    /**
     * Retrieves the parallel's resistance.
     * @return A double representing the resistance of the parallel.
     */
    public double getResistance(){
        calcTotalResist();
        return this.totalResistance;
    }

    /**
     * Calculates the total resistance of the parallel.
     */
    private void calcTotalResist() {
        ArrayList<Double> resistanceList = new ArrayList<>();
        double combinedResistance = 0;

        // Loop through circuits in parallel circuit using forEach
        for (Circuit circuit : parallelCircuit) {
            // Get resistance of resistor
            var resistance = circuit.getResistance();

            // Get 1/Resistance to use in Ohm's law
            var oneOverResist = 1 / resistance;

            // Add value to resistance list
            resistanceList.add(oneOverResist);
        }

        // Loop through resistances using forEach
        for (double resistance : resistanceList) {
            // Add up resistances before dividing 1 in Ohm's law
            combinedResistance += resistance;
        }

        this.totalResistance = 1 / combinedResistance;
    }

    /**
     * Appends the circuit passed in to the end of the parallel list.
     * @param circuit An object representing a circuit.
     */
    public void add(Circuit circuit) {
        parallelCircuit.add(circuit);
    }
}
