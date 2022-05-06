import java.util.Objects;

/**
 * Represents a currency.
 */
public class Currency {
    private final String country;
    private final String currency;
    private final String code;
    private double convertedAmount;
    private final double rate;
    private final String start;
    private final String end;

    /**
     * Class constructor.
     * @param country A string representing the name of a country.
     * @param currency A string representing the name of a currency.
     * @param code A string representing a currency code.
     * @param rate A double representing an exchange rate.
     * @param start A string representing a starting date.
     * @param end A string representing an end date.
     */
    public Currency(String country, String currency, String code, double rate, String start, String end) {
        this.country = Objects.requireNonNull(country, "");
        this.currency = Objects.requireNonNull(currency, "");
        this.code = code;
        this.rate = rate;
        this.start = Objects.requireNonNull(start, "");
        this.end = Objects.requireNonNull(end, "");
    }

    /**
     * Retrieves a currency's country.
     * @return A string representing the country of the currency.
     */
    public String getCountry(){ return this.country; }
    /**
     * Retrieves a currency's currency.
     * @return A string representing the name of the currency.
     */
    public String getCurrency(){ return this.currency; }
    /**
     * Retrieves a currency's currency code.
     * @return A string representing the code of the currency.
     */
    public String getCode(){ return this.code; }
    /**
     * Retrieves a currency's converted amount.
     * @return A double representing the resulting amount of money after it has been converted using the exchange rate.
     */
    public double getConvertedAmount(){ return this.convertedAmount; }
    /**
     * Retrieves a currency's exchange rate.
     * @return A double representing the exchange rate.
     */
    public double getRate(){ return this.rate; }
    /**
     * Retrieves a currency's start date.
     * @return A string representing the start date of the exchange rate.
     */
    public String getStart(){ return this.start; }
    /**
     * Retrieves a currency's end date.
     * @return A string representing the end date of the exchange rate.
     */
    public String getEnd(){ return this.end; }

    /**
     * Sets a currency's converted amount.
     * @param convertedAmount A double representing the resulting amount of money after it has been converted using the exchange rate.
     */
    public void setConvertedAmount(double convertedAmount){ this.convertedAmount = convertedAmount; }

    /**
     * Checks if the current currency is equal to the currency passed in.
     * @param obj The currency to check against.
     * @return True if both currencies are the same. False if the currencies are different.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Currency that)) return false;
        return Objects.equals(this.code, that.code);
    }

    /**
     * Generates a hash code for the currency.
     * @return An integer representing the hash code of the currency.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.code);
    }

    /**
     * Converts the currency to a string.
     * @return A string representing the currency.
     */
    @Override
    public String toString() {
        return "Currency{" + "country='" + this.country +
                ", currency='" + this.currency +
                ", code='" + this.code +
                ", rate=" + this.rate +
                ", start=" + this.start +
                ", end=" + this.end +
                '}';
    }
}
