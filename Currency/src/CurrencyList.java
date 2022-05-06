import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of currencies
 */
public class CurrencyList {
    private final List<Currency> currencies = new ArrayList<>();
    public void add(Currency currency) { this.currencies.add(currency); }
    public int size() { return this.currencies.size(); }
    public Currency get(int index) { return this.currencies.get(index); }
    public List<Currency> getList() { return this.currencies; }

    /**
     * Creates a file in the directory containing all the currencies with the conversions.
     * @param fileName The name of the file.
     */
    public void save(String fileName) {
        try {
            CurrencyDAO.save(this, fileName);
        } catch (DAOException e) {
            System.out.println(fileName + ".dat failed to save");
        }
    }

    /**
     * Creates a currency list with the current exchange rates.
     * @return Calls the load method to retrieve the current exchange rates.
     */
    public static CurrencyList load() throws Exception {
        return CurrencyDAO.load();
    }

    /**
     * Converts the currency list to a string.
     * @return A string representing the currency list.
     */
    @Override
    public String toString() {
        var builder = new StringBuilder();
        for (var currency : this.currencies) {
            builder.append(currency).append(System.lineSeparator());
        }
        return builder.toString();
    }
}
