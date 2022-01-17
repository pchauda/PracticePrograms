package com.p.pc.geekforgeeks.array_stack;

/**
 * Calculate the maximum difference between any two numbers of the given array. Large number should appear after the
 * smaller number.
 */
public class MaxDifferenceTwoNumbersInArray {
    public static void main(String[] args) {
        int[] arr = new int[] {-10, -5, -1, -10, -4, -5};
        int maxDiff = findMaxDiff(arr);
        System.out.println("Max diff: " + maxDiff); // Output: 9
    }

    private static int findMaxDiff(int[] arr) {
        if(arr == null || arr.length < 2) return -1;
        if(arr.length == 2) {
            int diff = arr[1] - arr[0];
            return diff > 0 ? diff : -1;
        }
        int maxDiff = Integer.MIN_VALUE, min = arr[0];
        for(int i=1; i < arr.length; i++) {
            int tmpDiff = arr[i] - min;
            if(tmpDiff > maxDiff) maxDiff = tmpDiff;
            if(arr[i] < min) min = arr[i];
        }
        return maxDiff;
    }
}
