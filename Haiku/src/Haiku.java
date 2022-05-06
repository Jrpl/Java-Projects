import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

public class Haiku {
    /**
     * Prints the passed prompt.
     * Retrieves the user's next input using the passed reader.
     * Retrieves each word in the line and adds it to the passed input HashMap.
     * @param reader
     * @param prompt
     * @param input
     */
    public static void printPrompt(Scanner reader, String prompt, HashMap<String, Integer> input) {
        System.out.print(prompt);
        String line = reader.nextLine();
        try (var lineScanner = new Scanner(line)) {
            var i = 0;
            while (lineScanner.hasNext()) {
                input.put(lineScanner.next(), i);
                i++;
            }
        }
    }

    /**
     * Loops through the input HashMap and retrieves the word and syllableCount pair at each entry.
     * Formats and prints the word and syllableCount pairs.
     * Formats and prints the totalSyllables for that input.
     * @param input
     * @param totalSyllables
     */
    public static void printSyllableCount(HashMap<String, Integer> input, Integer totalSyllables) {
        for(Map.Entry<String, Integer> entry : input.entrySet()) {
            String word = entry.getKey();
            Integer syllableCount = entry.getValue();
            System.out.print(word + "(" + syllableCount + ") ");
        }
        System.out.print("Line total - " + totalSyllables + "\n");
    }

    /**
     * Loops through the input HashMap and returns the total number of syllables across all keys.
     * @param input
     * @return count
     */
    public static int updateTotalCount(HashMap<String, Integer> input) {
        Integer count = 0;

        for(Map.Entry<String, Integer> entry : input.entrySet()) {
            Integer value = entry.getValue();
            count += value;
        }

        return count;
    }

    /**
     * Loops through the input HashMap and updates the values at each key with the number of syllables.
     * @param input
     */
    public static void updateSyllableCount(HashMap<String, Integer> input) {
        for(Map.Entry<String, Integer> entry : input.entrySet()) {
            String key = entry.getKey();
            Integer numSyllables = countSyllables(key);
            input.put(key, numSyllables);
        }
    }

    /**
     * Counts the number of syllables of the given word
     * @param word
     * @return numSyllables
     */
    public static int countSyllables(String word) {
        int numSyllables = 0;
        word = word.toLowerCase();
        ArrayList<Character> vowels = new ArrayList<>();
        vowels.add('a'); vowels.add('e'); vowels.add('i'); vowels.add('o'); vowels.add('u'); vowels.add('y');

        // My attempt at trimming punctuation without using regex
        /*
        for (int i = 0; i < word.length(); i++) {
            Character curr = word.charAt(i);
            if (!Character.isLetter(curr)) {
                word.replace(curr, ' ');
            }
        }
        */

        if (word.length() == 1 && vowels.contains(word.charAt(0))) {
            numSyllables++;
        } else {
            for (int i = 1; i < word.length() - 1; i++) {
                char curr = word.charAt(i);
                char prev = word.charAt(i - 1);
                char next = word.charAt(i + 1);

                if (word.length() == 2 && vowels.contains(curr) && vowels.contains(next)) {
                    numSyllables++;
                }
                else if (vowels.contains(curr) && vowels.contains(next)) {
                    numSyllables++;
                }
                else if (i == 1 && vowels.contains(prev)) {
                    numSyllables++;
                }
                // This check does not work, supposed to only count vowels that are not 'e' at the end of a word
                else if (i == word.length() - 1 && curr != 'e') {
                    numSyllables++;
                }
                else if (vowels.contains(curr)) {
                    numSyllables++;
                }
            }
        }

        return numSyllables;
    }

    /**
     * Prompts the user for 3 sentences.
     * Determines if the 3 sentences entered are a valid Haiku.
     * Displays if the Haiku is valid or not.
     */
    public static void main(String[] args) {
        HashMap<String,Integer> inputOne = new HashMap<>();
        HashMap<String,Integer> inputTwo = new HashMap<>();
        HashMap<String,Integer> inputThree = new HashMap<>();
        Integer totalSyllablesOne = 0;
        Integer totalSyllablesTwo = 0;
        Integer totalSyllablesThree = 0;
        String promptOne = "Enter line one > ";
        String promptTwo = "Enter line two > ";
        String promptThree = "Enter line three > ";

        var reader = new Scanner(System.in);
        printPrompt(reader, promptOne, inputOne); printPrompt(reader, promptTwo, inputTwo); printPrompt(reader, promptThree, inputThree);
        updateSyllableCount(inputOne); updateSyllableCount(inputTwo); updateSyllableCount(inputThree);
        totalSyllablesOne = updateTotalCount(inputOne); totalSyllablesTwo = updateTotalCount(inputTwo); totalSyllablesThree = updateTotalCount(inputThree);
        printSyllableCount(inputOne, totalSyllablesOne); printSyllableCount(inputTwo, totalSyllablesTwo); printSyllableCount(inputThree, totalSyllablesThree);

        Integer totalSyllables = totalSyllablesOne + totalSyllablesTwo + totalSyllablesThree;
        if(totalSyllablesOne == 5 && totalSyllablesTwo == 7 && totalSyllablesThree == 5) {
            System.out.println("Total Syllables " + totalSyllables + " - Valid Hiaku");
        } else {
            System.out.println("Total Syllables " + totalSyllables + " - Not a valid Hiaku");
        }
    }
}
