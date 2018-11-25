class Searchmisstrie {
	private Searchmisstrie() {
		//unused.
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
		TrieST dictionarytriest = loaddictionarytriest("dictionary.txt");
		StopwatchCPU timertriest = new StopwatchCPU();
		for(String key : dictionarytriest.keys()) {
			System.out.println(key);
		}
		double progtimetriest = timertriest.elapsedTime();
		System.out.println(progtimetriest + " Each miss in TrieST");
	}
}