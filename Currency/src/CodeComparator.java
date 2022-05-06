import java.util.Comparator;

/**
 * Implements a comparator for currency
 */
public class CodeComparator implements Comparator<Currency> {
    /**
     * Compares two currencies.
     * @param c1 The first currency to be compared.
     * @param c2 The second currency to be compared.
     * @return An integer representing the comparison result.
     */
    @Override
    public int compare(Currency c1, Currency c2) {
        String code1 = c1.getCode();
        String code2 = c2.getCode();

        return code1.compareTo(code2);
    }
}
