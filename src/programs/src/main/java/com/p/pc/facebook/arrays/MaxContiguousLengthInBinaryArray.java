package com.p.pc.facebook.arrays;

import java.util.HashMap;

/**
 * Given an array containing binary (0 or 1) numbers, return the maximum length of a contiguous sub-array with an equal
 * number of 0 and 1.
 *
 * Input: nums = [0,1,0]
 * Output: 2
 * Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 *
 * Approach:
 * Use the prefix sum logic to calculate the sum at each index, consider 0 as -1 for the sum.
 * Store the sum and its first occurring index in a hashMap. If the same sum appears next, then that means there are
 * equal number of 0 and 1's from the first index of the sum till the current index and thus we can compute the length
 * and max length for each such sum.
 */
public class MaxContiguousLengthInBinaryArray {
    public static void main(String[] args) {
        int[] arr = new int[]{1,0,0,1,1,0,1,1,1,0,0,0,1};
        System.out.println("Max length of the contiguous array having eq 0 & 1 is: " + maxLength(arr)); // Output: 12
    }

    private static int maxLength(int[] arr) {
        HashMap<Integer, Integer> sumToIndexMap = new HashMap<>();
        sumToIndexMap.put(0, -1); // Put a 0 sum at index -1
        int sum = 0, maxLength = 0;
        for(int i =0; i<arr.length; i++) {
            sum += (arr[i] == 0 ? -1 : 1);
            if(sumToIndexMap.containsKey(sum)) {
                // If sum is present then it means there are equal number of 0 and 1's from starting index to this index
                // hence length can be calculated as (current index - previous index)
                maxLength = Math.max(maxLength, i - sumToIndexMap.get(sum));
            } else {
                sumToIndexMap.put(sum, i);
            }
        }
        return maxLength;
    }
}
