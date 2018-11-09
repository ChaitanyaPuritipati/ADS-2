import java.awt.Color;
import java.util.Arrays;
public class SeamCarver {
	private Picture inputpic;
	private double[][] energyarray;
	private EdgeWeightedDigraph graph;
	// create a seam carver object based on the given picture
	public SeamCarver(Picture picture) throws Exception {
		if (picture == null) {
			throw new Exception("picture is null");
		}
		this.inputpic = picture;
		this.energyarray = new double[inputpic.height()][inputpic.width()];
		this.graph = new EdgeWeightedDigraph(inputpic.height() * inputpic.width());
		setenergyarray();
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
	public void setenergyarray() {
		for (int row = 0; row < height(); row++) {
			for (int col = 0; col < width(); col++) {
				energyarray[row][col] = energy(col, row);
			}
		}
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
		double min = Double.POSITIVE_INFINITY;
		DijkstraSP mindspobj = new DijkstraSP(graph, 0);
		int dest = -1;
		for (int i = 0; i < width() - 1; i++) {
			DijkstraSP eachspobj = new DijkstraSP(graph, i);
			for(int j = 0; j < height(); j++) {
				double testval = eachspobj.distTo(j);
				if(testval < min) {
					mindspobj = eachspobj;
					dest = j;
				}
			}
		}
		System.out.println(mindspobj.distTo(dest) + "mindist");
		System.out.println(dest + "val");
		return new int[0];
	}

	// remove horizontal seam from current picture
	public void removeHorizontalSeam(int[] seam) {

	}

	// remove vertical seam from current picture
	public void removeVerticalSeam(int[] seam) {

	}

	public void buildgraph() {
		for (int i = 0; i < height() - 1; i++) {
			for (int j = 0; j < width(); j++) {
				int ver1 = i * width() + j;
				if (j == 0) {
					int ver2 = ((i + 1) * width()) + j;
					int ver3 = ((i + 1) * width()) + j + 1;
					graph.addEdge(new DirectedEdge(ver1, ver2, energyarray[i + 1][j]));
					graph.addEdge(new DirectedEdge(ver1, ver3, energyarray[i + 1][j + 1]));
				} else if (j == width() - 1) {
					int ver2 = ((i + 1) * width()) + j;
					int ver3 = ((i + 1) * width()) + j - 1;
					graph.addEdge(new DirectedEdge(ver1, ver2, energyarray[i + 1][j]));
					graph.addEdge(new DirectedEdge(ver1, ver3, energyarray[i + 1][j - 1]));
				} else {
					int ver2 = ((i + 1) * width()) + j;
					int ver3 = ((i + 1) * width()) + j - 1;
					int ver4 = ((i + 1) * width()) + j + 1;
					graph.addEdge(new DirectedEdge(ver1, ver2, energyarray[i + 1][j]));
					graph.addEdge(new DirectedEdge(ver1, ver3, energyarray[i + 1][j - 1]));
					graph.addEdge(new DirectedEdge(ver1, ver4, energyarray[i + 1][j + 1]));
				}

			}
		}
	}

}