import java.util.Arrays;
class Solution {
	private Solution() {
		//unused
	}
	public static TrieST loaddictionarytriest(String filename) {
		In inobj = new In(filename);
		String[] dictwords = inobj.readAllLines();
		TrieST triestobj = new TrieST();
		for (String word : dictwords) {
			triestobj.add(word);
		}
		return triestobj;
	}
	public static void main(String[] args) {
		String dictfile = "dictionary.txt";
		TrieST dictionarytriest = loaddictionarytriest(dictfile);
		StopwatchCPU timertriest = new StopwatchCPU();
		String inputfile = "war.txt";
		In inobj = new In(inputfile);
		String[] inputwords = inobj.readAllLines();
		int miswordcounttriest = 0;
		for (String line : inputwords) {
			line = line.replaceAll("/", " ");
			line = line.replaceAll("[^a-zA-Z\\s+]", " ");
			if (!line.equals("")) {
				String[] words = line.split(" ");
				for (String word : words) {
					if (!word.equals("")) {
						word = word.toUpperCase();
						if (!dictionarytriest.contains(word)) {
							miswordcounttriest++;
							System.out.println(word);
						}
					}
				}
			}
		}
		double progtimetriest = timertriest.elapsedTime();
		System.out.println(progtimetriest + " triesttime");
		System.out.println(progtimetriest/miswordcounttriest + " average triest");
	}
}