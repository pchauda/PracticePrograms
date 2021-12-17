package com.p.pc.cracking_the_coding_interview.moderate_problems;

import java.util.Arrays;

/**
 * You are given two arrays, one shorter (with all distinct elements) and one longer. Find the shortest sub-array in the
 * longer array that contains all the elements in the shorter array. The items can appear in any order.
 *
 * EXAMPLE
 * Input:
 * S = {1, 5, 9}
 * B = {7, 5, 9, 0, 2, 1, 3, 5, 7, 9, 1, 1, 5, 8, 8, 9, 7}
 *                           ----------
 * Output:[7, 10] (the underlined portion above)
 *
 * Approach:
 *  For each index of the bigger array, try to find out closure (i.e. the closest index that contains all elements
 *  from the smaller array starting from the index). Once we have identified all the closures for each index then just
 *  find out the shortest closure compared to its index i.e. shortest (closure - index) for all indexes.
 *
 *  To find closures easily, we can do a backward sweep for each element in shorted and find the next index of that
 *  element in the larger array. Then for each index, by taking the maximum index will give us the closure index.
 *  Calculate the shortest diff between the closure index and corresponding index in the larger array to get the
 *  initial index of the sub array. End index will be = initial index of the shorted diff + shorted diff
 */
public class ShortestSubSequenceProblem {
    private static ShortestSubSequenceProblem obj = new ShortestSubSequenceProblem();

    public static void main(String[] args) {
        int[] s = new int[] {1, 5, 9, 0};
        int[] l = new int[] {7, 5, 9, 0, 2, 1, 3, 5, 7, 9, 1, 1, 5, 8, 8, 9, 7};

        int[] subArray = obj.findShortestSubArray(s, l);
        System.out.println("Shortest subsequence: " + Arrays.toString(subArray));
    }

    private int[] findShortestSubArray(int[] s, int[] l) {
        int[][] nextIndexes = calculateNextIndexUsingBackwardSweep(s, l);
        int[] closures = findClosures(nextIndexes);
        return getShortestClosure(closures, l);
    }

    /**
     * Compute the next indexes for all elements in the shorter array in the larger array.
     *
     * Value	7	5	9	0	2	1	3	5	7	9	1	1	5	8	8	9	7
     * Index	0	1	2	3	4	5	6	7	8	9	10	11	12	13	14	15	16
     * ---------------------------------------------------------------------------
     * next 1	5	5	5	5	5	5	10	10	10	10	10	11	-1	-1	-1	-1	-1
     * next 5	1	1	7	7	7	7	7	7	12	12	12	12	12	-1	-1	-1	-1
     * next 9	2	2	2	9	9	9	9	9	9	9	15	15	15	15	15	15	-1
     * next 1	5	5	5	5	5	5	10	10	10	10	10	11	-1	-1	-1	-1	-1
     */
    private int[][] calculateNextIndexUsingBackwardSweep(int[] s, int[] l) {
        int[][] nextIndexes = new int[s.length][l.length];
        for(int i=0; i<s.length; i++) {
            int next = -1;
            for(int j=l.length-1; j>=0; j--) {
                if(s[i] == l[j]) // if found a match then update the position
                    next = j;
                nextIndexes[i][j] = next;
            }
        }
        return nextIndexes;
    }

    /**
     * Compute closures for each index.
     *
     * Value	7	5	9	0	2	1	3	5	7	9	1	1	5	8	8	9	7
     * Index	0	1	2	3	4	5	6	7	8	9	10	11	12	13	14	15	16
     * ---------------------------------------------------------------------------
     * next 1	5	5	5	5	5	5	10	10	10	10	10	11	-1	-1	-1	-1	-1
     * next 5	1	1	7	7	7	7	7	7	12	12	12	12	12	-1	-1	-1	-1
     * next 9	2	2	2	9	9	9	9	9	9	9	15	15	15	15	15	15	-1
     * ---------------------------------------------------------------------------
     * closure	5	5	7	9	9	9	10	10	12	12	15	15	-1	-1	-1	-1	-1
     * ---------------------------------------------------------------------------
     * Using closures, diff can be calculated
     * diff	    5	4	5	6	5	4	4	3	4	3	5	4	-1	-1	-1	-1	-1
     */
    private int[] findClosures(int[][] nextIndexes) {
        int[] closures = new int[nextIndexes[0].length];
        for(int j=0; j<nextIndexes[0].length; j++) {
            int max = -1;
            for(int i=0; i<nextIndexes.length; i++) {
                int val = nextIndexes[i][j];
                if(val == -1) {
                    max = -1;
                    break;
                }

                if(val > max) {
                    max = val;
                }
            }
            closures[j] = max;
        }
        return closures;
    }

    int[] getShortestClosure(int[] closures, int[] l) {
        // find minimum diff between closure and corresponding larger array index
        int startIdx = -1, endIdx = -1;
        int minDiff = Integer.MAX_VALUE;
        for(int i=0; i<closures.length; i++) {
            int diff = closures[i] - i;
            if(diff > 0 && diff < minDiff) {
                minDiff = diff;
                startIdx = i;
                endIdx = i + minDiff;
            }
        }
        if(startIdx < 0 || endIdx < 0 ) return null;
        // extract the sub array
        int[] subArray = new int[endIdx - startIdx + 1];
        for(int i=startIdx; i<=endIdx; i++) {
            subArray[i - startIdx] = l[i];
        }
        return subArray;
    }
}
