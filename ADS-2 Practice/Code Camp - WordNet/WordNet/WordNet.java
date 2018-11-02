import java.io.File;
import java.util.HashMap;
public class WordNet {
    public Digraph digraph;
    public SAP sapobj;
    HashMap<Integer, String> idmap = new HashMap<>();
    HashMap<String, Bag<Integer>> wordmap = new HashMap<>();
    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        In filescan = new In(new File("Files/" + synsets));
        String[] syndata = filescan.readAllLines();
        for (String each : syndata) {
            String[] tokens = each.split(",");
            String[] words = tokens[1].split(" ");
            idmap.put(Integer.parseInt(tokens[0]), tokens[1]);
            for (String eachword : words) {
                if (wordmap.containsKey(eachword)) {
                    Bag testbag = wordmap.get(eachword);
                    testbag.add(tokens[0]);
                    wordmap.put(eachword,testbag);
                } else {
                    wordmap.put(eachword, new Bag<Integer>());
                    Bag testbag = wordmap.get(eachword);
                    testbag.add(Integer.parseInt(tokens[0]));
                    wordmap.put(eachword, testbag);
                }
            }
        }
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

    // returns all WordNet nouns
    // public Iterable<String> nouns() {
    //     return wordmap.Keys();
    // }
    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        return wordmap.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        sapobj = new SAP(digraph);
        System.out.println(wordmap.get(nounA));
        int dist = sapobj.length(wordmap.get(nounA), wordmap.get(nounB));
        return dist;
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        sapobj = new SAP(digraph);
        int id = sapobj.ancestor(wordmap.get(nounA), wordmap.get(nounB));
        return idmap.get(id);
    }

    // do unit testing of this class
}
