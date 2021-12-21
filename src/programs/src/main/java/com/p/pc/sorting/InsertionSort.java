package com.p.pc.sorting;

import java.util.Arrays;

/**
 * Algorithm:
 * Iterate over the array, compare the current element (key) to its predecessor. If the key element is smaller than
 * its predecessor, compare it to the elements before. Move the greater elements one position up to make space for
 * the swapped element. Every iteration of the insertion sort, puts the elements till that iteration in sorted order.
 *
 * Insertion sort is faster if value of n <= 8
 * If array is already almost sorted then insertion sort complexity will be O(n).
 *
 * Worst case complexity = O(n^2)
 *
 * Insertion Sort is a Stable Sort.
 */
public class InsertionSort {
    public static void main(String[] args) {
        int[] arr = new int[] {6, 3, 1, 5, 9, 8};
        System.out.println("Original Array: " + Arrays.toString(arr));
        insertionSort(arr);
        System.out.println("Sorted Array: " + Arrays.toString(arr));
    }

    private static void insertionSort(int[] arr) {
        for(int i=1; i < arr.length; i++) {
            int key = arr[i];
            int j = i-1;
            while(j>=0 && arr[j] > key) {
                arr[j+1] = arr[j]; // shift the element to the right to make space for the key
                j--;
            }
            // place the key at its sorted position
            arr[j + 1] = key;
        }
    }

}
