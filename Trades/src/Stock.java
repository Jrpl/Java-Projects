import java.text.DecimalFormat;

public class Stock {
    String symbol;
    double price;
    int quantity;

    public Stock(String symbol, double price, int quantity) {
        this.symbol = symbol;
        DecimalFormat df = new DecimalFormat("#.00");
        this.price = Double.valueOf(df.format(price));
        this.quantity = quantity;
    }

    public void printStock() {
        System.out.println("You own " + quantity + " shares of " + symbol + " bought at $" + price + " each.");
    }

    public void setQuantity(int newQuantity) {
        this.quantity = newQuantity;
    }
}
