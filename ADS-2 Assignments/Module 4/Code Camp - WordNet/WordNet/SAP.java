public class SAP {
    private Digraph graph;
    private int mindis;
    private int distance;
    private int ancs;
    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        this.graph = G;
        distance = -1;
        mindis = -1;
    }
    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        ancestor(v, w);
        return distance;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        BreadthFirstSearch bfsv = new BreadthFirstSearch(graph, v);
        BreadthFirstSearch bfsw = new BreadthFirstSearch(graph, w);
        for (int i = 0; i < graph.V(); i++) {
            if (bfsv.hasPathTo(i) && bfsw.hasPathTo(i)) {
                distance = bfsv.distTo(i) + bfsw.distTo(i);
                return i;
            }
        }
        return -1;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        ancestor(v, w);
        return mindis;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        mindis = -1;
        ancs = -1;
        for (Integer eachv : v) {
            for (Integer eachw : w) {
                if (ancs < 0 && ancestor(eachv, eachw) >= 0) {
                    ancs = ancestor(eachv, eachw);
                    mindis = length(eachv, eachw);
                } else {
                    if (ancs >= 0 && length(eachv, eachw) < mindis) {
                        ancs = ancestor(eachv, eachw);
                        mindis = length(eachv, eachw);
                    }
                }
            }
        }
        return ancs;
    }

}