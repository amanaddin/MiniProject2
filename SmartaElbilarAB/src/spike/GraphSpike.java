package spike;

import java.util.LinkedList;
import java.util.List;

import model.Edge;
import model.Graph;

public class GraphSpike {

    /**
     * Graph demo. Creates graph from Figur2b.
     */
    public static void main(String args[]) {
		//Create empty graph
		Graph graph = new Graph(); 
		
		//Add edges (and its vertices) to the graph
		List<Edge> edges = new LinkedList<Edge>();
		edges.add(new Edge(1, 2, 3.0));
		edges.add(new Edge(2, 1, 3.0));
		edges.add(new Edge(1, 3, 4.0));
		edges.add(new Edge(3, 2, 2.0));
		edges.add(new Edge(3, 4, 7.0));
		edges.add(new Edge(4, 3, 7.0));
		edges.add(new Edge(4, 2, 6.0));

		for (Edge edge : edges) {
			graph.addEdge(edge);
		}

		// Print graph
		System.out.println("Graph edges:");
		System.out.print(graph);
		System.out.print("\n");
    }
}
