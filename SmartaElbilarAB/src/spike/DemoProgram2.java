package spike;

import java.io.IOException;
import java.util.HashMap;

import algorithm.Dijkstra;
import model.Graph;

public class DemoProgram2 {
	
	public static void main(String args[]) throws IOException {
		Graph graph;
		graph = new Graph("resources//Demo2Map.txt"); 
		
		Dijkstra dijkstra = new Dijkstra(graph);
		dijkstra.calculateShortestDistancesAndPaths(17);
		System.out.println("Shortest distances and paths from source 17 to nearst charging station:");
		
		double toVertex1 = dijkstra.getShortestDistance(1);
		double toVertex25 = dijkstra.getShortestDistance(25);
		double toVertex28 = dijkstra.getShortestDistance(28);
		double nearestChStation = Double.min(Double.min(toVertex1, toVertex25), toVertex28);
		HashMap<Double, Integer> hash =  new HashMap<>();
		hash.put(toVertex1, 1);
		hash.put(toVertex25, 25);
		hash.put(toVertex28, 28);
		
		for(double r: hash.keySet()) {
			if(r == nearestChStation) {
				int destination = hash.get(r);
				System.out.print("17 -> " + destination + "\n" + 
						 "    Shortest distance: " + dijkstra.getShortestDistance(destination) + "\n" + 
				         "    Shortest path contains vertices: " + dijkstra.getShortestPath(destination));
			}
		}
	
		
	}
}
