package com.p.pc.facebook;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * <p>Given an integer array, move all elements that are 0 to the left while maintaining the order of other elements in
 * the array. The array has to be modified in-place.</p>
 *
 * <p>Approach:</p>
 * Use reader and writer index. Move the reader index from (start to end for RightMove) or (end to start for LeftMove)
 * Idea here is to stop the writer index when reader encounters a zero else write reader's value at writer index and move writer.
 *
 * If arr[reader] != 0 then arr[writer--] = arr[reader] followed by reader--, for Left Move
 * If arr[reader] != 0 then arr[writer++] = arr[reader] followed by reader++, for Right Move
 */
public class MoveZeroesToLeftRight {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 4, 0, -1, 10, 11, 34, 0, 4, 12, 3, 0, 0, 3, 6, 7, 10, 0, 0, -1, 0, -4, 0, 0, 23, 45, 90, 0, 0, 0, 23, 2, 2, 3, 4, 0, 0, 5, 6, 0};
        moveZeroesToLeftOptimized(arr);
        System.out.println("Moved zeros to Left: " + Arrays.toString(arr));
        moveZeroesToRightOptimized(arr);
        System.out.println("Moved zeros to Right: " + Arrays.toString(arr));
        moveZeroesToLeft(arr);
        System.out.println("Moved zeros to Left (insertion sort): " + Arrays.toString(arr));

    }
    // O(n) solution using reader and writer index
    private static void moveZeroesToLeftOptimized(int[] arr) {
        long start = System.nanoTime();
        int w = arr.length - 1;
        for(int r = arr.length - 1; r >= 0; r--) {
            if(arr[r] !=0) {
                if(r != w)
                    swap(arr, r, w);
                w--;
            }
        }
        long end = System.nanoTime();
        System.out.println("Time taken in moving left: " + (end - start) / (1000) + " us");
    }

    private static void swap(int[] arr, int r, int w) {
        int temp = arr[r];
        arr[r] = arr[w];
        arr[w] = temp;
        return;
    }

    // O(n) solution using reader and writer index
    private static void moveZeroesToRightOptimized(int[] arr) {
        long start = System.nanoTime();
        int w = 0, l = arr.length;
        for(int r=0; r < l; r++) {
            if(arr[r] !=0) {
                if(r != w)
                    arr[w] = arr[r];
                w++;
            }
        }
        while(w < l) {
            arr[w++] = 0;
        }
        long end = System.nanoTime();
        System.out.println("Time taken in moving right: " + (end - start)  + " ns");
    }

    // Using insertion sort O(n2) solution, not optimal
    private static void moveZeroesToLeft(int[] arr) {
        long start = System.nanoTime();
        if(arr.length <= 1) return;
        for(int i=1; i < arr.length; i++) {
            if(arr[i] == 0) {
                int j = i-1;
                while(j >= 0 && arr[j] != 0) {
                    arr[j+1] = arr[j];
                    j--;
                }
                arr[j+1] = 0;
            }
        }
        long end = System.nanoTime();
        System.out.println("Time taken: " + (end - start) + " ns");
    }
}
