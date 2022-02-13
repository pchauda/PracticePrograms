package com.p.pc.sorting;

import java.util.Arrays;

/**
 * Quicksort is a sorting algorithm based on the divide and conquer approach where
 * <p>
 * An array is divided into sub-arrays by selecting a pivot element (element selected from the array).
 * <p>
 * While dividing the array, the pivot element should be positioned in such a way that elements less than pivot are kept
 * on the left side and elements greater than pivot are on the right side of the pivot.
 * The left and right sub-arrays are also divided using the same approach. This process continues until each sub-array
 * contains a single element. At this point, elements are already sorted. Finally, elements are combined to form a
 * sorted array.
 * <p>
 * Complexity: Worst case O(n^2), Best case/Average Case: O(n log n)
 * Worst case happens when the pivot element picked is always the largest or smallest element. If pivot element is always
 * the last index and if the array is already sorted then QuickSort will result into worst case scenario.
 * <p>
 * Quick Sort is Unstable sort i.e. order of equal elements is not retained.
 * <p>
 * Quick Select algorithm can be used to find the Kth smallest element from an array.
 */
public class QuickSortAndSelectAlgo {

    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 10, 5, 9, -12, -20, 11}; // Output: -20, -12, 2, 3, 5, 9, 10, 11
        System.out.println("Original array: " + Arrays.toString(arr));
        quickSort(arr, 0, arr.length - 1);
        System.out.println("Sorted array: " + Arrays.toString(arr));
        System.out.println(kthSmallest(arr, 7)); // Output: 10
    }

    static void quickSort(int arr[], int begin, int end) {
        if (begin < end) {
            // find partition index such that all elements before this are smaller and after this as greater
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    static int partition(int[] arr, int start, int end) {
        int pivot = arr[end]; // select a pivot
        int j = start; // j wil be used as final position of the pivot
        for (int i = start; i < end; i++) {
            if (arr[i] < pivot) {
                swap(arr, i, j++); // this swaps ensures, all elements smaller than the pivot are on the left
            }
        }
        swap(arr, j, end);
        return j;
    }
    // Find Kth smallest element in the array
    static int kthSmallest(int[] arr, int k) {
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int pIdx = partition(arr, start, end);
            if (pIdx == k - 1) return arr[pIdx];
            else if (pIdx > k - 1) end = pIdx - 1;
            else start = pIdx + 1;
        }
        return Integer.MIN_VALUE;
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
