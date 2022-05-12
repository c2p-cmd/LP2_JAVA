package lp2.assignments.pathfinding.bitpuzzle;

import java.util.Arrays;
import java.util.Scanner;

public class BitPuzzle {
    private final static int[][] GOAL_STATE = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 0}
    };

    public static void main(String[] args) {
        final int[][] initial = new int[3][3];
        final int[] ctr = {0, 0, 0};
        final int x = 1, y = 0;

        final Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 1st row: ");
        Arrays.stream(scanner.nextLine().split(" ")).forEach(row -> initial[0][ctr[0]++] = Integer.parseInt(row));

        System.out.println("Enter 2nd row: ");
        Arrays.stream(scanner.nextLine().split(" ")).forEach(row -> initial[1][ctr[1]++] = Integer.parseInt(row));

        System.out.println("Enter 3rd row: ");
        Arrays.stream(scanner.nextLine().split(" ")).forEach(row -> initial[2][ctr[2]++] = Integer.parseInt(row));
        scanner.close();

        System.out.println("\nInitial State");
        Arrays.stream(initial).forEach(array -> System.out.println(Arrays.toString(array)));

        System.out.println("\nGoal State");
        Arrays.stream(GOAL_STATE).forEach(array -> System.out.println(Arrays.toString(array)));

        System.out.println("\nSolving:\n");
        new Puzzle().solveIfPossible(initial, GOAL_STATE, x, y);
        System.out.println("Complete.");
    }
}
