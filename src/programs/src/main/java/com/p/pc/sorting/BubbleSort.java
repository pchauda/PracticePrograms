package com.p.pc.sorting;

import java.util.Arrays;

/**
 * Each iteration of the bubble sort will place the max/min at the end.
 *
 * Algorithm:
 * Starting from first index, compare the first two elements and swap if required. (if a > b then swap else not)
 * Similarly, Compare the second and third element and so on. At the end, the max element will be the end of the array.
 * Repeat this process, till no swap occurs.
 *
 * Complexity: Avg and worst case: O(n^2), best case O(n) if the list is already sorted
 *
 * Bubble Sort is a Stable Sort.
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = new int[]{6, -3, 1, 5, 9, 8, -10, 4, 56, 4, 56, 46, -10};
        System.out.println("Original array: " + Arrays.toString(arr));
        bubbleSort(arr);
        System.out.println("Sorted Array: " + Arrays.toString(arr));
    }

    private static void bubbleSort(int[] arr) {
        // loop to access each array element
        for(int i=0; i < arr.length - 1; i++) {
            // improvised version of bubble sort to break early if no swap happened in any iteration i.e. array already
            // sorted, so break early.
            boolean swapHappened = false;
            // loop to compare array elements, since last element will be in its final position after each iteration
            for(int j=0; j < arr.length - 1 - i; j++) {
                if(arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapHappened = true;
                }
            }
            if(!swapHappened) break;
        }
    }
}
