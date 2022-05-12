package lp2.assignments.traversals;

import java.util.Arrays;

public class Graph {
    // enum to indicate directed or undirected graph
    public enum GraphType {
        DIRECTED, UNDIRECTED
    }

    // create graph using adjacency matrix
    private final boolean[][] adjMatrix;
    private final int numVertices;
    private final GraphType graphType;

    public Graph(final int numVertices, final GraphType graphType) {
        this.graphType = graphType;
        this.numVertices = numVertices;
        adjMatrix = new boolean[numVertices][numVertices];
    }

    private void isVertexValid(final int vertex) {
        if (vertex < 0 || vertex >= numVertices) {
            throw new IllegalArgumentException("Vertex " + vertex + " is not between 0 and " + (numVertices - 1));
        }
    }

    public void addEdge(final int source, final int destination) {
        isVertexValid(source);
        isVertexValid(destination);

        adjMatrix[source][destination] = true;
        if (GraphType.UNDIRECTED == graphType) {
            adjMatrix[destination][source] = true;
        }
    }

    public void removeEdge(final int source, final int destination) {
        isVertexValid(source);
        isVertexValid(destination);

        adjMatrix[source][destination] = false;
        if (GraphType.UNDIRECTED == graphType) {
            adjMatrix[destination][source] = false;
        }
    }

    public boolean isEdge(final int source, final int destination) {
        isVertexValid(source);
        return adjMatrix[source][destination];
    }

    @Override
    public String toString() {
        final StringBuilder buffer = new StringBuilder();

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (adjMatrix[i][j]) {
                    buffer.append('|').append(" ");
                } else {
                    buffer.append('_').append(" ");
                }
            }
            buffer.append("\n");
        }

        return buffer.toString();
    }

    // dept first traversal
    public void depthFirstTraversal(final int source) {
        final boolean[] visited = new boolean[numVertices];
        Arrays.fill(visited, false);
        depthFirstTraversal(source, visited);
        System.out.println();
    }

    private void depthFirstTraversal(final int source, final boolean[] visited) {
        visited[source] = true;
        System.out.print(source + " ");

        for (int i = 0; i < numVertices; i++) {
            if (isEdge(source, i) && !visited[i]) {
                depthFirstTraversal(i, visited);
            }
        }
    }

    // breadth first traversal
    public void breadthFirstTraversal(final int source) {
        final boolean[] visited = new boolean[numVertices];
        Arrays.fill(visited, false);
        final int[] queue = new int[numVertices];
        int front = 0, rear = 0;
        queue[rear++] = source;
        visited[source] = true;

        while (front != rear) {
            final int vertex = queue[front++];
            System.out.print(vertex + " ");

            for (int i = 0; i < numVertices; i++) {
                if (adjMatrix[vertex][i] && !visited[i]) {
                    visited[i] = true;
                    queue[rear++] = i;
                }
            }
        }
    }
}
