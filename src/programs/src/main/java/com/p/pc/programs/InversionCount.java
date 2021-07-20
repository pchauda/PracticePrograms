package com.p.pc.programs;

import java.util.Arrays;

/**
 * Inversion Count for an array indicates – how far (or close) the array is from being sorted.
 * If the array is already sorted, then the inversion count is 0, but if the array is sorted in the reverse order,
 * then the inversion count is the maximum. total inversions = n * (n-1)/2
 *
 * Apply advance merge sort to find out inversion count in an optimized way. Idea here is to breakdown elements
 * in a group of 2 and count the inversions in left array + right array + during merge step and bubble it to the top.
 */
public class InversionCount {

    public static void main(String[] args) {
        int[] array = new int[]{4,6,3,7,1,2,9};
        System.out.println(inversionCount(array)); // Output = 10
        System.out.println(inversionCountOptimized(array)); // Output = 10
    }

    static int inversionCount(int[] array) {
        int count= 0;
        for(int i=0; i < array.length; i++) {
            for(int j=i+1; j < array.length; j++) {
                if(array[i] > array[j])
                    count++;
            }
        }
        return count;
    }

    static int inversionCountOptimized(int[] array) {
        return mergeSortWithCount(array, 0, array.length - 1);
    }

    static int mergeSortWithCount(int[] array, int start, int end) {
        int count=0;
        if(start < end) {
            int mid = (start + end)/2;
            count += mergeSortWithCount(array, start, mid);
            count += mergeSortWithCount(array, mid + 1, end);
            count += mergeWithCount(array, start, mid, end);
        }
        return count;
    }

    static int mergeWithCount(int[] arr, int start, int mid, int end) {
        int[] left = Arrays.copyOfRange(arr, start, mid + 1);
        int[] right = Arrays.copyOfRange(arr, mid + 1, end + 1);

        int i=0, j=0, k=start, swaps = 0;
        while(i < left.length && j < right.length) {
            if(left[i] <= right[j])
                arr[k++] = left[i++];
            else {
                arr[k++] = right[j++];
                // If left element at index i is larger than that all elements starting i will be larger and hence can be counted towards swaps
                swaps += left.length - i;
            }
        }
        while(i < left.length)
            arr[k++] = left[i++];
        while(j < right.length)
            arr[k++] = right[j++];
        return swaps;
    }
}
