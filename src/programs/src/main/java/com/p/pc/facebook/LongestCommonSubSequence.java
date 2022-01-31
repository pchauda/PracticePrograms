package com.p.pc.facebook;

/**
 * Find length of the longest common subsequence of two given strings. Subsequence may not be strictly consecutive.
 */
public class LongestCommonSubSequence {
    public static void main(String[] args) {
        String s1 = "FindLongestCommonSubSequence";
        String s2 = "DifficultProblem";
        System.out.println("Length of LCS (memo): " + lcsWithMemo(s1.toCharArray(), s2.toCharArray())); // Output: itobe
        System.out.println("Length of LCS (DP): " + lcsWithDP(s1.toCharArray(), s2.toCharArray())); // Output: itobe
    }

    /**
     * This method uses recursion to compute the lcs using two cases:
     * 1. If the last character matches then lcs = 1 + lcs of previous chars
     * 2. If last characters don't match then lcs = max (lcs (x-1, y), lcs(x, y-1) )
     * <p>
     * Using this approach we can clearly see that we are computing the same results again and again and hence it can be
     * optimized with memoization and DP.
     * <p>
     * Complexity: O(2^n) worst case scenario without memo when both strings are completely different and lcs = 0
     * With memo, complexity = O (m * n)
     *
     * @param X first char array
     * @param Y second char array
     * @return lcs length
     */
    private static int lcsWithMemo(char[] X, char[] Y) {
        int[][] memo = new int[X.length + 1][Y.length + 1];
        return lcsWithMemoRecursive(X, Y, X.length - 1, Y.length - 1, memo);
    }

    private static int lcsWithMemoRecursive(char[] X, char[] Y, int xIdx, int yIdx, int[][] memo) {
        // base case, if either the array's length becomes less than zero then no match hence return zero as length
        if (xIdx < 0 || yIdx < 0) return 0;
        if (memo[xIdx][yIdx] != 0) return memo[xIdx][yIdx];
        // equal char scenario
        if (X[xIdx] == Y[yIdx]) {
            memo[xIdx][yIdx] = 1 + lcsWithMemoRecursive(X, Y, xIdx - 1, yIdx - 1, memo);
            return memo[xIdx][yIdx];
        } // un-equal char scenario
        else {
            memo[xIdx][yIdx] = Math.max(lcsWithMemoRecursive(X, Y, xIdx - 1, yIdx, memo),
                    lcsWithMemoRecursive(X, Y, xIdx, yIdx - 1, memo));
            return memo[xIdx][yIdx];
        }
    }

    /**
     * This method uses the DP approach with memoization to reduce the computation time from exponential to m * n
     * <p>
     * Complexity: O(m * n) where m = length of first string and n = length of second string
     *
     * @param X first array
     * @param Y second array
     * @return lcs length
     */
    private static int lcsWithDP(char[] X, char[] Y) {
        int m = X.length;
        int n = Y.length;
        int[][] lcs = new int[m + 1][n + 1];
        // populate the lcs table
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                // fill 0th index with 0
                if (i == 0 || j == 0)
                    lcs[i][j] = 0;
                    // if chars match then lcs = 1 + prev lcs
                else if (X[i - 1] == Y[j - 1])
                    lcs[i][j] = 1 + lcs[i - 1][j - 1];
                    // else max of either left or up
                else
                    lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
            }
        }

        // Below step is just to print the common sub-sequence
        int lcsVal = lcs[m][n];
        char[] lcsArr = new char[lcsVal];

        int i = m;
        int j = n;
        while (i > 0 && j > 0) {
            if (X[i - 1] == Y[j - 1]) {
                lcsArr[--lcsVal] = X[--i];
                j--;
            } else if (lcs[i - 1][j] > lcs[i][j - 1]) {
                i--;
            } else j--;
        }
        System.out.println("Common Sub sequence: " + new String(lcsArr));
        // return the result
        return lcs[m][n];
    }

}
