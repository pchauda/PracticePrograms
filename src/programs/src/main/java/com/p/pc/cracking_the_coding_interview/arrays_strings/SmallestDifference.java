package com.p.pc.cracking_the_coding_interview.arrays_strings;

import java.util.Arrays;

/**
 * Given two arrays of integers, compute the pair of values (one value in each
 * array) with the smallest (non-negative) difference. Return the difference.
 * EXAMPLE
 * Input: {1, 3, 15, 11, 2}, {23, 127, 235, 19, 8}
 * Output: 3. That is, the pair (11, 8)
 */
public class SmallestDifference {
    public static void main(String[] args) {
        int[] a1 = new int[]{2, 10, 3, 14, 18, 5};
        int[] a2 = new int[]{7, 8, 17};

        int[] pair = findPairHavingMinDiff(a1, a2);
        System.out.println("Pair: (" + a1[pair[0]] + ", " + a2[pair[1]] + ")");
        System.out.println("Diff: " + Math.abs(a1[pair[0]] - a2[pair[1]]));
    }

    private static int[] findPairHavingMinDiff(int[] a1, int[] a2) {
        Arrays.sort(a1);
        Arrays.sort(a2);
        // Use this if you do not want to sort the original array.
        // IntStream.of(a1).boxed().sorted(Comparator.reverseOrder()).mapToInt(i -> i).toArray()
        int i=0, j=0, diff = Integer.MAX_VALUE;
        int a1Idx = 0, a2Idx = 0;
        while(i < a1.length && j < a2.length) { // since arrays are sorted, loop can be broken as soon as one array ends
            int tmpDiff = Math.abs(a1[i] - a2[j]);
            if(tmpDiff < diff) {
                diff = tmpDiff;
                a1Idx = i; a2Idx = j;
            }
            if(a1[i] < a2[j]) i++;
            else j++;
        }
        return new int[]{a1Idx, a2Idx};
    }
}
