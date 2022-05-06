import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String rootURL;
        String word;
        Scanner reader = new Scanner(System.in);

        System.out.print("Enter a URL to start the search from: ");
        rootURL = reader.next();
        reader.nextLine();
        System.out.print("Enter the word(s) to search: ");
        word = reader.nextLine();

        Crawler crawler = new Crawler();
        crawler.crawl(rootURL, word);
    }
}
