package model;

import java.util.HashMap;

/**
 * An instance of this class stores all vertices together with their shortest
 * distance from the source (calculated so far)
 * 
 * @author SmartaElbilarAB
 */
public class ShortestDistances {
	// Hash map that stores a vertex index (Integer) together with
	// it's shortest distance from the source (Double) (calculated so far)
	private HashMap<Integer, Double> distanceMap = new HashMap<Integer, Double>();

	/**
	 * Updates the shortest distance calculated so far between the source and the
	 * given vertex
	 * 
	 * @param distance shortest distance so far
	 */
	public void updateDistanceFromSource(int vertex, double newDistance) {
		distanceMap.put(vertex, newDistance);
	}

	/**
	 * Gets the shortest distance calculated so far between the source and the given
	 * vertex
	 * 
	 * @return shortest distance calculated so far
	 */
	public double getDistanceFromSource(int vertex) {
		return distanceMap.get(vertex);
	}

}