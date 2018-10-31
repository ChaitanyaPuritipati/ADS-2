import java.util.Scanner;
class DirectedCycle {
	private boolean[] marked;
    private int[] edgeTo;
    private boolean[] onStack;
    private Stack<Integer> cycle;
    public DirectedCycle(Digraph G) {
        marked  = new boolean[G.V()];
        onStack = new boolean[G.V()];
        edgeTo  = new int[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!marked[v] && cycle == null) dfs(G, v);
    }
    private void dfs(Digraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (cycle != null) return;
            else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
            else if (onStack[w]) {
                cycle = new Stack<Integer>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }
    public boolean hasCycle() {
        return cycle != null;
    }
}
final class Solution {
	private Solution() {
		//unused.
	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int vertices = Integer.parseInt(scan.nextLine());
		int edges = Integer.parseInt(scan.nextLine());
		Digraph graphobj = new Digraph(vertices);
		for(int i = 0; i < edges; i++) {
			String[] edgevals = scan.nextLine().split(" ");
			graphobj.addEdge(Integer.parseInt(edgevals[0]), Integer.parseInt(edgevals[1])); 
		}
		DirectedCycle directedcycleobj = new DirectedCycle(graphobj);
		if(directedcycleobj.hasCycle()) {
			System.out.println("Cycle exists.");
		} else {
			System.out.println("Cycle doesn't exists.");
		}
	}
}