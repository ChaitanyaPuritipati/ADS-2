import java.util.Scanner;
import java.util.Arrays;
import java.io.File;
class Solution {
	private Solution() {
		//unused
	}
	public static void main(String[] args) {
		// StdIn stdinobj = new StdIn();
		String synfilename = StdIn.readLine();
		In file = new In(new File("Files/" + synfilename));
		String[] syndata = file.readAllLines();
		String hyperfilename = StdIn.readLine();
		file = new In(new File("Files/" + hyperfilename));
		String[] hypdata = file.readAllLines();
		String linethree = StdIn.readLine();
		System.out.println(Arrays.toString(syndata));
		System.out.println(Arrays.toString(hypdata));
		// if(linethree.equals("Graph")) {
		// }
	}
}