package lp2.assignments.greedy.algorithms.shortestpath;

import lp2.assignments.traversals.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Weighted graph using adjacency matrix representation
public class WeightedGraph {
    private final double[][] adjMatrix;
    private final int numVertices;
    private final Graph.GraphType type;

    // Constructor
    public WeightedGraph(int numVertices, Graph.GraphType type) {
        this.numVertices = numVertices;
        this.type = type;
        adjMatrix = new double[numVertices][numVertices];
    }

    // add edge to graph
    public void addEdge(int source, int destination, double weight) {
        adjMatrix[source][destination] = weight;
        if (type == Graph.GraphType.UNDIRECTED) {
            adjMatrix[destination][source] = weight;
        }
    }

    // adjacent vertices
    public List<Integer> getAdjacentVerticesOf(final int v) {
        final List<Integer> vertices = new ArrayList<>();

        for (int vertex = 0; vertex < numVertices; vertex++) {
            if (isEdge(v, vertex))
                vertices.add(vertex);
        }

        vertices.sort(Integer::compareTo);
        return vertices;
    }

    // get weight of edge
    public double getWeight(int source, int destination) {
        return adjMatrix[source][destination];
    }

    // is edge
    public boolean isEdge(int source, int destination) {
        return adjMatrix[source][destination] != 0;
    }

    // get number of vertices
    public int getNumVertices() {
        return numVertices;
    }

    // get type of graph
    public Graph.GraphType getType() {
        return type;
    }

    // to string
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(numVertices).append(" vertices, ").append(type).append(" graph\n");
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                sb.append(adjMatrix[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
