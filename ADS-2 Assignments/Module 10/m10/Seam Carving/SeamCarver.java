import java.awt.Color;
import java.util.Arrays;
public class SeamCarver {
	private Picture inputpic;
	private double[][] energyarray;
	// create a seam carver object based on the given picture
	public SeamCarver(Picture picture) throws Exception {
		if (picture == null) {
			throw new Exception("picture is null");
		}
		this.inputpic = picture;
		this.energyarray = new double[inputpic.height()][inputpic.width()];
	}
	// current picture
	public Picture picture() {
		return this.inputpic;
	}
	// width of current picture
	public int width() {
		return this.inputpic.width();
	}

	// height of current picture
	public int height() {
		return this.inputpic.height();
	}
	public void setenergyarray(int x, int y, double energyval) {
		this.energyarray[y][x] = energyval;
	}
	public String getEnergyarray() {
		String str = "";
		for (int i = 0; i < height(); i++) {
			for (int j = 0; j < width(); j++) {
				str = str + energyarray[i][j] + " ";
			}
			str = str + "\n";
		}
		return str;
	}
	// energy of pixel at column x and row y
	public double energy(int x, int y) {
		if (x == 0 || x == width() - 1 || y == 0 || y == height() - 1) {
			return 1000;
		}
		Color pixelnextcol = this.inputpic.get(x + 1 , y);
		Color pixelprevcol = this.inputpic.get(x - 1 , y);
		Color pixeltopcol = this.inputpic.get(x, y - 1);
		Color pixelbottomcol = this.inputpic.get(x, y + 1);
		double rxval = Math.pow(pixelnextcol.getRed() - pixelprevcol.getRed(), 2);
		double bxval = Math.pow(pixelnextcol.getBlue() - pixelprevcol.getBlue(), 2);
		double gxval = Math.pow(pixelnextcol.getGreen() - pixelprevcol.getGreen(), 2);
		double totalxval = rxval + bxval + gxval;
		double ryval = Math.pow(pixelbottomcol.getRed() - pixeltopcol.getRed(), 2);
		double byval = Math.pow(pixelbottomcol.getBlue() - pixeltopcol.getBlue(), 2);
		double gyval = Math.pow(pixelbottomcol.getGreen() - pixeltopcol.getGreen(), 2);
		double totalyval = ryval + byval + gyval;
		return Math.sqrt(totalxval + totalyval);

	}

	// sequence of indices for horizontal seam
	public int[] findHorizontalSeam() {
		return new int[0];
	}

	// sequence of indices for vertical seam
	public int[] findVerticalSeam() {
		System.out.println(energyarray[0] + "energyarray");
		return new int[0];
	}

	// remove horizontal seam from current picture
	public void removeHorizontalSeam(int[] seam) {

	}

	// remove vertical seam from current picture
	public void removeVerticalSeam(int[] seam) {

	}
}