package com.p.pc.sorting;

import java.util.Arrays;

/**
 * Algorithm:
 *  Merge Sort is one of the most popular sorting algorithms that is based on the principle of Divide and Conquer Algorithm.
 *  Here, a problem is divided into multiple sub-problems. Each sub-problem is solved individually. Finally, sub-problems
 *  are combined to form the final solution.
 *
 * Time complexity: O(n log n)
 *
 * Merge Sort is a Stable Sort.
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] array = new int[]{4, 1, 3, 5, 9, 8, 7};
        System.out.println("Original array: " + Arrays.toString(array));
        mergeSort(array, 0, array.length-1);
        System.out.println("Sorted Array: " + Arrays.toString(array));
    }

    static void mergeSort(int[] array, int start, int end) {
        if(start < end) {
            int mid = (start + end)/2;
            mergeSort(array, start, mid);
            mergeSort(array, mid + 1, end);
            merge(array, start, end, mid);
        }
    }

    static void merge(int[] array, int start, int end, int mid) {
        int l = mid - start + 1;
        int r = end - mid;

        int[] L = new int[l];
        int[] R = new int[r];
        // copy elements to left array
        for(int i=0; i < l; i++) {
            L[i] = array[start + i];
        }
        // copy elements to right array
        for(int i=0; i < r; i++) {
            R[i] = array[(mid + 1) + i];
        }
        int i=0,j=0,k=start;
        while(i < l && j < r) {
            // Equals condition here is important for retaining the stable-sort nature of merge sort
            int value = ( L[i] <= R[j] ? L[i++] : R[j++] );
            array[k++] = value;
        }

        while(i < l) {
            array[k++] = L[i++];
        }
        while(j < r) {
            array[k++] = R[j++];
        }
    }
}
