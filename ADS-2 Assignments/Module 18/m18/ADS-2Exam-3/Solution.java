import java.util.Scanner;
import java.util.HashMap;
import java.util.TreeSet;
/**
 * Class for solution.
 */
final class Solution {

    // Don't modify this method.

    /**
     * Constructs the object.
     */
    private Solution() {
        //unused.
    }

    /**
     * { Main method }.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        String cases = scan.nextLine();

        switch (cases) {
        case "loadDictionary":
            // input000.txt and output000.txt
            BinarySearchST<String, Integer> hash
                = loadDictionary(
                      "/Files/t9.csv");
            while (scan.hasNextLine()) {
                String key = scan.nextLine();
                System.out.println(hash.get(key));
            }
            break;

        case "getAllPrefixes":
            // input001.txt and output001.txt
            T9 t9 = new T9(loadDictionary("/Files/t9.csv"));
            while (scan.hasNextLine()) {
                String prefix = scan.nextLine();
                for (String each : t9.getAllWords(
                            prefix)) {
                    System.out.println(each);
                }
            }
            break;

        case "potentialWords":
            // input002.txt and output002.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            int count = 0;
            while (scan.hasNextLine()) {
                String t9Signature = scan.nextLine();
                for (String each : t9.potentialWords(
                            t9Signature)) {
                    count++;
                    System.out.println(each);
                }
            }
            if (count == 0) {
                System.out.println(
                    "No valid words found.");
            }
            break;

        case "topK":
            // input003.txt and output003.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            Bag<String> bag = new Bag<String>();
            int k = Integer.parseInt(scan.nextLine());
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                bag.add(line);
            }
            for (String each : t9.getSuggestions(
                        bag, k)) {
                System.out.println(each);
            }

            break;

        case "t9Signature":
            // input004.txt and output004.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            bag = new Bag<String>();
            k = Integer.parseInt(scan.nextLine());
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                for (String each : t9.t9(line, k)) {
                    System.out.println(each);
                }
            }
            break;

        default:
            break;

        }
    }

    // Don't modify this method.

    /**
     * { function_description }.
     *
     * @param      file  The file
     *
     * @return     { description_of_the_return_value }
     */
    public static String[] toReadFile(
        final String file) {
        In in = new In(file);
        return in.readAllStrings();
    }

    /**
     * Loads a dictionary.
     *
     * @param      file  The file
     *
     * @return     { description_of_the_return_value }
     */
    public static BinarySearchST<String, Integer> loadDictionary(
        final String file) {
        BinarySearchST<String, Integer>  st
            = new BinarySearchST<String, Integer>();
        // your code goes here
        String[] lines = toReadFile(file);
        for (String line : lines) {
            String[] words = line.split(" ");
            for (String word : words) {
                word = word.toLowerCase();
                if (st.contains(word)) {
                    int value = st.get(word);
                    value = value + 1;
                    st.put(word, value);
                } else {
                    st.put(word, 1);
                }
            }
        }
        return st;
    }

}
/**
 * Class for t 9.
 */
class T9 {
    /**
     * { var_description }.
     */
    private TST<Integer> tstobj;
    /**
     * { var_description }.
     */
    private HashMap<String, Integer> mapobj;
    /**
     * Constructs the object.
     *
     * @param      st    { parameter_description }
     */
    protected T9(final BinarySearchST<String, Integer> st) {
        // your code goes here
        tstobj = new TST<>();
        mapobj = new HashMap<>();
        for (String word : st.keys()) {
            tstobj.put(word, st.get(word));
        }
        String aplhabets = "abcdefghijklmnopqrstuvwxyz";
        String[] letters = aplhabets.split("");
        for (String letter : letters) {
            switch (letter) {
            case "a": mapobj.put(letter, 2);
                break;
            case "b": mapobj.put(letter, 2);
                break;
            case "c": mapobj.put(letter, 2);
                break;
            case "d": mapobj.put(letter, 2 + 1);
                break;
            case "e": mapobj.put(letter, 2 + 1);
                break;
            case "f": mapobj.put(letter, 2 + 1);
                break;
            case "g": mapobj.put(letter, 2 + 2);
                break;
            case "h": mapobj.put(letter, 2 + 2);
                break;
            case "i": mapobj.put(letter, 2 + 2);
                break;
            case "j": mapobj.put(letter, 2 + 2 + 1);
                break;
            case "k": mapobj.put(letter, 2 + 2 + 1);
                break;
            case "l": mapobj.put(letter, 2 + 2 + 1);
                break;
            case "m": mapobj.put(letter, 2 + 2 + 2);
                break;
            case "n": mapobj.put(letter, 2 + 2 + 2);
                break;
            case "o": mapobj.put(letter, 2 + 2 + 2);
                break;
            case "p": mapobj.put(letter, 2 + 2 + 2 + 1);
                break;
            case "q": mapobj.put(letter, 2 + 2 + 2 + 1);
                break;
            case "r": mapobj.put(letter, 2 + 2 + 2 + 1);
                break;
            case "s": mapobj.put(letter, 2 + 2 + 2 + 1);
                break;
            case "t": mapobj.put(letter, 2 + 2 + 2 + 2);
                break;
            case "u": mapobj.put(letter, 2 + 2 + 2 + 2);
                break;
            case "v": mapobj.put(letter, 2 + 2 + 2 + 2);
                break;
            case "w": mapobj.put(letter, 2 + 2 + 2 + 2 + 1);
                break;
            case "x": mapobj.put(letter, 2 + 2 + 2 + 2 + 1);
                break;
            case "y": mapobj.put(letter, 2 + 2 + 2 + 2 + 1);
                break;
            case "z": mapobj.put(letter, 2 + 2 + 2 + 2 + 1);
                break;
            default:
            }
        }
    }

    // get all the prefixes that match with given prefix.

    /**
     * Gets all words.
     *
     * @param      prefix  The prefix
     *
     * @return     All words.
     */
    public Iterable<String> getAllWords(final String prefix) {
        // your code goes here
        return tstobj.keysWithPrefix(prefix);
    }

    /**
     * { function_description }.
     *
     * @param      t9Signature  The t 9 signature
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<String> potentialWords(final String t9Signature) {
        // your code goes here
        HashMap<String, TreeSet<String>> matchmap = new HashMap<>();
        for (String key : tstobj.keys()) {
            // System.out.println(key + "keyval");
            String[] keyletters = key.split("");
            String pattern = "";
            for (String keyletter : keyletters) {
                pattern = pattern + mapobj.get(keyletter);
            }
            if (matchmap.containsKey(pattern)) {
                TreeSet presentwords = matchmap.get(pattern);
                presentwords.add(key);
                matchmap.put(pattern, presentwords);
            } else {
                TreeSet<String> presentwords = new TreeSet<>();
                presentwords.add(key);
                matchmap.put(pattern, presentwords);
            }
        }
        if (matchmap.get(t9Signature) != null) {
            return matchmap.get(t9Signature);
        }
        return new Bag<String>();
    }

    // return all possibilities(words), find top k with highest frequency.

    /**
     * Gets the suggestions.
     *
     * @param      words  The words
     * @param      k      { parameter_description }
     *
     * @return     The suggestions.
     */
    public Iterable<String> getSuggestions(
        final Iterable<String> words,
        final int k) {
        // your code goes here
        MaxPQ<Integer> pqobj = new MaxPQ<>();
        HashMap<Integer, TreeSet<String>> suggestwords = new HashMap<>();
        String[] topwords = new String[k];
        for (String word : words) {
            pqobj.insert(tstobj.get(word));
            if (suggestwords.containsKey(tstobj.get(word))) {
                TreeSet getwords = suggestwords.get(tstobj.get(word));
                getwords.add(word);
                suggestwords.put(tstobj.get(word), getwords);
            } else {
                TreeSet<String> getwords = new TreeSet<>();
                getwords.add(word);
                suggestwords.put(tstobj.get(word), getwords);
            }
        }
        TreeSet<String> resultwords = new TreeSet<String>();
        int count = 0;
        while (count != k) {
            if (pqobj.isEmpty()) {
                return resultwords;
            }
            int val = pqobj.delMax();
            // System.out.println(val);
            for (String result : suggestwords.get(val)) {
                if (count == k) {
                    return resultwords;
                }
                // System.out.println(result + "word" + val +"value");
                resultwords.add(result);
                count++;
            }
        }
        return resultwords;
    }

    // final output
    // Don't modify this method.

    /**
     * { function_description }.
     *
     * @param      t9Signature  The t 9 signature
     * @param      k            { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<String> t9(final String t9Signature, final int k) {
        return getSuggestions(potentialWords(t9Signature), k);
    }
}
