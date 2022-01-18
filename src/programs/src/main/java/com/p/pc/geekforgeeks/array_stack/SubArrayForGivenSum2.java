package com.p.pc.geekforgeeks.array_stack;

import java.util.Arrays;
import java.util.HashMap;

/**
 * <p>Given an unsorted array of integers, find a sub-array that adds to a given number. If there is more than one
 * sub-array with the sum of the given number, print any of them.
 * Example:
 * Input: arr[] = {10, 2, -2, -20, 10}, sum = -10 <br/>
 * Output: Sum found between indexes 0 and 3 => [10, 2, -2, -20]<br/></p>
 * <p>
 * Approach: <br/>
 * Calculate the continuous sum for each index. If running sum is equal to the required sum then return the sub array
 * from start to current index.
 * Else calculate the diff = running sum - required sum, if the diff exists in the hashmap then return the sub array
 * from hashMap index + 1 to current index.
 * Else put the running sum in the hashmap with current index.
 */
public class SubArrayForGivenSum2 {
    public static void main(String[] args) {
        int[] arr = new int[]{10, 2, -3, -20, 10};
        Pair subArrayIndexes = findSubArrayForGivenSum(arr, -23);
        printSubArray(arr, subArrayIndexes); // Output: [2, -3, -20]
    }

    /**
     * Since array contains positive numbers, as soon as the continuous sum exceeds the given sum, we can start dropping
     * initial elements.
     */
    private static Pair findSubArrayForGivenSum(int[] arr, int sum) {
        HashMap<Integer, Integer> sumMap = new HashMap<>();
        int runningSum = 0;
        for(int i=0; i<arr.length; i++) {
            runningSum += arr[i];
            if(runningSum == sum) {
                return new Pair(0, i); // since running sum is equal to sum hence start idx will from the beginning
            }
            int diff = runningSum - sum;
            if(sumMap.containsKey(diff)) {
                return new Pair(sumMap.get(diff) + 1, i);
            } else {
                sumMap.put(runningSum, i);
            }
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
