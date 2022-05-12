package lp2.assignments.greedy.algorithms.shortestpath;

import java.util.*;

public class Dijsktra {
    private static class DistanceInfo {
        private double distance;
        private int vertex;

        public DistanceInfo() {
            this.distance = Integer.MAX_VALUE;
            this.vertex = -1;
        }

        public DistanceInfo(double distance, int vertex) {
            this.distance = distance;
            this.vertex = vertex;
        }

        public double getDistance() {
            return distance;
        }

        public int getVertex() {
            return vertex;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public void setVertex(int vertex) {
            this.vertex = vertex;
        }
    }

    private static class VertexInfo {
        private final int vertexID;
        private final double distance;

        public VertexInfo(final int vertexID, final double distance) {
            this.vertexID = vertexID;
            this.distance = distance;
        }

        public int getVertexID() {
            return vertexID;
        }

        public double getDistance() {
            return distance;
        }
    }

    private static class DistanceComparator implements Comparator<VertexInfo> {
        @Override
        public int compare(VertexInfo v1, VertexInfo v2) {
            return Double.valueOf(v1.distance - v2.distance).intValue();
        }
    }

    private static Map<Integer, DistanceInfo> buildDistanceTable(final WeightedGraph graph, final int source) {
        final HashMap<Integer, DistanceInfo> distanceTable = new HashMap<>();

        // putting all vertices
        for (int v = 0; v < graph.getNumVertices(); v++) {
            distanceTable.put(v, new DistanceInfo());
        }

        // setting first element
        distanceTable.put(source, new DistanceInfo(0, source));

        final VertexInfo sourceVertexInfo = new VertexInfo(source, 0);

        final PriorityQueue<VertexInfo> priorityQueue = new PriorityQueue<>(new DistanceComparator());
        priorityQueue.add(sourceVertexInfo);

        final Map<Integer, VertexInfo> vertexInfoMap = new HashMap<>();
        vertexInfoMap.put(source, sourceVertexInfo);

        while (!priorityQueue.isEmpty()) {
            final int currentVertex = priorityQueue.poll().getVertexID();

            for (Integer vertexNeighbour : graph.getAdjacentVerticesOf(currentVertex)) {
                // new distance from source to neighbour
                final double distance = distanceTable.get(currentVertex).getDistance() + graph.getWeight(currentVertex, vertexNeighbour);

                /* if we find new shortest path to neighbour update
                   the distance and last vertex */
                if (distanceTable.get(vertexNeighbour).getDistance() > distance) {
                    distanceTable.put(vertexNeighbour, new DistanceInfo(distance, currentVertex));

                    final VertexInfo neighbourVertexInfo = vertexInfoMap.get(vertexNeighbour);
                    priorityQueue.remove(neighbourVertexInfo);

                    final VertexInfo newNeighbourVertexInfo = new VertexInfo(vertexNeighbour, distance);
                    priorityQueue.add(newNeighbourVertexInfo);
                    vertexInfoMap.put(vertexNeighbour, newNeighbourVertexInfo);
                }
            }
        }

        return distanceTable;
    }

    public static String shortestPath(final WeightedGraph graph, final int source, final int destination) {
        final Map<Integer, DistanceInfo> distanceTable = buildDistanceTable(graph, source);

        final Stack<Integer> stack = new Stack<>();
        stack.push(destination);

        int previousVertex = distanceTable.get(destination).getVertex();
        while (previousVertex != -1 && previousVertex != source) {
            stack.push(previousVertex);
            previousVertex = distanceTable.get(previousVertex).getVertex();
        }

        if (previousVertex == -1)
            return "There is no path from vertex " + source + " to " + destination;
        else {
            final StringBuilder builder = new StringBuilder();

            builder.append("Smallest path is ").append(source);
            while (!stack.isEmpty()) {
                builder.append("->").append(stack.pop());
            }
            builder.append(" Dijkstra DONE!!\n");

            return builder.toString();
        }
    }
}
