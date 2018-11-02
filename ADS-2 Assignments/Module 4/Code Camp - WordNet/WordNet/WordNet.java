import java.io.File;
public class WordNet {
    public Digraph digraph;
    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        In filescan = new In(new File("Files/" + synsets));
        String[] syndata = filescan.readAllLines();
        filescan = new In(new File("Files/" + hypernyms));
        String[] hypdata = filescan.readAllLines();
        digraph = new Digraph(syndata.length);
        for (String eachline : hypdata) {
            String[] edges = eachline.split(",");
            for (int l = 1; l < edges.length; l++) {
                digraph.addEdge(Integer.parseInt(edges[0]), Integer.parseInt(edges[l]));
            }
        }
    }

    // // returns all WordNet nouns
    // public Iterable<String> nouns()

    // // is the word a WordNet noun?
    // public boolean isNoun(String word)

    // // distance between nounA and nounB (defined below)
    // public int distance(String nounA, String nounB)

    // // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // // in a shortest ancestral path (defined below)
    // public String sap(String nounA, String nounB)

    // // do unit testing of this class
    // public static void main(String[] args)
}
