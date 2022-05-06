// import java.util.Scanner;

public class Main {
    /**
     * Displays the nominal resistance, tolerance, actual resistance, and band colors of a resistor with a resistance of 330 and a tolerance of 10.
     * Displays the nominal gain and actual gain of two resistors. The first having 250 resistance and 5 tolerance and the second having 750 resistance and 5 tolerance.
     */
    public static void main(String[] args) {
        final int NUMBER_OF_RESISTORS = 10;

        var resistance = 330.0;
        var tolerance = 10.0;
        for (var resistorCounter = 1; resistorCounter <= NUMBER_OF_RESISTORS; resistorCounter++) {
            var resistor = new Resistor(resistance, tolerance);
            System.out.printf("%d %s%n", resistorCounter, resistor);
        }

        /* Input code used in first sample run
        Scanner in = new Scanner(System.in);

        System.out.print("Resistance: ");
        resistance = in.nextDouble();
        System.out.print("Tolerance in percent: ");
        tolerance = in.nextDouble();
        var resistor = new Resistor(resistance, tolerance);
        System.out.println(resistor.colorBands());
         */

        final int NUMBER_OF_DIVIDERS = 10;

        for (var dividerCounter = 1; dividerCounter <= NUMBER_OF_DIVIDERS; dividerCounter++) {
            var firstResistor = new Resistor(250, 5);
            var secondResistor = new Resistor(750, 5);
            var divider = new VoltageDivider(firstResistor, secondResistor);
            System.out.println(divider);
        }
    }
}
