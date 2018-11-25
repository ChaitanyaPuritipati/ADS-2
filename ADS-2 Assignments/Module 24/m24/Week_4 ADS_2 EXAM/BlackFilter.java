public class BlackFilter {

    // Do not instantiate.
    private BlackFilter() { }

    public static void main(String[] args) {
        SET<String> set = new SET<String>();

        // read in strings and add to set
        In in = new In(args[0]);
        while (!in.isEmpty()) {
            String word = in.readString();
            set.add(word);
        }

        StopwatchCPU timerfilter = new StopwatchCPU();
        // read in string from standard input, printing out all exceptions
        int miscount = 0;
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            word = word.replace("/", " ");
            String[] tokens = word.split(" ");
            for (String token : tokens) {
                token = token.replaceAll("[^a-zA-Z\\s+]", "");
                token = token.trim();
                token = token.toUpperCase();
                if (!token.equals("") && !set.contains(token)) {
                    miscount++;
                    StdOut.println(token);
                }
            }
        }
        double progtimefilter = timerfilter.elapsedTime();
        System.out.println(progtimefilter);
        System.out.println(progtimefilter/miscount + " Average blackfilter hit");
    }
}