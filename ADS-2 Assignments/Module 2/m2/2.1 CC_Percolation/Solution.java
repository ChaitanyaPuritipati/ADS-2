import java.util.Scanner;
class AdjacencyList {
	/**
	 * { variable for Bags array }.
	 */
	private Bag<Integer>[] bags;
	/**
	 * { variable for vertex value }.
	 */
	private int vertexval;
	/**
	 * { variable for edge num }.
	 */
	private int edgenum;
	/**
	 * Constructs the object.
	 *
	 * @param      vertex  The vertex
	 */
	AdjacencyList(final int vertex) {
		this.vertexval = vertex;
		bags = (Bag<Integer>[]) new Bag[vertex];
		for (int l = 0; l < vertex; l++) {
			bags[l] = new Bag();
		}
		this.edgenum = 0;
	}
	/**
	 * { function for number of vertices }.
	 * Complexity: O(1)
	 * @return     { description_of_the_return_value }
	 */
	public int numberofVertices() {
		return this.vertexval;
	}
	/**
	 * { function for number of edges }.
	 *Complexity: O(1)
	 * @return     { description_of_the_return_value }
	 */
	public int numberofEdges() {
		return this.edgenum;
	}
	/**
	 * Adds an edge.
	 *Complexity: O(1)
	 * @param      v     { parameter_description }
	 * @param      w     { parameter_description }
	 */
	public void addEdge(final int v, final int w) {
		edgenum++;
		if (v == w || hasEdge(v, w)) {
			edgenum--;
		}
		bags[v].add(w);
		bags[w].add(v);
	}
	/**
	 * Determines if it has edge.
	 *Complexity: O(degree(v))
	 * @param      v     { parameter_description }
	 * @param      w     { parameter_description }
	 *
	 * @return     True if has edge, False otherwise.
	 */
	public boolean hasEdge(final int v, final int w) {
		for (Integer eachval : bags[v]) {
			if (eachval == w) {
				return true;
			}
		}
		return false;
	}
	/**
	 * { function for Iterator }.
	 *O(degree(v))
	 * @param      v     { parameter_description }
	 *
	 * @return     { description_of_the_return_value }
	 */
	public Iterable<Integer> adj(final int v) {
		Queue<Integer> queue = new Queue<>();
		for (Integer eachval : bags[v]) {
			queue.enqueue(eachval);
		}
		return queue;
	}
	/**
	 * Returns a string representation of the object.
	 *Complexity: O(1)
	 * @return     String representation of the object.
	 */
	public String toString() {
		if (edgenum == 0) {
			System.out.println(vertexval + " vertices, "
			                   + edgenum + " edges");
			System.out.println("No edges");
			return null;
		}
		System.out.println(vertexval + " vertices, "
		                   + edgenum + " edges");
		return null;
	}
}
class Solution {
	Solution() {
		//unused.
	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int vertex = Integer.parseInt(scan.nextLine());
		AdjacencyList adjlstobj = new AdjacencyList(vertex);
		while(scan.hasNext()) {
			String[] edges = scan.nextLine().split(" ");
			adjlstobj.addEdge(Integer.parseInt(edges[0]), Integer.parseInt(edges[1]));
		}
		for(Integer each : adjlstobj.adj(1)) {
			System.out.println(each);
		}
	}
}
