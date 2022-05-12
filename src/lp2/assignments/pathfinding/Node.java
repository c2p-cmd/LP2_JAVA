package lp2.assignments.pathfinding;

import java.util.*;

public class Node implements Comparable<Node> {
    // id for readability of result purposes
    private static int idCounter = 0;
    public int id;

    // Parent in the path
    public Node parent = null;

    public List<Edge> neighbors;

    // Evaluation functions
    public double fScore = Double.MAX_VALUE;
    public double gScore = Double.MAX_VALUE;
    // Hardcoded heuristic
    public double hScore;

    Node(double h) {
        this.hScore = h;
        this.id = idCounter++;
        this.neighbors = new ArrayList<>();
    }

    @Override
    public int compareTo(Node n) {
        return Double.compare(this.fScore, n.fScore);
    }

    public static class Edge {
        Edge(int weight, Node node){
            this.weight = weight;
            this.node = node;
        }

        public int weight;
        public Node node;
    }

    public void addBranch(int weight, Node node){
        Edge newEdge = new Edge(weight, node);
        neighbors.add(newEdge);
    }
}
