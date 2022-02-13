package com.p.pc.geekforgeeks.array_stack;

/**
 * Calculate the maximum difference between any two numbers (larger - smaller) of the given array. Large number should
 * appear after the smaller number.
 * <p>
 * Approach: <br/>
 * Iterate over the array and keep track of the minimum number seen so far. For each element of the array, calculate diff
 * and store it if maximum and also adjust the minimum number based of the current element seen from the array.
 */
public class MaxDifferenceTwoNumbersInArray {
    public static void main(String[] args) {
        System.out.println("Max diff: " + findMaxDiff(new int[]{-10, -5, -1, -7, -4, -5})); // Output: 9
        System.out.println("Max diff: " + findMaxDiff(new int[]{-10, -15, -17, -19})); // Output: -1
    }

    private static int findMaxDiff(int[] arr) {
        if (arr == null || arr.length < 2) return -1;
        if (arr.length == 2) {
            int diff = arr[1] - arr[0];
            // If diff is negative then larger number is before the smaller number which contradicts the requirement hence return -1
            return diff > 0 ? diff : -1;
        }
        int maxDiff = Integer.MIN_VALUE, min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            int tmpDiff = arr[i] - min;
            if (tmpDiff > maxDiff) maxDiff = tmpDiff;
            if (arr[i] < min) min = arr[i];
        }
        return maxDiff > 0 ? maxDiff : -1;
    }
}
