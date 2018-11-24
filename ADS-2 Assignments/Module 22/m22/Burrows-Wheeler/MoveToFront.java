import java.util.ArrayList;
public class MoveToFront {

	public static void encode() {
		ArrayList<Integer> exascii = new ArrayList<>();
		int R = 256;
		for (int i = 0; i < R; i++) {
			exascii.add(i);
		}
		while (!BinaryStdIn.isEmpty()) {
			int aschar = BinaryStdIn.readChar();
			int index = exascii.indexOf(aschar);
			BinaryStdOut.write(index, 8);
			exascii.remove(index);
			exascii.add(0, aschar);
		}
		BinaryStdOut.close();
	}

	public static void decode() {
		ArrayList<Integer> exascii = new ArrayList<>();
		int R = 256;
		for (int i = 0; i < R; i++) {
			exascii.add(i);
		}
		while (!BinaryStdIn.isEmpty()) {
			int index = BinaryStdIn.readChar();
			int ch = exascii.get(index);
			BinaryStdOut.write(ch, 8);
			exascii.remove(index);
			exascii.add(0, ch);
		}
		BinaryStdOut.close();
	}

	public static void main(String[] args) {
		if      (args[0].equals("-")) encode();
		else if (args[0].equals("+")) decode();
		else throw new IllegalArgumentException("Illegal command line argument");
	}
}
