package com.p.pc.facebook;

import java.util.LinkedList;

/**
 * <p>Find the length of longest strictly increasing subsequence in a given array. </p>
 *
 * Approach:
 *  This problem can be solved using dynamic programming as it contains overlapping sub-problems and optimal sub-structure properties.
 *  Idea here is to calculate the max length of strictly increasing sub-seq for each index starting from the first index.
 *
 *  Naive solution here will have O(2^n) complexity which is not great and hence we will use DP to improve the complexity
 *  to O(n^2).
 */
public class LongestIncreasingSubSequence {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, -4, 2, -2, -1, 9, 0, 2, 2, 9, 3, -1, 5};
        System.out.println("Longest increasing subsequence length: " + longestIncreasingSubSeq(arr));
        //Output: 7, [-4, -2, -1, 0, 2, 3, 5]
    }
    // complexity: O(n^2)
    private static int longestIncreasingSubSeq(int[] arr) {
        // array to store the results of sub-problems
        int[] lis = new int[arr.length];
        // initialize the array with 1 as every number will make a sub-sequence
        for(int i=0; i < lis.length; i++) {
            lis[i] = 1;
        }
        int maxLength = 1;
        // for each index calculate the max length from starting index
        for(int j=1; j<arr.length; j++) {
            for(int i=0; i < j; i++) {
                if(arr[j] > arr[i] && lis[i] + 1 > lis[j]) {
                    lis[j] = lis[i] + 1;
                    if(lis[j] > maxLength) maxLength = lis[j];
                }
            }
        }
        printSubSequence(arr, lis, maxLength);
        return maxLength;
    }

    private static void printSubSequence(int[] arr, int[] lis, int maxLength) {
        int length = lis.length;
        LinkedList<Integer> seq = new LinkedList<>();
        for(int j=length-1; j >=0 && maxLength > 0; j--) {
            if(lis[j] == maxLength) {
                seq.addFirst(arr[j]);
                maxLength--;
            }
        }
        System.out.println("Longest increasing sub-seq: " + seq);
    }
}
