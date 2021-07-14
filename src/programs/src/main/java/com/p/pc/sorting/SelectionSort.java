package com.p.pc.sorting;

import java.util.Arrays;
import java.util.StringJoiner;

/**
 * Find the minimum element in the unsorted array, place it to the first position, repeat the same steps for remaining
 * unsorted array.
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] arr = new int[]{6, 3, 1, 5, 9, 8};
        printArray(arr);
        selectionSort(arr);
        System.out.println("Sorted Array:");
        printArray(arr);
    }

    private static void selectionSort(int[] arr) {
        for(int i=0; i < arr.length; i++) {
            // find the index for minimum element, swap i with minimum index
            int minIdx = i;
            for(int j= i+1; j<arr.length; j++) {
                if(arr[j] < arr[minIdx])
                    minIdx = j;
            }
            swap(arr, i, minIdx);
        }
    }

    private static void swap(int[] arr, int i, int minIdx) {
        int temp = arr[i];
        arr[i] = arr[minIdx];
        arr[minIdx] = temp;
    }

    static void printArray(int[] arr) {
        StringJoiner j = new StringJoiner(",", "[","]");
        Arrays.stream(arr).boxed().forEach(t -> j.add(Integer.toString(t)));
        System.out.println(j.toString());
    }
}
