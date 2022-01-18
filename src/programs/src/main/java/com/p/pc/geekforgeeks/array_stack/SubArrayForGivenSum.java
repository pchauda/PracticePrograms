package com.p.pc.geekforgeeks.array_stack;

import java.util.Arrays;

/**
 * <p>Given an unsorted array of non-negative integers, find a continuous sub-array which adds to a given number.
 * Example:
 * Input: arr[] = {1, 4, 20, 3, 10, 5}, sum = 33 <br/>
 * Output: Sum found between indexes 2 and 4 <br/>
 * Sum of elements between indices 2 and 4 is 20 + 3 + 10 = 33 </p>
 * <p>
 * Approach: <br/>
 * Keep track of sum from starting index, if the total sum exceeds the required sum then move the start idx till sum is
 * less than or equal to required sum.
 */
public class SubArrayForGivenSum {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 4, 20, 16, 10, 5, 18};
        Pair subArrayIndexes = findSubArrayForGivenSum(arr, 33);
        printSubArray(arr, subArrayIndexes);
    }

    /**
     * Since array contains positive numbers, as soon as the continuous sum exceeds the given sum, we can start dropping
     * initial elements.
     */
    private static Pair findSubArrayForGivenSum(int[] arr, int sum) {
        int startIdx = 0, currSum = arr[0];
        if(currSum == sum) return new Pair(startIdx, startIdx);
        for(int i=1; i <= arr.length; i++) {
            while (currSum > sum && startIdx < arr.length - 1) {
                currSum -= arr[startIdx++];
            }
            if(currSum == sum) return new Pair(startIdx, i-1);
            if(i < arr.length)
                currSum += arr[i];
        }
        return null;
    }

    private static void printSubArray(int[] arr, Pair subArrayIndexes) {
        if(subArrayIndexes == null) {
            System.out.println("No sub array found");
            return;
        }
        int start = subArrayIndexes.a, end = subArrayIndexes.b;
        int[] subArray = new int[end - start + 1];
        for (int i = 0; i < subArray.length; i++) {
            subArray[i] = arr[start + i];
        }
        System.out.println(Arrays.toString(subArray));
    }

    static class Pair {
        int a, b;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}
