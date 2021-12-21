package com.p.pc.sorting;

import java.util.Arrays;
import java.util.StringJoiner;

/**
 * Find the minimum element in the unsorted array, place it to the first position, repeat the same steps for remaining
 * unsorted array.
 *
 * Time complexity is O(n^2) and it is worse than the insertion sort.
 * Selection sort requires at most n swaps, however, bubble sort may require n * n-1 swaps. So if swaps are costlier
 * then Selection sort can be used compared to Bubble sort.
 *
 * Selection Sort is Unstable sort i.e. order of equal elements is not retained.
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] arr = new int[]{6, 3, 1, 5, 9, 8};
        System.out.println("Original array: " + Arrays.toString(arr));
        selectionSort(arr);
        System.out.println("Sorted Array: " + Arrays.toString(arr));
    }

    private static void selectionSort(int[] arr) {
        for(int i=0; i < arr.length; i++) {
            // find the index for minimum element, swap i with minimum index
            int minIdx = i;
            for(int j= i+1; j<arr.length; j++) {
                if(arr[j] < arr[minIdx])
                    minIdx = j;
            }
            if(minIdx != i)
                swap(arr, i, minIdx);
        }
    }

    private static void swap(int[] arr, int i, int minIdx) {
        int temp = arr[i];
        arr[i] = arr[minIdx];
        arr[minIdx] = temp;
    }
}
