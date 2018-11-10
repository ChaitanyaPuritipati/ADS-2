import java.util.Scanner;
import java.util.ArrayList;
/**
 * Class for solution.
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
        // Self loops are not allowed...
        // Parallel Edges are allowed...
        // Take the Graph input here...

        Scanner scan =  new Scanner(System.in);
        int citycount = Integer.parseInt(scan.nextLine());
        int linkscount = Integer.parseInt(scan.nextLine());
        EdgeWeightedGraph graphobj = new EdgeWeightedGraph(citycount);
        for (int i = 0; i < linkscount; i++) {
            String[] linkdata = scan.nextLine().split(" ");
            Edge linkedge1 = new Edge(
                Integer.parseInt(
                    linkdata[0]), Integer.parseInt(
                    linkdata[1]), Double.parseDouble(
                    linkdata[2]));
            graphobj.addEdge(linkedge1);
        }
        String caseToGo = scan.nextLine();
        switch (caseToGo) {
        case "Graph":
            System.out.println(graphobj);
            break;

        case "DirectedPaths":
            // Handle the case of DirectedPaths, where two integers are given.
            // First is the source and second is the destination.
            // If the path exists print the distance between them.
            // Other wise print "No Path Found."

            String[] pathdata = scan.nextLine().split(" ");
            DijkstraUndirectedSP spobj = new DijkstraUndirectedSP(
                graphobj, Integer.parseInt(
                    pathdata[0]));
            if (spobj.hasPathTo(
                        Integer.parseInt(pathdata[1]))) {
                System.out.println(spobj.distTo(
                                       Integer.parseInt(pathdata[1])));
            } else {
                System.out.println("No Path Found.");
            }
            break;

        case "ViaPaths":
            // Handle the case of ViaPaths, where three integers are given.
            // First is the source and second
            // is the via is the one where path
            // should pass throuh.
            // third is the destination.
            // If the path exists print the distance between them.
            // Other wise print "No Path Found."

            String[] viapathdata = scan.nextLine().split(" ");

            //source

            int source = Integer.parseInt(viapathdata[0]);

            //viacity

            int viacity = Integer.parseInt(viapathdata[1]);

            //destination

            int dest =  Integer.parseInt(viapathdata[2]);

            //Spobj from source to viacity

            DijkstraUndirectedSP spobj1 = new DijkstraUndirectedSP(
                graphobj, source);

            //SPobj from viaobj to destination

            DijkstraUndirectedSP spobj2 = new DijkstraUndirectedSP(
                graphobj, viacity);
            if (spobj1.hasPathTo(viacity) && spobj2.hasPathTo(dest)) {

                System.out.println(
                    spobj1.distTo(viacity) + spobj2.distTo(
                        dest));
                //To display pathcities
                ArrayList<Integer> pathvert = new ArrayList<>();
                for (Edge eachlink : spobj1.pathTo(viacity)) {
                    int either = eachlink.either();
                    int other = eachlink.other(eachlink.either());
                    if (!pathvert.contains(other)) {
                        pathvert.add(other);
                    }
                    if (!pathvert.contains(either)) {
                        pathvert.add(either);
                    }

                }
                for (Edge eachlink1 : spobj2.pathTo(dest)) {
                    int either1 = eachlink1.either();
                    int other1 = eachlink1.other(eachlink1.either());
                    if (!pathvert.contains(other1)) {
                        pathvert.add(other1);
                    }
                    if (!pathvert.contains(either1)) {
                        pathvert.add(either1);
                    }
                }
                //printing path
                for (int everyval : pathvert) {
                    System.out.print(everyval + " ");
                }

            } else {
                System.out.println("No Path Found.");
            }
            break;

        default:
            break;
        }

    }
}
