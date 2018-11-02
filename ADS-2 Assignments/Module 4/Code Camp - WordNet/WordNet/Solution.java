import java.util.Scanner;
import java.util.Arrays;

class Solution {
	private Solution() {
		//unused
	}
	public static void main(String[] args) {

		String synfilename = StdIn.readLine();
		String hyperfilename = StdIn.readLine();
		WordNet wordobj =new WordNet(synfilename, hyperfilename);
		String linethree = StdIn.readLine();
		if (linethree.equals("Graph")) {
			System.out.println(wordobj.digraph.V() + " vertices, " + wordobj.digraph.E() + " edges ");
			for (int i = 0; i < wordobj.digraph.V(); i++) {
				String str = "";
				str = str + i + ": ";
				for (int eachadj : wordobj.digraph.adj(i)) {
					str = str + eachadj + " ";
				}
				System.out.println(str);
			}
		}
	}
}