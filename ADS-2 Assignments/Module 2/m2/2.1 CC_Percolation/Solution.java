import java.util.Scanner;
class Graph {
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
	Graph(final int vertex) {
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
	public int vertices() {
		return this.vertexval;
	}
	/**
	 * { function for number of edges }.
	 *Complexity: O(1)
	 * @return     { description_of_the_return_value }
	 */
	public int e() {
		return this.edgenum;
	}
	/**
	 * Adds an edge.
	 *Complexity: O(1)
	 * @param      v     { parameter_description }
	 * @param      w     { parameter_description }
	 */
	public void addEdge(final int v, final int w) {
		// System.out.println(v + "   ----  " + w);
		if (v == w || hasEdge(v, w)) {
			return;
		}
		bags[v].add(w);
		bags[w].add(v);
		edgenum++;
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
	// /**
	//  * Returns a string representation of the object.
	//  *Complexity: O(1)
	//  * @return     String representation of the object.
	//  */
	// public String toString() {
	// 	if (edgenum == 0) {
	// 		System.out.println(vertexval + " vertices, "
	// 		                   + edgenum + " edges");
	// 		System.out.println("No edges");
	// 		return null;
	// 	}
	// 	System.out.println(vertexval + " vertices, "
	// 	                   + edgenum + " edges");
	// 	return null;
	// }
}
class CC {
	
    private boolean[] marked;
    
    private int[] id;
    
    private int[] size;
    
    private int count;

    
    public CC(final Graph g) {
        marked = new boolean[g.vertices()];
        id = new int[g.vertices()];
        size = new int[g.vertices()];
        for (int v = 0; v < g.vertices(); v++) {
            if (!marked[v]) {
                dfs(g, v);
                count++;
            }
        }
    }

   
    private void dfs(final Graph g, final int v) {
        marked[v] = true;
        id[v] = count;
        size[count]++;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }
    }
    
    public int id(final int v) {
        return id[v];
    }
    
    public boolean connected(final int v, final int w) {
        return id(v) == id(w);
    }
}
class Percolation {
    private int cnt;
    private int[][] grid;
    private int size;
    private Graph cd;
    private CC cc;
    Percolation(final int n) {
        grid = new int[n][n];
        cnt = 0;
        cd = new Graph((n * n) + 2);
        cc = new CC(cd);
        this.size = n;
    }
    void open(final int row, final int col) {
        grid[row][col] = 1;
        cnt++;
        if (row == 0) {
            cd.addEdge(0, component(row, col));
        }
        if (row == size - 1) {
            cd.addEdge((size * size) + 1, component(row, col));
        }
        if (row + 1 < size && grid[row][col] == 1) {
                cd.addEdge(
                    component(row + 1, col), component(row, col));
            }
        if (row - 1 >= 0 && grid[row - 1][col] == 1) {
                cd.addEdge(
                    component(row - 1, col), component(row, col));
        }
        if (col - 1 >= 0 && grid[row][col - 1] == 1) {
                cd.addEdge(component(row, col - 1), component(row, col));
        }
        if (col + 1 < size && grid[row][col + 1] == 1) {
                cd.addEdge(
                    component(row, col + 1), component(row, col));
        }
    }
   
    int component(final int i, final int j) {
        return (i) * size + j;
    }
    
    boolean isOpen(final int row, final int col) {
        return grid[row][col] == 1;
    }
  
    boolean isFull(final int row, final int col) {
        return grid[row][col] == 0;
    }
    
    int numberofopensites() {
        return cnt;
    }
    
    boolean percolates() {
        cc = new CC(cd);
        return cc.connected(0, (size * size) + 1);
    }
}
class Solution {
	Solution() {
		//unused.
	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int vertex = Integer.parseInt(scan.nextLine());
		Percolation percoobj = new Percolation(vertex);
		while (scan.hasNext()) {
			String[] edges = scan.nextLine().split(" ");
			percoobj.open(Integer.parseInt(edges[0]) - 1, Integer.parseInt(edges[1]) - 1);
		}
		System.out.println(percoobj.percolates());
	}
}
