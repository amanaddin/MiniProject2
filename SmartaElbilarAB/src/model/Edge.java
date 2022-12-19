package model;

/**
 * An instance of this class represents an edge in a graph
 * The direction of the edge is from startVertex to endVertex
 * 
 * @author SmartaElbilarAB
 */
public class Edge {
    private int startVertex, endVertex; //indices of vertices
    private double length; //distance between the start vertex and end vertex.
    /**
     * Constructs graph edge
     */
    public Edge(int startVertex, int endVertex, double length) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.length = length;
    }

    /**
     * @return start vertex of edge
     */
    public int startVertex() { return startVertex; }

    /**
     * @return end vertex of edge
     */
    public int endVertex() { return endVertex; }

    /**
     * @return length of edge 
     */
    public double length() { return length; }
}
