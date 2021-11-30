package com.p.pc.cracking_the_coding_interview.moderate_problems;

import java.util.HashSet;
import java.util.Set;

/**
 * You are building a diving board by placing a bunch of planks of wood end-to-end.
 * There are two types of planks, one of length shorter and one of length longer. You must use
 * exactly K planks of wood. Write a method to generate all possible lengths for the diving board.
 *
 * Approach:
 *  1. Recursive approach: For each plank there are two options, either pick shorter or longer and so on. Pick any plank
 *  and recursively repeat the same step until K becomes zero and then add the total length obtained so far in a Set
 *  to avoid duplicates. Solution can be improved by memoization.
 *  Time complexity of simple recursive solution will exponential - O (2^K). With memoization Time complexity will be O(K^2) only.
 *
 *  2. Iterative approach: If you take a close look to the problem, it is evident that there are only K + 1 distinct sums
 *  to picking K planks of wood. I.e. (0 smaller, K larger), (1 smaller, K-1 larger) ...... ( K smaller, 0 larger)
 *  Using this approach, we can just pick up planks following the above order and find all possible lengths.
 *  Time complexity is linear here O(K)
 */
public class DivingBoardProblem {
    public static void main(String[] args) {
        int K = 25; // total plans to select
        int s = 3; // length of shorter plank
        int l = 5; // length of longer plank

        Set<Integer> allPossibleLengths = new HashSet<>();
        allPossibleLengthsRecursive(K, 0, s, l, allPossibleLengths);
        System.out.println("All possible lengths (recursive):           " + allPossibleLengths);
        allPossibleLengths = new HashSet<>(); // reset
        allPossibleLengthsRecursiveWithMemo(K, 0, s, l, allPossibleLengths, new HashSet<>());
        System.out.println("All possible lengths (recursive with memo): " + allPossibleLengths);
        System.out.println("All possible lengths (iterative):           " + allPossibleLengthsIterative(K, s, l));
    }
    // Simple recursive approach
    private static void allPossibleLengthsRecursive(int k, int totalLength, int s, int l, Set<Integer> allPossibleLengths) {
        if(k == 0 ) {
            allPossibleLengths.add(totalLength);
            return;
        }
        // select shorter
        allPossibleLengthsRecursive(k -1, totalLength + s, s, l, allPossibleLengths);
        // select longer
        allPossibleLengthsRecursive(k -1, totalLength + l, s, l, allPossibleLengths);
    }
    // Recursive approach with memoization
    private static void allPossibleLengthsRecursiveWithMemo(int k, int totalLength, int s, int l, Set<Integer> allPossibleLengths, Set<String> visited) {
        if(k == 0 ) {
            allPossibleLengths.add(totalLength);
            return;
        }
        String key = k + " " + totalLength; // Key should be using both total plank remaining and length
        if(visited.contains(key)) return; // already visited option
        // select shorter
        allPossibleLengthsRecursiveWithMemo(k -1, totalLength + s, s, l, allPossibleLengths, visited);
        // select longer
        allPossibleLengthsRecursiveWithMemo(k -1, totalLength + l, s, l, allPossibleLengths, visited);
        visited.add(key);
    }

    // Simple iterative approach by picking one type of plank x times and picking another type for K-x times
    private static Set<Integer> allPossibleLengthsIterative(int k, int s, int l) {
        Set<Integer> retVal = new HashSet<>();
        for(int nShort = 0 ; nShort <= k; nShort++) {
            int nLong = k - nShort;
            int totalLength = nShort * s + nLong * l;
            retVal.add(totalLength);
        }
        return retVal;
    }
}
