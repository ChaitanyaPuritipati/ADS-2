import java.util.HashMap;
import java.util.Arrays;
public class BurrowsWheeler {
	public static void transform() {
		String inputstr = BinaryStdIn.readString();
		CircularSuffixArray suffixobj = new CircularSuffixArray(inputstr);
		for (int i = 0; i < inputstr.length(); i++) {
			if (suffixobj.index(i) == 0) {
				BinaryStdOut.write(i);
				break;
			}
		}
		for (int i = 0; i < inputstr.length(); i++) {
			if (suffixobj.index(i) == 0) {
				BinaryStdOut.write(inputstr.charAt(inputstr.length() - 1), 8);
			} else {
				BinaryStdOut.write(inputstr.charAt(suffixobj.index(i) - 1), 8);
			}
		}
		BinaryStdOut.close();
	}
	public static void inverseTransform() {
		int first = BinaryStdIn.readInt();
		String lastcol = BinaryStdIn.readString();
		String[] t = lastcol.split("");
		HashMap<String, Queue<Integer>> inversemap = new HashMap<>();
		for (int i = 0; i < t.length; i++) {
			if (!inversemap.containsKey(t[i])) {
				inversemap.put(t[i], new Queue<>());
			}
			inversemap.get(t[i]).enqueue(i);
		}
		Arrays.sort(t);
		int[] next = new int[t.length];
		for (int j = 0; j < t.length; j++) {
			next[j] = inversemap.get(t[j]).dequeue();
		}
		for (int k = 0; k < next.length; k++) {
			BinaryStdOut.write(t[first], 8);
			first = next[first];
		}
		BinaryStdOut.close();
	}

	public static void main(String[] args) {
		if (args.length == 0) {
			throw new IllegalArgumentException("Illegal command line argument");
		}
		if (args[0].equals("-")) {
			transform();
		} else if (args[0].equals("+")) {
			inverseTransform();
		} else {
			throw new IllegalArgumentException("Illegal command line argument");
		}
	}
}
