import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a band.
 */
public class Band {
    String color;
    Integer digit;
    Integer multiplier;
    Double tolerance;

    /**
     * Class constructor.
     * @param color A string representing the color of the band. Cannot be null.
     * @param digit An integer representing the digit of the band. Can be null.
     * @param multiplier An integer representing the multiplier of the band. Can be null.
     * @param tolerance A double representing the tolerance of the band. Can be null.
     */
    public Band(String color, Integer digit, Integer multiplier, Double tolerance) {
        Objects.requireNonNull(color);
        this.color = color;
        this.digit = digit;
        this.multiplier = multiplier;
        this.tolerance = tolerance;
    }

    /**
     * Creates an array list of band colors and their values.
     * @return An array list containing band colors and their values.
     */
    // Band colors and values are manually created due to them being provided in the project instructions.
    public static ArrayList<Band> createBandArray() {
        ArrayList<Band> bandTable = new ArrayList<>();
        bandTable.add(new Band("Black", 0, 0, null));
        bandTable.add(new Band("Brown", 1, 1, 1.0));
        bandTable.add(new Band("Red", 2, 2, 2.0));
        bandTable.add(new Band("Orange", 3, 3, null));
        bandTable.add(new Band("Yellow", 4, 4, null));
        bandTable.add(new Band("Green", 5, 5, 0.5));
        bandTable.add(new Band("Blue", 6, 6, 0.25));
        bandTable.add(new Band("Violet", 7, 7, 0.1));
        bandTable.add(new Band("Gray", 8, 8, 0.05));
        bandTable.add(new Band("White", 9, 9, null));
        bandTable.add(new Band("Gold", null, -1, 5.0));
        bandTable.add(new Band("Silver", null, -2, 10.0));
        bandTable.add(new Band("None", null, null, 20.0));

        return bandTable;
    }
}
