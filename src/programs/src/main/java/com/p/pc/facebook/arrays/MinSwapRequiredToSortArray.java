package com.p.pc.facebook.arrays;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Calculate the minimum number of swaps required to sort a given array.
 *
 * Approach:
 *  Create a temporary sorted array which will have all elements in its sorted position. Now, for each element of
 *  original compare it with the temp sorted array, if it does not match then swap it with the sorted element in the original array.
 */
public class MinSwapRequiredToSortArray {
    public static void main(String[] args) {
        MinSwapRequiredToSortArray obj = new MinSwapRequiredToSortArray();
        int[] arr = new int[] {31, 33, 14, 21, 65, 50};
        System.out.println("Minimum swaps required are: " + obj.minSwapsRequired(arr)); // Output: 3
    }

    private int minSwapsRequired(int[] arr) {
        int swaps = 0;
        int[] tmp = Arrays.copyOf(arr, arr.length);
        Arrays.sort(tmp);
        // Hashmap to store each value of the original array and its index
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<arr.length; i++) {
            map.put(arr[i], i);
        }
        // check each element of arr to see if it is in its correct position or not by comparing it with the sorter array
        // if not, then swap the element with sorted element for that position
        for(int i=0; i<arr.length; i++) {
            int original = arr[i];
            int sorted = tmp[i];
            if(original != sorted) {
                swaps++;
                int swapIdx = map.get(sorted); // find the index of the sorted element in the original array
                swap(arr, i, swapIdx); // swap values in the array
                // update the hashmap for swapped values
                map.put(sorted, i);
                map.put(original, swapIdx);
            }
        }
        return swaps;
    }

    private void swap(int[] arr, int i, int swapIdx) {
        int tmp = arr[i];
        arr[i] = arr[swapIdx];
        arr[swapIdx] = tmp;
    }

}
