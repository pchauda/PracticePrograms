package com.p.pc.facebook;

/**
 * Find length of the longest common subsequence of two given strings
 */
public class LongestCommonSubSequence {
    public static void main(String[] args) {
        String s1 = "PrinceChauda";
        String s2 = "RiyaMalik";
        System.out.println("Length of LCS: " + lcs(s1.toCharArray(), s2.toCharArray(), s1.length() -1 , s2.length() - 1));
        System.out.println("Length of LCS (DP): " + lcsWithMemoization(s1.toCharArray(), s2.toCharArray()));
    }

    /**
     * This method uses recursion to compute the lcs using two cases:
     * 1. If the last character matches then lcs = 1 + lcs of previous chars
     * 2. If last characters don't match then lcs = max (lcs (x-1, y), lcs(x, y-1) )
     *
     * Using this approach we can clearly see that we are computing the same results again and again and hence it can be
     * optimized with memoization and DP.
     *
     * Complexity: O(2^n) worst case scenario when both strings are completely different and lcs = 0
     *
     * @param X first char array
     * @param Y second char array
     * @param xIdx index of first char array
     * @param yIdx index of first char array
     * @return lcs length
     */
    private static int lcs(char[] X, char[] Y, int xIdx, int yIdx) {
        // base case, if either the array's length becomes less than zero then no match hence return zero as length
        if(xIdx < 0 || yIdx < 0) return 0;
        // equal char scenario
        if(X[xIdx] == Y[yIdx])
            return 1 + lcs(X, Y, xIdx - 1, yIdx - 1);
        // un-equal char scenario
        else
            return Math.max(lcs(X, Y, xIdx -1, yIdx), lcs(X, Y, xIdx, yIdx - 1));

    }

    /**
     * This method uses the DP approach with memoization to reduce the computation time from exponential to m * n
     *
     * Complexity: O(m * n) where m = length of first string and n = length of second string
     *
     * @param X first array
     * @param Y second array
     * @return lcs length
     */
    private static int lcsWithMemoization(char[] X, char[] Y) {
        int m = X.length; int n=Y.length;
        int[][] lcs = new int[m+1][n+1];
        // populate the lcs table
        for(int i=0; i<=m; i++) {
            for(int j=0; j<=n; j++) {
                // fill 0th index with 0
                if(i == 0 || j == 0)
                    lcs[i][j] = 0;
                // if chars match then lcs = 1 + prev lcs
                else if(X[i-1] == Y[j-1])
                    lcs[i][j] = 1 + lcs[i-1][j-1];
                // else max of either left or up
                else
                    lcs[i][j] = Math.max(lcs[i-1][j], lcs[i][j-1]);
            }
        }

        // Below step is just to print the common sub-sequence
        int index = lcs[m][n];
        char[] lcsArr = new char[index];

        int i=m; int j=n;
        while(i > 0 && j > 0) {
            if(X[i-1] == Y[j-1]) {
                lcsArr[--index] = X[--i];
                j--;
            } else if(lcs[i-1][j] > lcs[i][j-1]) {
                i--;
            } else j--;
        }
        System.out.println("Common Sub sequence: " + new String(lcsArr));
        // return the result
        return lcs[m][n];
    }

}
