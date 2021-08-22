package com.p.pc.facebook.arrays;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * We are given an array A of positive integers, and two positive integers L and R (L <= R).
 * Return the number of (contiguous, non-empty) subarrays such that the value of the maximum array element in that subarray is at least L and at most R.
 *
 * Example :
 * Input:
 * A = [2, 1, 4, 3]
 * L = 2
 * R = 3
 * Output: 3
 * Explanation: There are three subarrays that meet the requirements: [2], [2, 1], [3].
 *
 * Approach for max value within the array:
 * Start with two pointers start and end with both at -1.
 * Iterate on the array, if a[i] >= Low && a[i] <= R then move end pointer to i -- Needed value
 *                       if a[i] > R then move both end and start pointer to i -- Reset value
 *                       if a[i] < Low then leave start and end unchanged
 *                       for each element calculate result += (end - start)
 */
public class SubArrayWithBoundedMaxMin {

    public static void main(String[] args) {
        int[] arr = new int[]{3,0,5,2,6,1};
        // bounded range: 2 <= array's max/min <= 4

        System.out.println("Count of subarray having MAX array element within 2 and 4 is: " + countOfSubArrayWithBoundedMax(arr, 2, 4));
        //Output = 3; [3], [3,0], [2]
        System.out.println("Count of subarray having MIN array element within 2 and 4 is: " + countOfSubArrayWithBoundedMin(arr, 2, 4));
        //Output = 5; [3], [5,2], [2], [5,2,6], [2,6]

        arr = new int[]{0,3,1,2,0,5,1,2};
        System.out.println("Count of subarray having MAX array element within 2 and 4 is: " + countOfSubArrayWithBoundedMax(arr, 2, 4));
        //Output = 14; [3], [3, 0], [1, 3], [1, 3, 0], [2], [2, 1], [2, 1, 3], [2, 1, 3, 0], [0, 2], [0, 2, 1], [0, 2, 1, 3], [0, 2, 1, 3, 0], [2], [2, 1]
        System.out.println("Count of subarray having MIN array element within 2 and 4 is: " + countOfSubArrayWithBoundedMin(arr, 2, 4));
        //Output = 3; [3], [2], [2]
        List<List<Integer>> subArrays = new ArrayList<>();
        countAndCollectSubArraysWithBoundedMax(arr, 2, 4, subArrays);
        System.out.println(subArrays);
    }
    /**
     * Since, the max value of the subarray should be within the range, any array value > high value will reset the end and start pointers.
     * For any array value < low, keep the start and end pointer unchanged as the value can be included in the sub array as long as max is within range
     */
    static int countOfSubArrayWithBoundedMax(int[] arr, int low, int high) {
        int start = -1, end = -1;
        int result = 0;
        for(int i=0; i<arr.length; i++) {
            if(arr[i] >= low && arr[i] <= high) end = i; // Needed value
            if(arr[i] > high) start = end = i; // Reset value as soon as a value greater than the bound appears
            // ignore the cases where arr[i] < low as this value be included in the array as long as max is within range
            result += (end - start);
        }
        return result;
    }

    static int countOfSubArrayWithBoundedMin(int[] arr, int low, int high) {
        int start = -1, end = -1;
        int result = 0;
        for(int i=0; i<arr.length; i++) {
            if(arr[i] >= low && arr[i] <= high ) end = i; // Needed value
            if(arr[i] < low) start = end = i; // Reset value as soon as a value lower than the bound appears
            // ignore the cases where arr[i] > high as this value be included in the array as long as min is within range
            result += (end - start);
        }
        return result;
    }

    /**
     * This method counts the number of sub-arrays as well also collects those sub-arrays
     * @param arr
     * @param low
     * @param high
     * @param subArrays
     * @return
     */
    static int countAndCollectSubArraysWithBoundedMax(int[] arr, int low, int high, List<List<Integer>> subArrays) {
        int start = -1, end = -1;
        int result = 0;
        for(int i=0; i<arr.length; i++) {

            if(arr[i] >= low && arr[i] <= high) end = i; // Needed value
            if(arr[i] > high) start = end = i; // Reset value as soon as a value greater than the bound appears
            // ignore the cases where arr[i] < low as this value be included in the array as long as max is within range
            result += (end - start);
            // only collect the sub arrays when start != end i.e. there is at least one max element present from 0 to ith index
            if(start != end) collectSubArrays(start, i, low, arr, subArrays);
        }
        return result;
    }

    private static void collectSubArrays(int start, int end, int low, int[] arr, List<List<Integer>> subArrays) {
        LinkedList<Integer> tmp = new LinkedList<>();
        boolean shouldCollect = false;
        while(end > start) {
            int value = arr[end--];
            tmp.addFirst(value); // add values to a temporary list, add first to keep the elements order intact
            // only start collecting as soon we hit a first element within the range, don't have to check for high as this method will not be called for high
            if(value >= low) shouldCollect = true;
            if(shouldCollect)
                subArrays.add(new ArrayList<>(tmp)); // add a clone
        }
    }
}
