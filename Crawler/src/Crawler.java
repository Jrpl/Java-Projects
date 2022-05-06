import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a crawler.
 */
public class Crawler {
    private final Queue<String> urlQueue;
    private final List<String> visitedURLs;

    /**
     * Class constructor.
     */
    public Crawler() {
        urlQueue = new LinkedList<>();
        visitedURLs = new ArrayList<>();
    }

    /**
     * Searches web pages to find a page containing the given word.
     * @param rootURL A string representing the url to begin the search from.
     * @param word A string representing the word or words to search for.
     */
    public void crawl(String rootURL, String word) {
        urlQueue.add(rootURL);

        while(!urlQueue.isEmpty()) {
            String nextURL = urlQueue.remove();
            visitedURLs.add(nextURL);
            System.out.println("Checking " + nextURL);
            StringBuilder html = new StringBuilder();

            try {
                URL url = new URL(nextURL);
                BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
                String inputLine = in.readLine();

                while(inputLine != null) {
                    html.append(inputLine);
                    inputLine = in.readLine();
                }
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (html.toString().contains(word)) {
                System.out.println("The URL " + nextURL + " contains the word(s) " + word);
                System.out.println("The number of pages searched was " + visitedURLs.size());
                break;
            } else {
                extractURLs(html.toString());
            }
        }
    }

    /**
     * Retrieves urls from a given web page.
     * @param html A string representing the html content of a web page.
     */
    public void extractURLs(String html) {
        String regex = "\\b((?:https?|ftp|file):" + "//[-a-zA-Z\\d+&@#/%?=" + "~_|!:, .;]*[-a-zA-Z\\d+" + "&@#/%=~_|])";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(html);

        while (matcher.find()) {
            String newURL = html.substring(matcher.start(0), matcher.end(0));
            if (!visitedURLs.contains(newURL)) {
                urlQueue.add(newURL);
            }
        }
    }

    /**
     * Checks if the current crawler is equal to the crawler passed in.
     * @param obj The crawler to check against.
     * @return True if both crawlers are the same. False if the crawlers are different.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Crawler that)) return false;
        return Objects.equals(this.urlQueue, that.urlQueue);
    }

    /**
     * Generates a hash code for the crawler.
     * @return An integer representing the hash code of the crawler.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.urlQueue);
    }

    /**
     * Converts the crawler to a string.
     * @return A string representing the crawler.
     */
    @Override
    public String toString() {
        var builder = new StringBuilder();
        for (var url : this.urlQueue) {
            builder.append(url).append(System.lineSeparator());
        }
        return builder.toString();
    }
}
