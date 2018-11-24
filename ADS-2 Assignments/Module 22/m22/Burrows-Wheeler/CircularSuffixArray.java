import java.util.Arrays;
public class CircularSuffixArray {
    private Suffix[] suffixes;
    private String text;
    /**
     * Initializes a suffix array for the given {@code text} string.
     * @param text the input string
     */
    public CircularSuffixArray(String text) {
        this.text = text;
        int n = text.length();
        this.suffixes = new Suffix[n];
        for (int i = 0; i < n; i++)
            suffixes[i] = new Suffix(i);
        Arrays.sort(suffixes);
    }

    private class Suffix implements Comparable<Suffix> {
        // private final String text;
        private final int index;

        private Suffix(int index) {
            // this.text = text;
            this.index = index;
        }
        private int length() {
            return text.length();
        }
        private char charAt(int i) {
            i = (index + i) % length();
            return text.charAt(i);
        }

        public int compareTo(Suffix that) {
            if (this == that) return 0;  // optimization
            int n = Math.min(this.length(), that.length());
            for (int i = 0; i < n; i++) {
                if (this.charAt(i) < that.charAt(i)) return -1;
                if (this.charAt(i) > that.charAt(i)) return +1;
            }
            return this.length() - that.length();
        }

        public String toString() {
            return text.substring(index) + text.substring(0, index);
        }
    }
    public int length() {
        return suffixes.length;
    }

    /**
     * returns index of ith sorted suffix
     *
     * @param i
     *            the index of the ith sorted suffix
     * @return
     */
    public int index(int i) {
        if (i < 0 || i >= suffixes.length) throw new IllegalArgumentException();
        return suffixes[i].index;
    }
}
