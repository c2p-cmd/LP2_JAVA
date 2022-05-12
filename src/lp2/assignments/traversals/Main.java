package lp2.assignments.traversals;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println();
        final Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        final int vertices = scanner.nextInt();

        System.out.println("\nNature of graph:\n1. Directed\n2. Undirected");
        final boolean isDirected = scanner.nextInt() == 1;

        final Graph graph = isDirected ? new Graph(vertices, Graph.GraphType.DIRECTED) : new Graph(vertices, Graph.GraphType.UNDIRECTED);

        System.out.println("Which edges to add?");
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                System.out.println("Do you want to add edge going from " + (i) + " to " + (j) + "?");
                System.out.print("Y for yes, N for no: ");
                final char option = scanner.next().charAt(0);
                if (option == 'y' || option == 'Y') {
                    graph.addEdge(i, j);
                }
            }
        }

        System.out.println("Choose source node for BFS =");
        int source = scanner.nextInt();
        System.out.println("\nBFS->");
        graph.breadthFirstTraversal(source);

        System.out.println("\nChoose source node for DFS =");
        source = scanner.nextInt();
        System.out.println("DFS->");
        graph.depthFirstTraversal(source);

        scanner.close();
    }
}
