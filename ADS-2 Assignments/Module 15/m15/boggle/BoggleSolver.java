import java.util.HashSet;
// import edu.princeton.cs.algs4.In;
// import edu.princeton.cs.algs4.StdRandom;
// import edu.princeton.cs.algs4.Queue;
public class BoggleSolver {
	private TrieST inputdict;
	// Initializes the data structure using the given array of strings as the dictionary.
	// (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
	public BoggleSolver(String[] dictionary) {
		inputdict = new TrieST();
		for (int i = 0; i < dictionary.length; i++) {
			inputdict.add(dictionary[i]);
		}
		// validwords = new HashSet<>();
	}
	// Returns the set of all valid words in the given Boggle board, as an Iterable.
	public Iterable<String> getAllValidWords(BoggleBoard board) {
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
	private void dfs(BoggleBoard board, boolean[][] visited, int row, int col, String str, HashSet<String> words) {
		if (visited[row][col]) {
			return;
		}
		// Iterable<String> results = inputdict.keysWithPrefix(str);
		if (!inputdict.hasPrefix(str)) {
			return;
		}
		char letter = board.getLetter(row, col);
		if (letter == 'Q') {
			str = str + "QU";
		} else {
			str = str + letter;
		}
		if (str.length() > 2 && inputdict.contains(str)) {
			words.add(str);
		}
		visited[row][col] = true;
		for (int rownew = -1; rownew <= 1; rownew++) {
			for (int k = -1; k <= 1; k++) {
				if ((k == 0 && rownew == 0) || row + rownew < 0 || col + k < 0 || row + rownew >= board.rows() || col + k >= board.cols()) {
					continue;
				}
				dfs(board, visited, row + rownew, col + k, str, words);
			}
		}
		visited[row][col] = false;
	}

	// Returns the score of the given word if it is in the dictionary, zero otherwise.
	// (You can assume the word contains only the uppercase letters A through Z.)
	public int scoreOf(String word) {
		// System.out.println(word + " each word ");
		if (!inputdict.contains(word)) {
			return 0;
		}
		int wordlen = word.length();
		switch (wordlen) {
		case 0:
		case 1:
		case 2: return 0;
		case 3:
		case 4: return 1;
		case 5: return 2;
		case 6: return 3;
		case 7: return 5;
		default: return 11;
		}
	}
}