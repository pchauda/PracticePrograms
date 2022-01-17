package com.p.pc.facebook.arrays;

import java.util.Arrays;

/**
 * <p>Given an array containing only 0’s, 1’s, and 2’s, sort it in linear time and using constant space.</p>
 * <p>
 * Approach: <br/>
 * Use 3 way quick sort to sort the array using
 * 1. elements less than the pivot
 * 2. elements equal to the pivot
 * 3. elements greater than pivot and here take 1 as pivot.
 */
public class SortAnArrayOf123 {

    public static void main(String[] args) {
        int[] arr = new int[] {0, 1, 2, 1, 0, 1, 2, 0, 0, 2, 2, 1, 1};
        System.out.println("Input array:  " + Arrays.toString(arr));
        modifiedQuickSort(arr);
        System.out.println("Sorted array: " + Arrays.toString(arr));
    }

    private static void modifiedQuickSort(int[] arr) {
        int pivot = 1;
        int start = 0, end = arr.length - 1;
        int mid = 0; // counter also used to track the position of mid-element
        while(mid <= end) {
            if(arr[mid] < pivot) { // encountered 0, move to the start
                // swap the low and mid
                swap(arr, start++, mid++);
            } else if(arr[mid] > pivot) { // encountered 2, move to the end
                // swap end with mid
                swap(arr, end--, mid); // do not increment the mid to check again for the same index as the value at end could be anything
            } else {
                mid++; // encountered 1, just move the pointer
            }
        }
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
