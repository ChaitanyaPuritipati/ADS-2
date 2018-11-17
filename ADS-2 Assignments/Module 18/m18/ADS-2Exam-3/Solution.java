import java.util.Scanner;
import java.util.HashMap;
import java.util.TreeSet;

public class Solution {

	// Don't modify this method.
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String cases = scan.nextLine();

		switch (cases) {
		case "loadDictionary":
			// input000.txt and output000.txt
			BinarySearchST<String, Integer> hash = loadDictionary("/Files/t9.csv");
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
				for (String each : t9.getAllWords(prefix)) {
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
				for (String each : t9.potentialWords(t9Signature)) {
					count++;
					System.out.println(each);
				}
			}
			if (count == 0) {
				System.out.println("No valid words found.");
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
			for (String each : t9.getSuggestions(bag, k)) {
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
	public static String[] toReadFile(String file) {
		In in = new In(file);
		return in.readAllStrings();
	}

	public static BinarySearchST<String, Integer> loadDictionary(String file) {
		BinarySearchST<String, Integer>  st = new BinarySearchST<String, Integer>();
		// your code goes here
		String[] lines = toReadFile(file);
		for(String line : lines) {
			String[] words = line.split(" ");
			for(String word : words) {
				word = word.toLowerCase();
				if(st.contains(word)) {
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

class T9 {
	private TST<Integer> tstobj;
	private HashMap<String, Integer> mapobj; 
	public T9(BinarySearchST<String, Integer> st) {
		// your code goes here
		 tstobj = new TST<>();
		 mapobj = new HashMap<>();
		for(String word : st.keys()) {
			tstobj.put(word, st.get(word));
		}
		String aplhabets = "abcdefghijklmnopqrstuvwxyz";
		String[] letters = aplhabets.split("");
		for(String letter : letters) {
			switch(letter) {
				case "a":mapobj.put(letter , 2);
				break;
				case "b":mapobj.put(letter , 2);
				break;
				case "c": mapobj.put(letter , 2);
				break;
				case "d":mapobj.put(letter , 3);
				break;
				case "e":mapobj.put(letter , 3);
				break;
				case "f": mapobj.put(letter , 3);
				break;
				case "g":mapobj.put(letter , 4);
				break;
				case "h":mapobj.put(letter , 4);
				break;
				case "i": mapobj.put(letter , 4);
				break;
				case "j":mapobj.put(letter , 5);
				break;
				case "k":mapobj.put(letter , 5);
				break;
				case "l": mapobj.put(letter , 5);
				break;
				case "m":mapobj.put(letter , 6);
				break;
				case "n":mapobj.put(letter , 6);
				break;
				case "o": mapobj.put(letter , 6);
				break;
				case "p":mapobj.put(letter , 7);
				break;
				case "q":mapobj.put(letter , 7);
				break;
				case "r": mapobj.put(letter , 7);
				break;
				case "s":mapobj.put(letter , 7);
				break;
				case "t":mapobj.put(letter , 8);
				break;
				case "u": mapobj.put(letter , 8);
				break;
				case "v":mapobj.put(letter , 8);
				break;
				case "w":mapobj.put(letter , 9);
				break;
				case "x": mapobj.put(letter , 9);
				break;
				case "y":mapobj.put(letter , 9);
				break;
				case "z":mapobj.put(letter , 9);
				break;
				default:
			}
		}
	}

	// get all the prefixes that match with given prefix.
	public Iterable<String> getAllWords(String prefix) {
		// your code goes here
		return tstobj.keysWithPrefix(prefix);
	}

	public Iterable<String> potentialWords(String t9Signature) {
		// your code goes here
		HashMap<String, TreeSet<String>> matchmap = new HashMap<>();
		for(String key : tstobj.keys()) {
			String[] keyletters = key.split("");
			String pattern = "";
			for(String keyletter : keyletters) {
				pattern = pattern + mapobj.get(keyletter);
			}
			if(matchmap.containsKey(pattern)) {
				TreeSet presentwords = matchmap.get(pattern);
				presentwords.add(key);
				matchmap.put(pattern, presentwords);
			} else {
				TreeSet<String> presentwords = new TreeSet<>();
				presentwords.add(key);
				matchmap.put(pattern, presentwords);
			}
		}
		if(matchmap.get(t9Signature) != null) {
			return matchmap.get(t9Signature);
		}
		return new Bag<String>();
	}

	// return all possibilities(words), find top k with highest frequency.
	public Iterable<String> getSuggestions(Iterable<String> words, int k) {
		// your code goes here
		MaxPQ<Integer> pqobj = new MaxPQ<>();
		HashMap<Integer, TreeSet<String>> suggestwords = new HashMap<>();
		String[] topwords = new String[k];
		for(String word : words) {
			// System.out.println(tstobj.get(word) + "frequencies");
			pqobj.insert(tstobj.get(word));
			if(suggestwords.containsKey(tstobj.get(word))) {
				TreeSet getwords = suggestwords.get(tstobj.get(word));
				getwords.add(word);
				suggestwords.put(tstobj.get(word), getwords);
			} else {
				TreeSet<String> getwords = new TreeSet<>();
				getwords.add(word);
				suggestwords.put(tstobj.get(word), getwords);
			}
		}
		Bag<String> resultwords = new Bag<String>();
		int count = 0;
		while(count != k) {
			if(pqobj.isEmpty()) {
				return resultwords;
			}
			int val = pqobj.delMax();
			// System.out.println(val);
			for(String result : suggestwords.get(val)) {
				if(count == k) {
					return resultwords;
				}
				// System.out.println(result);
				resultwords.add(result);
				count++;
			}
		}
		return resultwords;
	}

	// final output
	// Don't modify this method.
	public Iterable<String> t9(String t9Signature, int k) {
		return getSuggestions(potentialWords(t9Signature), k);
	}
}
