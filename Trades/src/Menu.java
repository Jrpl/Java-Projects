import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;

public class Menu {
    List<Stock> boughtStocks = new ArrayList<>();
    Scanner reader;
    ArrayList<String> options = new ArrayList<>(Arrays.asList("b", "s", "p", "e"));
    boolean exit = false;

    public Menu(Scanner reader) {
        this.reader = reader;
    }

    public void getInput() {
        while (!exit) {
            System.out.println("B) Buy");
            System.out.println("S) Sell");
            System.out.println("P) Print");
            System.out.println("E) Exit");
            System.out.print("Enter an option > ");
            String input = this.reader.next();
            input = input.toLowerCase();

            if (!options.contains(input)) {
                System.out.println("Not a valid option, try again.");
            } else if (input.equals("e")) {
                System.out.println("BYE");
                exit = true;
            } else if (input.equals("b")) {
                String newSymbol = promptSymbol();
                double newPrice = promptPrice();
                int newQuantity = promptQuantity();
                Stock newStock = new Stock(newSymbol, newPrice, newQuantity);
                boughtStocks.add(newStock);
                displayStocks();
            } else if (input.equals("s")) {
                String sellSymbol = promptSellSymbol();
                double sellPrice = promptPrice();
                int sellQuantity = promptSellQuantity(sellSymbol);
                Stock sellStock = new Stock(sellSymbol, sellPrice, sellQuantity);
                sellCheck(sellStock);
            } else if (input.equals("p")) {
                displayStocks();
            }
        }
    }

    public String promptSymbol() {
        boolean validSymbol = false;
        String symbol = null;

        while (!validSymbol) {
            System.out.print("Enter stock symbol > ");
            symbol = reader.next().toUpperCase();

            if (symbol.matches("[a-zA-Z]+") && symbol.length() == 3) {
                validSymbol = true;
            } else {
                System.out.println("Invalid entry, try again.");
            }
        }

        return symbol;
    }

    public double promptPrice() {
        boolean validPrice = false;
        double price = 0.00;

        while (!validPrice) {
            System.out.print("Enter the price > ");
            try {
                price = Double.parseDouble(reader.next());
            } catch(Exception e) {
                System.out.println("Invalid entry, try again.");
                continue;
            }

            DecimalFormat df = new DecimalFormat("#.00");
            price = Double.parseDouble(df.format(price));

            if (price > 0.00) {
                validPrice = true;
            } else {
                System.out.println("Invalid entry, try again.");
            }
        }

        return price;
    }

    public int promptQuantity() {
        boolean validQuantity = false;
        int quantity = 0;

        while (!validQuantity) {
            System.out.print("Enter the quantity > ");
            try {
                quantity = Integer.parseInt(reader.next());
            } catch(Exception e) {
                System.out.println("Invalid entry, try again.");
                continue;
            }

            if (quantity > 0) {
                validQuantity = true;
            } else {
                System.out.println("Invalid entry, try again.");
            }
        }

        return quantity;
    }

    public String promptSellSymbol() {
        boolean validSymbol = false;
        String sellSymbol = null;
        ArrayList<String> ownedSymbols = new ArrayList<>();

        while (!validSymbol) {
            System.out.print("Enter stock symbol > ");
            sellSymbol = reader.next().toUpperCase();

            for (Stock ownedStock : boughtStocks) {
                if (ownedStock.symbol.equals(sellSymbol)) {
                    ownedSymbols.add(ownedStock.symbol);
                }
            }

            if (sellSymbol.matches("[a-zA-Z]+") && sellSymbol.length() == 3) {
                if (ownedSymbols.contains(sellSymbol)) {
                    validSymbol = true;
                } else {
                    System.out.println("No stock of " + sellSymbol + " has been bought.");
                }
            } else {
                System.out.println("Invalid entry, try again.");
            }
        }

        return sellSymbol;
    }

    public int promptSellQuantity(String sellSymbol) {
        boolean validQuantity = false;
        int sellQuantity = 0;
        int ownedQuantity = 0;

        for (Stock ownedStock : boughtStocks) {
            if (ownedStock.symbol.equals(sellSymbol)) {
                ownedQuantity += ownedStock.quantity;
            }
        }

        while (!validQuantity) {
            System.out.print("Enter the quantity > ");
            try {
                sellQuantity = Integer.parseInt(reader.next());
            } catch(Exception e) {
                System.out.println("Invalid entry, try again.");
                continue;
            }

            if (sellQuantity > 0 && sellQuantity <= ownedQuantity) {
                validQuantity = true;
            } else {
                System.out.println("Invalid quantity / not enough stock to sell");
            }
        }

        return sellQuantity;
    }

    public void displayStocks() {
        for (Stock stock : boughtStocks) {
            stock.printStock();
        }
    }

    public void sortStocks() {
        Collections.sort(boughtStocks, (o1, o2) -> Double.compare(o2.price, o1.price));
    }

    public void sellCheck(Stock sellStock) {
        sortStocks();
        int currentPosition = 0;
        double totalProfit = 0.00;

        while (sellStock.quantity > 0) {
            for (Stock ownedStock : boughtStocks) {
                double profitPerShare = sellStock.price - ownedStock.price;

                if (ownedStock.symbol.equals(sellStock.symbol)) {
                    if (sellStock.quantity < ownedStock.quantity) {
                        System.out.println("Selling " + sellStock.quantity + " shares at " + profitPerShare + " per share for a total of: $" + sellStock.quantity * profitPerShare);
                        int remainingQuantity = ownedStock.quantity - sellStock.quantity;
                        totalProfit += sellStock.quantity * profitPerShare;
                        sellStock.quantity = sellStock.quantity - ownedStock.quantity;
                        boughtStocks.get(currentPosition).setQuantity(remainingQuantity);
                    } else {
                        System.out.println("Selling " + ownedStock.quantity + " shares at " + profitPerShare + " per share for a total of: $" + ownedStock.quantity * profitPerShare);
                        sellStock.quantity = sellStock.quantity - ownedStock.quantity;
                        totalProfit += ownedStock.quantity * profitPerShare;
                        boughtStocks.remove(currentPosition);
                    }
                }
            }
        }

        System.out.println("Total profit: $" + totalProfit);
        System.out.println("Taxes due: $" + totalProfit * .15);
        displayStocks();
    }
}
