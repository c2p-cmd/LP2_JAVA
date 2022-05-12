package lp2.assignments.pathfinding.bitpuzzle;

import java.util.*;

public class Puzzle {
    final private int[] row = { 1, 0, -1, 0 };
    final private int[] col = { 0, -1, 0, 1 };

    private int calculateCost(int[][] initial, int[][] goal) {
        int count = 0;
        int n = initial.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (initial[i][j] != 0 && initial[i][j] != goal[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    private void printMatrix(int[][] matrix) {
        for (int[] rowIntegers : matrix) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(rowIntegers[j] + " ");
            }
            System.out.println();
        }
    }

    private boolean isSafe(int x, int y) {
        final int dimension = 3;
        return (x >= 0 && x < dimension && y >= 0 && y < dimension);
    }

    private void printPath(Node root) {
        if (root == null) {
            return;
        }
        printPath(root.parent);
        printMatrix(root.matrix);
        System.out.println();
    }

    private boolean isSolvable(int[][] matrix) {
        int count = 0;
        List<Integer> array = new ArrayList<>();

        for (int[] ints : matrix) {
            for (int j = 0; j < matrix.length; j++) {
                array.add(ints[j]);
            }
        }

        Integer[] anotherArray = new Integer[array.size()];
        array.toArray(anotherArray);

        for (int i = 0; i < anotherArray.length - 1; i++) {
            for (int j = i + 1; j < anotherArray.length; j++) {
                if (anotherArray[i] != 0 && anotherArray[j] != 0 && anotherArray[i] > anotherArray[j]) {
                    count++;
                }
            }
        }

        return count % 2 == 0;
    }

    private void solve(int[][] initial, int[][] goal, int x, int y) {
        final PriorityQueue<Node> pq = new PriorityQueue<>(1000,
                Comparator.comparingInt(a -> (a.cost + a.level))
        );
        final Node root = new Node(initial, x, y, x, y, 0, null);
        root.cost = calculateCost(initial, goal);
        pq.add(root);

        while (!pq.isEmpty()) {
            Node min = pq.poll();
            if (min.cost == 0) {
                printPath(min);
                return;
            }

            for (int i = 0; i < 4; i++) {
                if (isSafe(min.x + row[i], min.y + col[i])) {
                    Node child = new Node(min.matrix, min.x, min.y, min.x + row[i], min.y + col[i], min.level + 1, min);
                    child.cost = calculateCost(child.matrix, goal);
                    pq.add(child);
                }
            }
        }
    }

    public void solveIfPossible(final int[][] initialState, final int[][] goalState, final int x, final int y) {
        if (isSolvable(initialState))
            solve(initialState, goalState, x, y);
        else
            throw new RuntimeException("This isn't solvable.");
    }
}
