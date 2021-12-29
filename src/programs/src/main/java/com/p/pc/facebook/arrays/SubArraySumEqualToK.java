package com.p.pc.facebook.arrays;

import java.util.HashMap;

/**
 * Given an array, find count of all sub-arrays having sum equal to a given number.
 *
 * Approach:
 *  Follow two sum approach here, keep storing the prefix sum in a HashMap and calculate the running sum.
 *  If running sum = prefix sum + (sum of sub array i.e. target sum) i.e. if (runningSum - targetSum) is present in the
 *  HashMap then that means there are these many prefix sums which can be removed from the running sum to get the target sum.
 *  That means there are exactly these many sub arrays whose sum will be equal to the target sum. Follow this approach for
 *  all elements of the array.
 */
public class SubArraySumEqualToK {

    public static void main(String[] args) {
        int arr[] = { 2, 3, 1, -3, -1, 4, 2 };
        int sum = 4;
        int n = arr.length;
        System.out.println(findSubArraySum(arr, n, sum)); // Output: 3
    }

    // Function to find number of sub-arrays with target exactly equal to k.
    static int findSubArraySum(int arr[], int n, int target) {
        // HashMap to store number of sub-arrays starting from index zero having particular value of target.
        HashMap<Integer, Integer> prevSum = new HashMap<>();
        int res = 0;
        // Sum of elements so far.
        int runningSum = 0;
        for (int i = 0; i < n; i++) {
            // Add current element to target so far.
            runningSum += arr[i];
            // If runningSum is equal to desired target, then a new sub-array is found starting from the index 0. So increase count of sub-arrays.
            if (runningSum == target)
                res++;
            // runningSum exceeds the target by prefix sum. if we can find a prefix sum which is equal
            // to (running sum - target) then these many sub-arrays having this prefix sum can be excluded from runningSum
            // to get to the target hence increase the result count by these many sub-arrays' count.
            res += prevSum.getOrDefault(runningSum - target, 0);

            // Add runningSum value to the hashMap, increase the count by 1 if running sum is already present in hashmap
            prevSum.compute(runningSum, (k, v) -> v == null ? 1 : v + 1);
        }
        return res;
    }
}