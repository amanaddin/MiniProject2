package spike;

import java.util.LinkedList;
import java.util.List;

import algorithm.Dijkstra;
import model.Edge;
import model.Graph;

public class DemoProgram1 {

	public static void main(String args[]) {
		Graph graph;
		graph = new Graph();
		List<Edge> edges = new LinkedList<Edge>();
		edges.add(new Edge(1, 2, 4.0));
		edges.add(new Edge(2, 1, 4.0));
		edges.add(new Edge(1, 3, 5.0));
		edges.add(new Edge(4, 2, 6.0));
		edges.add(new Edge(3, 4, 4.0));
		edges.add(new Edge(3, 5, 6.0));
		edges.add(new Edge(5, 6, 4.0));
		edges.add(new Edge(6, 4, 5.0));

		for (Edge edge : edges) {
			graph.addEdge(edge);
		}

		Dijkstra dijkstra = new Dijkstra(graph);

		int source = 4;
		dijkstra.calculateShortestDistancesAndPaths(source);
	
		System.out.println("Shortest distances and paths from source 4 to the vertice 6:");
		System.out.print("4 -> " + 6 + "\n" + "    Shortest distance: " + dijkstra.getShortestDistance(6)
				+ "\n" + "    Shortest path contains vertices: " + dijkstra.getShortestPath(6));

	}

}
