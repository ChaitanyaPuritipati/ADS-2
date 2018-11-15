import java.util.TreeSet;
public class BoggleSolver {
	private TreeSet<String> inputdict;
	private Bag<String> validwords;
	// Initializes the data structure using the given array of strings as the dictionary.
	// (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
	public BoggleSolver(String[] dictionary) {
		inputdict = new TreeSet<>();
		 for(int i = 0; i < dictionary.length; i++) {
			inputdict.add(dictionary[i]);
		}
		validwords = new Bag<>();
	}
	// Returns the set of all valid words in the given Boggle board, as an Iterable.
	public Iterable<String> getAllValidWords(BoggleBoard board) {
		for (int i = 0; i < board.rows(); i++) {
			for (int j = 0; j < board.cols(); j++) {
				Boolean[][] marked = new Boolean[board.rows()][board.cols()];
				String word = "";
				dfs(board, marked, i, j, word);
			}
		}
		return validwords;
	}
	public void dfs(BoggleBoard board, Boolean[][] visited, int row, int col, String str) {
		System.out.println(visited[row][col] + "boolean");
		if(visited[row][col]) {
			return;
		}
		visited[row][col] = true;
		char letter = board.getLetter(row, col);
		if(letter == 'Q') {
			str = str + "Qu";
		} else {
			str = str + letter;
		}
		if(str.length() > 2 && inputdict.contains(str)) {
			validwords.add(str);
		}
		for(int l = -1; l < 1; l++) {
			for(int k = -1; k < 1; k++) {
				dfs(board, visited, row + l, col + k, str);
			}
		}

	}

	// Returns the score of the given word if it is in the dictionary, zero otherwise.
	// (You can assume the word contains only the uppercase letters A through Z.)
	public int scoreOf(String word) {
		int wordlen = word.length();
		switch(wordlen) {
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