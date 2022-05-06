import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Represents a currency data access object.
 */
public class CurrencyDAO {
    private static final URL ADDRESS;

    static {
        try {
            ADDRESS = new URL("https://assets.publishing.service.gov.uk/government/uploads/system/uploads/attachment_data/file/1063393/exrates-monthly-0422.csv");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private static final String DELIMITER = ",";
    private static final String FILE_FORMAT = "%s%s %s%s %s%s %.4f%s %.4f%s %s%s %s";

    /**
     * Reads each line of the file.
     * @param line A string representing the current section of the file.
     * @return A currency representing the current line of the file.
     */
    private static Currency parse(String line) {
        var tokens = line.split(DELIMITER);
        return new Currency(
                tokens[0],
                tokens[1],
                tokens[2],
                Double.parseDouble(tokens[3]),
                tokens[4],
                tokens[5]
        );
    }

    /**
     * Formats and converts a currency to a string.
     * @param currency An object representing the currency we want to convert.
     * @return A string representing the currency.
     */
    private static String toFileString(Currency currency) {
        return String.format(
                FILE_FORMAT,
                currency.getCode(), DELIMITER,
                currency.getCountry(), DELIMITER,
                currency.getCurrency(), DELIMITER,
                currency.getConvertedAmount(), DELIMITER,
                currency.getRate(), DELIMITER,
                currency.getStart(), DELIMITER,
                currency.getEnd());
    }

    /**
     * Creates a file containing the currencies in a currency list in csv format.
     * @param currencies The currency list to save.
     * @param fileName The name of the file to save.
     */
    public static void save(CurrencyList currencies, String fileName) throws DAOException {
        try (var write = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".dat")))) {
            for (var index = 0; index < currencies.size(); index++) {
                write.println(toFileString(currencies.get(index)));
            }
        } catch (IOException iOEx) {
            throw new DAOException("Unable to save Currency List");
        }
    }

    /**
     * Retrieves the current exchange rate list from the specified url address.
     * @return A currency list containing the current exchange rates.
     */
    public static CurrencyList load() throws IOException {
        var currencies = new CurrencyList();
        BufferedReader in = new BufferedReader(new InputStreamReader(ADDRESS.openStream()));

        // Skip the first line
        in.readLine();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            currencies.add(parse(inputLine));
        }
        in.close();

        return currencies;
    }
}
