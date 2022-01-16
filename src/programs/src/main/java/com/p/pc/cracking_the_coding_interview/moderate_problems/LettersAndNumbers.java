package com.p.pc.cracking_the_coding_interview.moderate_problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Given an array filled with letters and numbers, find the longest sub-array with
 * an equal number of letters and numbers.
 * Example:
 * char[] arr = new char[] {'1', 'A', 'b', '1', '2', 'c', 'D', '4'};
 *
 * Output:
 *  Sub-array length having equal number of letters and numbers: 8 - {'1', 'A', 'b', '1', '2', 'c', 'D', '4'}
 *</p>
 * Approach: <br/>
 * Calculate the delta between letter and number count for each element in the array. As shown below wherever the delta
 * matches then there is equal number of letters and numbers (i.e. same number of letters and numbers appeared in between)
 * starting from match index + 1 to last matching index.
 * Now to find the longest sub-array, we just need to find the largest range of matching delta, which can be done in O(n) using HashMap.
 *
 *                  1   A   b   1   2   c   D   4
 *                  -----------------------------
 *  LetterCount:    0   1   2   2   2   3   4   4
 *  NumCount:       1   1   1   2   3   3   3   4
 *  Delta:         -1   0   1   0  -1   0   1   0
 *
 *  DeltaMap starts with: (0, -1) : For delta of zero mark the initial index as -1 so that if first character is number then it can be counted.
 */
public class LettersAndNumbers {
    private static final LettersAndNumbers obj = new LettersAndNumbers();

    public static void main(String[] args) {
        System.out.println(Arrays.toString(obj.longestSubArrayHavingEqualLetterAndNumbers(new char[] {'A', 'b', '1', '2', 'c', 'D', '4'})));
        System.out.println(Arrays.toString(obj.longestSubArrayHavingEqualLetterAndNumbers(new char[] {'6','A', 'b', '1', '2', 'c', 'D', '4'})));
        System.out.println(Arrays.toString(obj.longestSubArrayHavingEqualLetterAndNumbers(new char[] {'1', 'A', '2'})));
    }

    private char[] longestSubArrayHavingEqualLetterAndNumbers(char[] arr) {
        int[] deltaArr = new int[arr.length];
        int delta = 0;
        // calculate delta array
        for(int i=0; i<arr.length; i++) {
            if(Character.isAlphabetic(arr[i])) {
                delta++;
            } else if(Character.isDigit(arr[i])) {
                delta--;
            }
            deltaArr[i] = delta;
        }

        // find indexes for largest sub-array
        int startIdx = -1, endIdx = -1;
        Map<Integer, Integer> deltaIndexMap = new HashMap<>(); // Map<DeltaCount, Index>
        deltaIndexMap.put(0, -1); // Put deltaCount of 0 at -1 position as initial conditon

        for(int i=0; i<deltaArr.length; i++) {
            int curr = deltaArr[i];
            if(!deltaIndexMap.containsKey(curr)) {
                deltaIndexMap.put(curr, i);
            } else {
                Integer match = deltaIndexMap.get(curr);
                int prevMax = endIdx - startIdx;
                int currMax = i - match;
                if(currMax > prevMax) {
                    startIdx = match;
                    endIdx = i;
                }
            }
        }

        if(startIdx == endIdx) throw new IllegalArgumentException("No sub-array found");

        // find the longest sub-array
        char[] retVal = new char[endIdx - startIdx];
        for(int i= startIdx + 1; i <= endIdx; i++) {
            retVal[i - (startIdx + 1)] = arr[i];
        }
        return retVal;
    }
}
