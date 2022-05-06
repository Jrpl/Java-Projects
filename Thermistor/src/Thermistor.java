import java.util.Scanner;

public class Thermistor {

    /**
     * Prompts the user for a resistance that is taken as a double.
     * Calculates the temperature of the substance in the pipe clip in Kelvin.
     * Converts the temperature to Celsius and Fahrenheit.
     * Formats and displays the temperatures.
     */
    public static void main(String[] args) {
        // Prompt the user for the resistance
        System.out.print("Enter the resistance > ");
        try (var scanner = new Scanner(System.in)) {
            var input = scanner.nextDouble();

            // Calculate the temperature in Kelvin
            var denom = (298.15 * (Math.log(input / 10000))) + 3960;
            var nume = 3960 * 298.15;
            var tempK = (nume / denom);

            // Convert the temperature from Kelvin to Celsius and Fahrenheit
            var tempC = tempK - 273.15;
            var tempF = (tempK - 273.15) * 9/5 + 32;

            // Format and display the temperatures
            System.out.printf(
                    "%-20s%.2f%n%-20s%.2f%n%-20s%.2f%n",
                    "Kelvin ", tempK,
                    "Celsius ", tempC,
                    "Fahrenheit ", tempF);
        }
    }
}
