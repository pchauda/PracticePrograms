package com.p.pc.cracking_the_coding_interview.moderate_problems;

import java.util.Arrays;

/**
 * Given two strings str1 and str2, the task is to find the length of the shortest string that has both str1 and str2
 * as subsequences.
 *
 * Approach: Follow recursive approach with memoization.
 *  1. If last element matches then length of SCS will be = 1 + SCS (str1 - 1, str2 - 1)
 *  2. If last element does not match then length of SCS will be = 1 + min (SCS (str1 - 1, str2), SCS (str1, str2 - 1))
 */
public class ShortestCommonSuperSequence {
    public static void main(String[] args) {
        String s1 = "ABDD";
        String s2 = "ABCDE";

        System.out.println("SCS: " + findSCS(s1, s2, s1.length(), s2.length()));
        int[][] lookUp = new int[s1.length() + 1][s2.length() + 1];
        System.out.println("SCS optimzied: " + findSCSOptimized(s1, s2, s1.length(), s2.length(), lookUp));
        System.out.println("SCS (DP) : " + findSCSUsingDP(s1, s2));
    }
    // Simple recursive solution. Complexity = O(2 ^ min(m,n))
    private static int findSCS(String s1, String s2, int l1, int l2) {
        // base case, when one string is empty then return the second string
        if(l1 == 0 || l2 == 0) return l1 + l2;

        if(s1.charAt(l1-1) == s2.charAt(l2-1)) {
            return 1 + findSCS(s1, s2, l1 - 1, l2 - 1);
        }
        return 1 + Math.min(findSCS(s1, s2, l1 - 1, l2), findSCS(s1, s2, l1, l2 - 1));
    }
    // Using DP approach as this problem has overlapping sub-problems and optimal sub-problems
    // use additional data structure to hold already computed SCS for a given sub problem
    private static int findSCSOptimized(String s1, String s2, int l1, int l2, int[][] lookUp) {
        if(l1 == 0 || l2 == 0) {
            lookUp[l1][l2] = l1 + l2; // store the result and return
            return lookUp[l1][l2];
        }
        if(lookUp[l1][l2] == 0) {
            if(s1.charAt(l1 - 1) == s2.charAt(l2 - 1)) {
                lookUp[l1][l2] = 1 + findSCSOptimized(s1, s2, l1 - 1, l2 - 1, lookUp);
            } else {
                lookUp[l1][l2] = 1 + Math.min(findSCSOptimized(s1, s2, l1 - 1, l2, lookUp),
                        findSCSOptimized(s1, s2, l1, l2 - 1, lookUp));
            }
        }
        return lookUp[l1][l2];
    }

    private static int findSCSUsingDP(String s1, String s2) {
        int l1 = s1.length();
        int l2 = s2.length();
        // store previously computed results
        int[][] dp = new int[l1+1][l2+1];
        for(int i=0; i<=l1; i++) {
            for(int j=0; j<=l2; j++) {
                if(i == 0) {
                    dp[i][j] = j;
                } else if(j == 0) {
                    dp[i][j] = i;
                } else if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[l1][l2];
    }
}
