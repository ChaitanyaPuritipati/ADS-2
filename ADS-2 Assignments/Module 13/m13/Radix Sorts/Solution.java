import java.util.Scanner;
class Solution {
	private Solution() {
		//unused
	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int stringscount = Integer.parseInt(scan.nextLine());
		String[] strings = new String[stringscount];
		for (int i = 0; i < stringscount; i++) {
			strings[i] = scan.nextLine();
		}
		Quick3string way3sortobj = new Quick3string();
		way3sortobj.sort(strings);
		System.out.println(way3sortobj.toString(strings));
	}
}