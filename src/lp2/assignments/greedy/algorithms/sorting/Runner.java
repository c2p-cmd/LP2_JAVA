package lp2.assignments.greedy.algorithms.sorting;

import java.util.Scanner;

public class Runner {
    // main for selection sort
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        // create a new array of integers
        System.out.println("Enter array size: ");
        int[] array = new int[scanner.nextInt()];

        System.out.println("Enter array elements: ");

        // take user input to fill array
        for (int i = 0; i < array.length; i++) {
            array[i] = scanner.nextInt();
        }
        // print the array before sorting
        System.out.println("Before sorting:");
        for (int j : array) {
            System.out.print(j + " ");
        }
        // sort the array
        SelectionSort.sort(array);
        // print the array after sorting
        System.out.println("\nAfter sorting:");
        for (int j : array) {
            System.out.print(j + " ");
        }
        scanner.close();
    }
}
