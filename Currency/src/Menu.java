import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

/**
 * Represents a menu.
 */
public class Menu {
    CurrencyList currencies;
    Scanner reader;
    boolean exit = false;

    /**
     * Class constructor.
     * @param reader Represents a scanner object.
     * @param currencies Represents a currency list object.
     */
    public Menu(Scanner reader, CurrencyList currencies) { this.reader = reader; this.currencies = currencies; }

    /**
     * Starts the menu.
     */
    public void getInput() {
        while(!exit) {
            double amount = promptAmount();
            Currency currency = promptCountry();
            showResults(amount, currency);
        }
    }

    /**
     * Prompts the user for the amount of money to be converted.
     * @return A double representing the amount of money to be converted.
     */
    public double promptAmount() {
        double amount = 0;
        boolean validAmount = false;

        while (!validAmount) {
            System.out.print("Please enter the amount of UK Pounds you wish to convert: ");
            String value = this.reader.next();
            try {
                amount = Double.parseDouble(value);
            } catch(Exception e) {
                throw new RuntimeException(e);
            }

            if (amount > 0) {
                validAmount = true;
            }

            if (!validAmount) {
                System.out.println("Please enter a valid currency value.");
            }
        }

        return amount;
    }

    /**
     * Prompts the user for the country to convert the money to.
     * @return An object representing the currency information of the country.
     */
    public Currency promptCountry() {
        Currency currency = new Currency("", "", "", 0, "", "");
        boolean validCountry = false;

        while (!validCountry) {
            System.out.print("Please enter the country for which you would like to exchange the currency: ");
            String country = this.reader.next().toLowerCase();

            for (var index = 0; index < currencies.size(); index++) {
                Currency newCurrency = currencies.get(index);

                if (newCurrency.getCountry().toLowerCase().equals(country)) {
                    currency = newCurrency;
                    validCountry = true;
                }
            }

            if (!validCountry) {
                System.out.println("Please enter a valid country.");
            }
        }

        return currency;
    }

    /**
     * Displays the results of after converting the amount input to the currency input.
     * @param amount A double representing the amount of money to be converted.
     * @param currency An object representing the currency to convert the money to.
     */
    public void showResults(double amount, Currency currency) {
        System.out.println("Your new currency is: " + currency.getCurrency());
        System.out.println("Your original amount entered was " + amount + " pounds");

        double resultAmount = amount * currency.getRate();
        NumberFormat formatter = new DecimalFormat("#0.0000");
        String formattedAmount = formatter.format(resultAmount);

        System.out.println("Your currency based on the current rate of " + currency.getRate() + " is " + formattedAmount + " " + currency.getCurrency());
        System.out.println("This currency rate is valid from " + currency.getStart() + " through " + currency.getEnd());
        System.out.println("Would you like to export your results for all currencies to a csv file? Y or N");
        String answer = this.reader.next().toLowerCase();

        if (answer.equals("y")) {
            System.out.print("Please enter a name for the file: ");
            String fileName = this.reader.next();

            CurrencyList converted = new CurrencyList();
            for (var index = 0; index < currencies.size(); index++) {
                Currency newCurrency = currencies.get(index);
                newCurrency.setConvertedAmount(amount * newCurrency.getRate());
                converted.add(newCurrency);
            }

            converted.getList().sort(new CodeComparator());

            converted.save(fileName);
        }

        exit = true;
    }
}
