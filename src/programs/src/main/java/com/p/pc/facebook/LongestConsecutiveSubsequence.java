package com.p.pc.facebook;

import java.util.HashSet;

/**
 * Given an array of integers, find the length of the longest sub-sequence such that elements in the subsequence are
 * consecutive integers, the consecutive numbers can be in any order.
 * <p>
 * Approach:
 * Put all elements of the array in a Set. Then for each element of the array, check if just smaller element exists in the
 * array or not. If no, then current element is the starting element and then check in the set for consecutive elements
 * and increase the count.
 */
public class LongestConsecutiveSubsequence {

    public static void main(String args[]) {
        int arr[] = {1, 9, 3, 10, 4, 20, 2};
        int n = arr.length;
        System.out.println("Length of the Longest consecutive subsequence is " + findLongestConseqSubseq(arr, n)); // Output: 4 => 1,3,4,2
    }

    // Returns length of the longest consecutive subsequence
    static int findLongestConseqSubseq(int arr[], int n) {
        HashSet<Integer> set = new HashSet<>();
        int ans = 0;
        // add elements to a hash
        for(int e : arr) {
            set.add(e);
        }
        // check each possible sequence from the start then update optimal length
        for (int i = 0; i < n; ++i) {
            // if current element is the starting element of a sequence
            if (!set.contains(arr[i] - 1)) {
                // Then check for next elements in the sequence
                int j = arr[i]; int count = 0;
                while (set.contains(j++)) {
                    count++;
                }
                if (ans < count)
                    ans = count;
            }
        }
        return ans;
    }
}
