package spike;

import java.util.LinkedList;
import java.util.List;

import algorithm.Dijkstra;
import model.Edge;
import model.Graph;

/**
 * 
 * Dijkstra demo. Creates graph from Figur2b and applies Dijkstras algorithm.
 *
 */
public class DijkstraSpike {

	public static void main(String args[]) {
		Graph graph;

		//Create empty graph
		graph = new Graph(); 
		
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

		//Setup dijkstra with the graph
		Dijkstra dijkstra = new Dijkstra(graph);

		//Calculate shortest distances and paths from vertex 2 (source) to all other vertices
		dijkstra.calculateShortestDistancesAndPaths(2);

		// Print shortest distances and paths 
		System.out.println("Shortest distances and paths from source 2 to all other vertices:");
		System.out.print("2 -> 1  " + "\n" + 
						 "    Shortest distance: " + dijkstra.getShortestDistance(1) + "\n" + 
				         "    Shortest path contains vertices: " + dijkstra.getShortestPath(1) + "\n");
		
		System.out.print("2 -> 3  " + "\n" + 
				         "    Shortest distance: " + dijkstra.getShortestDistance(3) + "\n" +
				         "    Shortest path contains vertices: " + dijkstra.getShortestPath(3) + "\n");
		
		System.out.print("2 -> 4  " + "\n" + 
		                 "    Shortest distance: " + dijkstra.getShortestDistance(4) + "\n" + 
				         "    Shortest path contains vertices: " + dijkstra.getShortestPath(4) + "\n");

	}

}
