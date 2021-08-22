package com.p.pc.facebook.arrays;

import java.util.Arrays;
import java.util.Stack;

/**
 * You are given an array arr of N integers. For each index i, you are required to determine the number of contiguous subarrays that fulfills the following conditions:
 * The value at index i must be the maximum element in the contiguous subarrays, and
 * These contiguous subarrays must either start from or end on index i.
 * Signature
 * int[] countSubarrays(int[] arr)
 * Input
 * Array arr is a non-empty list of unique integers that range between 1 to 1,000,000,000
 * Size N is between 1 and 1,000,000
 * Output
 * An array where each index i contains an integer denoting the maximum number of contiguous subarrays of arr[i]
 * Example:
 * arr = [3, 4, 1, 6, 2]
 * output = [1, 3, 1, 5, 1]
 * Explanation:
 * For index 0 - [3] is the only contiguous sub-array that starts (or ends) with 3, and the maximum value in this subarray is 3.
 * For index 1 - [4], [3, 4], [4, 1]
 * For index 2 - [1]
 * For index 3 - [6], [6, 2], [1, 6], [4, 1, 6], [3, 4, 1, 6]
 * For index 4 - [2]
 * So, the answer for the above input is [1, 3, 1, 5, 1]
 */
public class ContiguousSubArrayCount {

    public static void main(String[] args) {
        int[] arr = new int[] {3, 4, 1, 6, 2, 5, 1, 3, 4, 6};
        // Brute Force - O(n^2)
        System.out.println(Arrays.toString(findCountOfContiguousSubArray(arr))); // Output: [1, 3, 1, 5, 1]
        // Optimized solution O(n)
        System.out.println(Arrays.toString(findCountOfContiguousSubArrayOptimized(arr))); // Output: [1, 3, 1, 5, 1]
    }

    // O(n^2) solution
    static int[] findCountOfContiguousSubArray(int[] arr) {
        int length = arr.length;
        int[] result = new int[length];
        Arrays.fill(result, 1);
        for (int idx = 0; idx < length; idx++) {
            // Check for left values
            int ptr = idx - 1;
            while (ptr >= 0 && ptr < length && arr[idx] > arr[ptr]) {
                result[idx]++;
                ptr--;
            }
            // Check for right values
            ptr = idx + 1;
            while (ptr >= 0 && ptr < length && arr[idx] > arr[ptr]) {
                result[idx]++;
                ptr++;
            }
        }
        return result;
    }

    /**
     * Maintain a stack such that it always contains the index for the last maximum encountered. If the next element is
     * greater than the arr[stack.peek()] then pop the top of the stack till we find a equal or greater element.
     * If stack is empty then it means that the current element is the maximum of all and hence there are
     *  (current index + 1) possible arrays meeting the criteria.
     * If stack is not empty, then (current index - stack top) will be possible arrays for the index position
     *
     * O(n) solution
     * @param arr
     * @return
     */
    static int[] findCountOfContiguousSubArrayOptimized(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[arr.length];
        result[0] = 1;
        // For every index, check from the left for possible contiguous arrays
        stack.push(0);
        for(int i = 1; i < arr.length; i++) {
            while(!stack.isEmpty() && arr[stack.peek()] < arr[i]) stack.pop(); // pop the stack elements till arr[stack top] is greater than or equal to the current element
            // after the above step either stack is empty or stack top is the max element and hence contiguous sub-arrays having max element at i are only possible till the stack top
            if(stack.isEmpty()) result[i] = i + 1;
            else result[i] = i - stack.peek();
            stack.push(i);
        }
        stack.clear();
        // For every index, check from the right for possible contiguous arrays
        stack.push(arr.length - 1);
        for(int i = arr.length - 2; i >= 0; i--) {
            while(!stack.isEmpty() && arr[stack.peek()] < arr[i]) stack.pop();
            // after the above step either stack is empty or stack top is the max element and hence contiguous sub-arrays having max element at i are only possible till the stack top
            if(stack.isEmpty()) result[i] += (arr.length - i - 1);
            else result[i] += (stack.peek() - i - 1);
            stack.push(i);
        }
        return result;
    }
}
