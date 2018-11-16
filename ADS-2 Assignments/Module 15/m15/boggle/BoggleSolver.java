import java.util.HashSet;
/**
 * Class for boggle solver.
 */
public class BoggleSolver {
    /**
     * { Input dictionay }.
     */
    private TrieST inputdict;

    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)

    /**
     * Constructs the object.
     *
     * @param      dictionary  The dictionary
     */
    public BoggleSolver(final String[] dictionary) {
        inputdict = new TrieST();
        for (int i = 0; i < dictionary.length; i++) {
            inputdict.add(dictionary[i]);
        }
        // validwords = new HashSet<>();
    }
    // Returns the set of all valid words in the given Boggle board, as an Iterable.

    /**
     * Gets all valid words.
     *
     * @param      board  The board
     *
     * @return     All valid words.
     */
    public Iterable<String> getAllValidWords(final BoggleBoard board) {
        HashSet<String> validwords = new HashSet<>();
        for (int i = 0; i < board.rows(); i++) {
            for (int j = 0; j < board.cols(); j++) {
                boolean[][] marked = new boolean[board.rows()][board.cols()];
                String word = "";
                dfs(board, marked, i, j, word, validwords);
            }
        }
        return validwords;
    }
    /**
     * { dfs function }.
     *
     * @param      board    The board
     * @param      visited  The visited
     * @param      row      The row
     * @param      col      The col
     * @param      str      The string
     * @param      words    The words
     */
    private void dfs(final BoggleBoard board,
                     final boolean[][] visited,
                     final int row, final int col,
                     final String str,
                     final HashSet<String> words) {
        String teststr = str;
        if (visited[row][col]) {
            return;
        }
        // Iterable<String> results = inputdict.keysWithPrefix(str);
        if (!inputdict.hasPrefix(teststr)) {
            return;
        }
        char letter = board.getLetter(row, col);
        if (letter == 'Q') {
            teststr = teststr + "QU";
        } else {
            teststr = teststr + letter;
        }
        if (teststr.length() > 2 && inputdict.contains(teststr)) {
            words.add(teststr);
        }
        visited[row][col] = true;
        for (int rownew = -1; rownew <= 1; rownew++) {
            for (int k = -1; k <= 1; k++) {
                if ((k == 0 && rownew == 0)
                        || row + rownew < 0
                        || col + k < 0 || row + rownew >= board.rows()
                        || col + k >= board.cols()) {
                    continue;
                }
                dfs(board, visited,
                    row + rownew,
                    col + k, teststr,
                    words);
            }
        }
        visited[row][col] = false;
    }

    // Returns the score of the given word
    // if it is in the dictionary,
    // zero otherwise.
    // (You can assume the word contains
    //  only the uppercase letters A
    //  through Z.)

    /**
     * { scoreof }.
     *
     * @param      word  The word
     *
     * @return     { description_of_the_return_value }
     */
    public int scoreOf(final String word) {
        final int three = 3;
        final int four = 4;
        final int five = 5;
        final int six = 6;
        final int seven = 7;
        final int eleven = 11;
        if (!inputdict.contains(word)) {
            return 0;
        }
        int wordlen = word.length();
        switch (wordlen) {
        case 0:
        case 1:
        case 2: return 0;
        case three:
        case four: return 1;
        case five: return 2;
        case six: return three;
        case seven: return five;
        default: return eleven;
        }
    }
}