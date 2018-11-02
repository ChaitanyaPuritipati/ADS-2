import java.util.Scanner;
import java.util.Arrays;
import java.io.File;
class Solution {
	private Solution() {
		//unused
	}
	public static void main(String[] args) {
		String synfilename = StdIn.readLine();
		In file = new In(new File("Files/" + synfilename));
		String[] syndata = file.readAllLines();
		String hyperfilename = StdIn.readLine();
		file = new In(new File("Files/" + hyperfilename));
		String[] hypdata = file.readAllLines();
		String linethree = StdIn.readLine();
		Digraph digraphobj = new Digraph(syndata.length);
		for (String eachline : hypdata) {
			String[] edges = eachline.split(",");
			for (int l = 1; l < edges.length; l++) {
				digraphobj.addEdge(Integer.parseInt(edges[0]), Integer.parseInt(edges[l]));
			}
		}
		if (linethree.equals("Graph")) {
			System.out.println(digraphobj.V() + " vertices, " + digraphobj.E() + " edges ");
			for (int i = 0; i < digraphobj.V(); i++) {
				String str = "";
				str = str + i + ": ";
				for (int eachadj : digraphobj.adj(i)) {
					str = str + eachadj + " ";
				}
				System.out.println(str);
			}
		}
	}
}