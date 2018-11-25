class Seachmissblack {
	private Seachmissblack() {
		//unused.
	}
	public static void main(String[] args) {
		SET<String> set = new SET<String>();
		In in = new In("dictionary.txt");
        while (!in.isEmpty()) {
            String word = in.readString();
            set.add(word);
        }
        StopwatchCPU timerfilter = new StopwatchCPU();
        for(String word : set) {
        	System.out.println(word);
        }
        double progtimefilter = timerfilter.elapsedTime();
        System.out.println(progtimefilter + " Each miss in Filter");
	}
}