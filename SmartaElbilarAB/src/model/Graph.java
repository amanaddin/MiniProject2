package model;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;

// For file reading
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

/**
 * Implementation of a graph using a HashMap to represent adjacency list.
 * 
 * @author SmartaElbilarAB
 */
public class Graph {
	// Hash map that stores a vertex index together with
	// a list of all it's connected edges
	private HashMap<Integer, ArrayList<Edge>> vertexEdgesMap = new HashMap<Integer,ArrayList<Edge>>(); // adjacency-list

	/**
	 * Instantiate empty graph
	 */
	public Graph() {
	};

	/**
	 * Instantiate graph from file with data
	 * 
	 * @param file
	 * @throws IOException
	 */
	public Graph(String file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		while ((line = reader.readLine()) != null) {
			String[] parts = line.split("\\s");

			if (parts.length == 3) {
				int from = Integer.parseInt(parts[0]);
				int to = Integer.parseInt(parts[1]);
				double length = Double.parseDouble(parts[2]);

				addEdge(new Edge(from, to, length));
			}
		}
		reader.close();
	}

	/**
	 * @param vertex
	 * @return list of edges that the given vertex is connected to.
	 */
	public ArrayList<Edge> getEdgesForVertex(int vertex) {
		return vertexEdgesMap.get(vertex);
	}

	/**
	 * @return list of all edges in the graph.
	 */
	public ArrayList<Edge> edges() {
		ArrayList<Edge> list = new ArrayList<Edge>();

		// Loop over all vertices
		for (int vertex : vertexEdgesMap.keySet()) {
			ArrayList<Edge> currentEdges = vertexEdgesMap.get(vertex);
			for (Edge e : currentEdges) {
				list.add(e);
			}
		}
		return list;
	}

	/**
	 * @return iterable of all vertices in the graph.
	 */
	public Iterable<Integer> vertices() {
		HashSet<Integer> set = new HashSet<Integer>();
		for (Edge edge : edges()) {
			set.add(edge.startVertex());
			set.add(edge.endVertex());
		}

		return set;
	}

	/**
	 * @return number of vertices in graph
	 */
	public int size() {
		return vertexEdgesMap.size();
	}

	/**
	 * @return string representation of the graph
	 */
	public String toString() {
		String string = "";
		// Loop over all vertices
		for (int vertex : vertexEdgesMap.keySet()) {
			ArrayList<Edge> edges = vertexEdgesMap.get(vertex);

			if (edges.size() == 0)
				string += "-,";

			for (Edge edge : edges)
				string += vertex + " -> " + edge.endVertex() + " length: " + edge.length() + "\n";
		}

		return string;
	}

	/**
	 * Add new edge to the graph
	 * 
	 * @param newEdge
	 */
	public void addEdge(Edge newEdge) {
		if (!vertexEdgesMap.containsKey(newEdge.startVertex()))
			vertexEdgesMap.put(newEdge.startVertex(), new ArrayList<Edge>());

		ArrayList<Edge> currentEdges = vertexEdgesMap.get(newEdge.startVertex());

		// Check if edge already exists,
		// if it is, replace it with new one assuming it needs to be updated
		boolean edgeExists = false;
		for (int i = 0; i < currentEdges.size(); i++) {
			if (currentEdges.get(i).endVertex() == newEdge.endVertex()) {
				currentEdges.set(i, newEdge);
				edgeExists = true;
				break;
			}
		}

		if (!edgeExists)
			currentEdges.add(newEdge);

		vertexEdgesMap.put(newEdge.startVertex(), currentEdges);
	}
}
