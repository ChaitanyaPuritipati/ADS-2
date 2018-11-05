import java.util.Scanner;
/**
 * Solution class.
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
    public static void main(final String[] args) {
        Scanner scan =  new Scanner(System.in);
        int vertices = Integer.parseInt(scan.nextLine());
        int edges = Integer.parseInt(scan.nextLine());
        EdgeWeightedGraph edgegraphobj = new EdgeWeightedGraph(vertices);
        for (int i = 0; i < edges; i++) {
            String[] edgedata = scan.nextLine().split(" ");
            Edge eachedge = new Edge(
                Integer.parseInt(
                    edgedata[0]), Integer.parseInt(
                    edgedata[1]), Double.parseDouble(
                    edgedata[2]));
            edgegraphobj.addEdge(eachedge);
        }
        KruskalMST kruobj = new KruskalMST(edgegraphobj);
        StdOut.printf("%.5f", kruobj.weight());
    }
}