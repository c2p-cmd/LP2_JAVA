package lp2.assignments.greedy.algorithms.shortestpath;

import lp2.assignments.traversals.Graph;

import java.util.Scanner;

import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        out.println("Enter number of vertices: ");
        final int vertices = scanner.nextInt();

        out.println("Graph is directed? (y/n): ");
        final boolean directed = scanner.next().equalsIgnoreCase("y");
        final Graph.GraphType graphType = directed ? Graph.GraphType.DIRECTED : Graph.GraphType.UNDIRECTED;

        final WeightedGraph graph = new WeightedGraph(vertices, graphType);

        out.println("Enter number of edges: ");
        final int edges = scanner.nextInt();

        for (int i = 0; i < edges; i++) {
            out.println("Enter edges & weight ");
            final int v1 = scanner.nextInt();
            final int v2 = scanner.nextInt();
            final double weight = scanner.nextDouble();
            graph.addEdge(v1, v2, weight);
        }

        out.println("Enter source and destination:\nChoose from '0' to '" + (vertices-1) + "'. ");
        final int source = scanner.nextInt();
        final int destination = scanner.nextInt();
        scanner.close();

        if (source < 0 || source >= vertices || destination < 0 || destination >= vertices) {
            out.println("The vertex is not valid.");
        } else {
            out.println(Dijsktra.shortestPath(graph, source, destination));
        }
    }
}
