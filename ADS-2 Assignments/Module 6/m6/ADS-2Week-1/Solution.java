import java.util.Arrays;
class PageRank {
	private Digraph pggraph;
	private double[] prval;
	private double[] nowval;
	PageRank(Digraph graph) {
		this.pggraph = graph;
		prval = new double[pggraph.V()];
		for(int y = 0; y < prval.length; y++) {
			prval[y] = (1.0 / (pggraph.V()));
		}
		for (int z = 0; z < pggraph.V(); z++) {
			if(pggraph.outdegree(z) == 0) {
				for (int b = 0; b < pggraph.V(); b++) {
					if(b != z) {
						pggraph.addEdge(z, b);
					}
				}
			}
		}
		nowval = new double[pggraph.V()];
		updatingprvals();
	}
	void updatingprvals() {
		for (int i = 1; i < 1000; i++) {
			for (int j = 0; j < pggraph.V(); j++) {
				getPR(j);
			}
			prval = Arrays.copyOf(nowval, nowval.length);
		}
	}
	double getPR(int v) {
		double testprval = 0.0;
		if(pggraph.indegree(v) == 0) {
			nowval[v] = 0.0;
			return nowval[v];
		}
		for(Integer eachadj : pggraph.reverse().adj(v)) {
			testprval = testprval + (prval[eachadj]/pggraph.outdegree(eachadj));
		}
		nowval[v] = testprval;
		return nowval[v];
	}
	public String toString() {
		String str = "";
		for (int l = 0; l < nowval.length; l++) {
			str = str + l + " - " + nowval[l] + "\n";
		}
		return str;
	}
}

class WebSearch {

}


public class Solution {
	public static void main(String[] args) {
		// read the first line of the input to get the number of vertices

		int vertexnum = Integer.parseInt(StdIn.readLine());
		// iterate count of vertices times

		// to read the adjacency list from std input
		// and build the graph
		Digraph graph = new Digraph(vertexnum);
		for (int i = 0; i < vertexnum; i++) {
			String[] edges = StdIn.readLine().split(" ");
			for (int k = 1; k < edges.length; k++) {
				graph.addEdge(Integer.parseInt(edges[0]), Integer.parseInt(edges[k]));
			}
		}
		System.out.println(graph);
		// Create page rank object and pass the graph object to the constructor

        PageRank pgrankobj = new PageRank(graph);
		// print the page rank object
        System.out.println(pgrankobj);
		// This part is only for the final test case

		// File path to the web content
		String file = "WebContent.txt";

		// instantiate web search object
		// and pass the page rank object and the file path to the constructor

		// read the search queries from std in
		// remove the q= prefix and extract the search word
		// pass the word to iAmFeelingLucky method of web search
		// print the return value of iAmFeelingLucky

	}
}
