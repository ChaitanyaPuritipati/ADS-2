class Searchmisstst {
	private Searchmisstst() {
		//unused.
	}
	public static TST loaddictionarytst(String filename) {
		In inobj = new In(filename);
		String[] dictwords = inobj.readAllLines();
		TST tstobj = new TST();
		int wordcount = 0;
		for (String word : dictwords) {
			tstobj.put(word, wordcount);
			wordcount++;
		}
		return tstobj;
	}
	public static void main(String[] args) {
		TST dictionarytst = loaddictionarytst("dictionary.txt");
		StopwatchCPU timertst = new StopwatchCPU();
		for(Object key : dictionarytst.keys()) {
			System.out.println(key);
		}
		double progtimetst = timertst.elapsedTime();
		System.out.println(progtimetst + " Each miss in TST");
	}
}