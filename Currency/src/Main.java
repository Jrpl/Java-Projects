import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        CurrencyList rates = CurrencyList.load();
        Scanner reader = new Scanner(System.in);
        Menu menu = new Menu(reader, rates);
        menu.getInput();
    }
}
