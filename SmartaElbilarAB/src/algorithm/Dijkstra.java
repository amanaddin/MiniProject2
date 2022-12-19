package algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import model.Edge;
import model.Graph;
import model.ShortestDistances;
import priorityQueue.HeapPriorityQueue;
import priorityQueue.PriorityQueue;

/**
 * Class that implements Dijkstras algorithm
 * 
 * @author SmartaElbilarAB
 */
public class Dijkstra {
	/*
	 * Number of vertices in the graph
	 */
	private int size;
	private final double INFINITY = Double.POSITIVE_INFINITY;
	/*
	 * A hash map that stores a vertex index together with it's shortest distance
	 * from the source calculated so far
	 */
	private ShortestDistances shortestDistances;

	/*
	 * A hash map that stores a vertex together with a list that should describe
	 * it's shortest path from the source
	 */
	private HashMap<Integer, List<Integer>> shortestPaths;

	/*
	 * Priority queue that stores vertices that needs to be visited in Dijkstras
	 * algorithm
	 */
	private PriorityQueue<Integer> verticesToVisit;

	/*
	 * Comparator that is used for the priority queue The comparator instance is
	 * created using an anonymous class
	 */
	public Comparator<Integer> PQComparator = new Comparator<Integer>() {

		public int compare(Integer vertexA, Integer vertexB) {
			double distanceA = shortestDistances.getDistanceFromSource(vertexA);
			double distacneB = shortestDistances.getDistanceFromSource(vertexB);
			return Double.compare(distanceA, distacneB);
		}
	};

	/*
	 * The graph to be searched for shortest distances/paths in
	 */
	private Graph graph;

	/*
	 * An internal hash map that stores vertices and it's previous vertex. This has
	 * map is used internally in this class to construct shortest paths
	 */
	private HashMap<Integer, Integer> previousVertexHashMap;

	/**
	 * Constructor that assigns the graph to be considered using Dijkstras algorithm
	 * 
	 * @param graph
	 */
	public Dijkstra(Graph graph) {
		this.graph = graph;
		size = graph.size();
	}

	/**
	 * Calculates shortest distances and paths from the given source to each of the
	 * vertices in the graph
	 * 
	 * @param source source vertex
	 */
	public void calculateShortestDistancesAndPaths(int source) {
		previousVertexHashMap = new HashMap<Integer, Integer>();
		shortestDistances = new ShortestDistances();
		shortestPaths = new HashMap<Integer, List<Integer>>();
		verticesToVisit = new HeapPriorityQueue<Integer>(size, PQComparator);

		for (int vertex : graph.vertices()) {
			shortestDistances.updateDistanceFromSource(vertex, INFINITY);
		}

		previousVertexHashMap.put(source, -1);
		shortestDistances.updateDistanceFromSource(source, 0);
		verticesToVisit.enqueue(source);

		// Visit all vertices in priority queue
		while (verticesToVisit.size() > 0) {
			int currentVertex = verticesToVisit.dequeue();

			ArrayList<Edge> currentEdges = graph.getEdgesForVertex(currentVertex);

			if (currentEdges != null) {
				for (Edge currentEdge : currentEdges) {
					int neighborVertex = currentEdge.endVertex();

					if (shortestDistances.getDistanceFromSource(neighborVertex) == INFINITY) {
						verticesToVisit.enqueue(neighborVertex);
					}

					double newDistance = shortestDistances.getDistanceFromSource(currentVertex) + currentEdge.length();
					if (shortestDistances.getDistanceFromSource(neighborVertex) > newDistance) {
						shortestDistances.updateDistanceFromSource(neighborVertex, newDistance);
						previousVertexHashMap.put(neighborVertex, currentVertex);
					}

				}
			}
		}

		for (int vertex : graph.vertices()) {
			shortestPaths.put(vertex, generateShortestPaths(vertex));
		}
	}

	/**
	 * Returns shortest distance from source to given vertex.
	 * 
	 * @param vertex
	 * @return the shortest distance from the source to the given vertex
	 * @throws RuntimeException if shortest distances and paths have not yet been
	 *                          calculated (i.e.
	 *                          {@link #calculateShortestDistancesAndPaths(int)} has
	 *                          not been called).
	 */
	public double getShortestDistance(int vertex) {
		if (shortestDistances == null) {
			throw new RuntimeException("Shortest distances and paths have not been calculated.");
		}
		return shortestDistances.getDistanceFromSource(vertex);
	}

	/**
	 * @param vertex
	 * @return a list of vertices that represents the shortest path from the source
	 *         to the given vertex
	 * @throws RuntimeException if shortest distances and paths have not yet been
	 *                          calculated (i.e.
	 *                          {@link #calculateShortestDistancesAndPaths(int)} has
	 *                          not been called).
	 */
	public List<Integer> getShortestPath(int vertex) {
		if (shortestPaths == null) {
			throw new RuntimeException("Shortest distances and paths have not been calculated.");
		}
		return shortestPaths.get(vertex);
	}

	/**
	 * Private help method that generates the shortest path from the source to the
	 * given vertex
	 * 
	 * @param destinationVertex
	 * @return the shortest path from the source to the given vertex in the form of
	 *         a list of vertices
	 */
	private ArrayList<Integer> generateShortestPaths(int destinationVertex) {
		// Path from source to destinationVertex will be stored in this list
		ArrayList<Integer> shortestPathVertices = new ArrayList<Integer>();

		//
		// We are reverse walking vertices to get to the beginning of the path.
		// Using a temporary stack to reverse the order of vertices
		//
		Stack<Integer> reverseStack = new Stack<Integer>();
		reverseStack.push(destinationVertex);

		int previousVertex = destinationVertex;
		while (previousVertexHashMap.containsKey(previousVertex) && previousVertexHashMap.get(previousVertex) >= 0
				&& previousVertex > 0) {
			previousVertex = previousVertexHashMap.get(previousVertex);
			reverseStack.push(previousVertex);
		}

		// Put node in ArrayList in reversed order
		while (reverseStack.size() > 0)
			shortestPathVertices.add(reverseStack.pop());
		return shortestPathVertices;
	}

}
