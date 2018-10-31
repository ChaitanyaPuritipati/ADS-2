import java.util.Scanner;
/**
 * Class for directed cycle.
 */
class DirectedCycle {
    /**
     * { marked array }.
     */
    private boolean[] marked;
    /**
     * { edgeTo array }.
     */
    private int[] edgeTo;
    /**
     * { onStack array }.
     */
    private boolean[] onStack;
    /**
     * { cycle stack }.
     */
    private Stack<Integer> cycle;
    /**
     * Constructs the object.
     *
     * @param      G     { Graph }
     */
    public DirectedCycle(Digraph G) {
        marked  = new boolean[G.V()];
        onStack = new boolean[G.V()];
        edgeTo  = new int[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!marked[v] && cycle == null) dfs(G, v);
    }
    /**
     * { dfs function }.
     *
     * @param      G     { parameter_description }
     * @param      v     { parameter_description }
     */
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
    /**
     * Determines if it has cycle.
     *
     * @return     True if has cycle, False otherwise.
     */
    public boolean hasCycle() {
        return cycle != null;
    }
}
/**
 * { Solution class }.
 */
final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        //unused.
    }
    /**
     * { Main function }.
     *
     * @param      args  The arguments
     */
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