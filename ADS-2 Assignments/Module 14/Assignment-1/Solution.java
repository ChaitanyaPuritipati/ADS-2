import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		String[] words = loadWords();
		//Your code goes here...
		TST<Integer> tstobj = new TST<>();
		for (int i = 0; i < words.length; i++) {
			tstobj.put(words[i], i);
		}
		In testin = new In();
		String prefix = testin.readLine();
		for (String eachkey : tstobj.keysWithPrefix(prefix)) {
			System.out.println(eachkey);
		}
	}

	public static String[] loadWords() {
		In in = new In("/Files/dictionary-algs4.txt");
		String[] words = in.readAllStrings();
		return words;
	}
}