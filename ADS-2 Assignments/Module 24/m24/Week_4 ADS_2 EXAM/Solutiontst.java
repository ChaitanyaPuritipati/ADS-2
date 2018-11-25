class Solutiontst {
	private Solutiontst() {
		//unused
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
		String dictfile = "dictionary.txt";
		TST dictionarytst = loaddictionarytst(dictfile);
		String inputfile = "dickens.txt";
		In inobj = new In(inputfile);
		String[] inputwords = inobj.readAllLines();
		StopwatchCPU timertst = new StopwatchCPU();
		int miswordcounttst = 0;
		for (String line : inputwords) {
			line = line.replaceAll("/", " ");
			line = line.replaceAll("[^a-zA-Z\\s+]", " ");
			if (!line.equals("")) {
				String[] words = line.split(" ");
				for (String word : words) {
					if (!word.equals("")) {
						word = word.toUpperCase();
						if (!dictionarytst.contains(word)) {
							miswordcounttst++;
							System.out.println(word);
						}
					}
				}
			}
		}
		double progtimetst = timertst.elapsedTime();
		System.out.println(progtimetst + " tsttime");
		System.out.println(progtimetst/miswordcounttst + " average tst");
	}
}