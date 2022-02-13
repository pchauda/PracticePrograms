package com.p.pc.cracking_the_coding_interview.recursion_dp;

/**
 * <p>Given an infinite number of quarters (25 cents), dimes (1 O cents), nickels (5 cents), and
 * pennies (1 cent), write code to calculate the number of ways of representing n cents.
 * <p>
 * Approach: <br/>
 * Use dynamic programming approach to solve this problem as this problem can be easily broken to optimal sub problems.
 * Start by calculating all possible way to make 0 USD using all type of cents and store the results. Iteratively, calculate
 * all possible way to compute given amount using previously computed values.
 * </p>
 * Example: <br/>
 *                         Amount
 * ____________________________________________________ <br/>
 * Coins|	0	1	2	3	4	5	6	7	8	9	10  <br/>
 * _____|______________________________________________ <br/>
 * 0	|   1	0	0	0	0	0	0	0	0	0	0   <br/>
 * 1	|   1	1	1	1	1	1	1	1	1	1	1   <br/>
 * 5	|   1	1	1	1	1	2	2	2	2	2	3   <br/>
 * 10	|   1	1	1	1	1	2	2	2	2	2	4   <br/>
 * 25	|   1	1	1	1	1	2	2	2	2	2	4   <br/>
 */
public class CoinChangeProblem {

    public static void main(String[] args) {
        int[] coinTypes = new int[]{1, 5, 10, 25};
        int amount = 100;

        int ways = waysToMakeChange(coinTypes, amount);
        System.out.format("Total possible ways to make the change of %d are: %d", amount, ways); // Output: 242
    }

    private static int waysToMakeChange(int[] coinTypes, int amount) {
        int[][] dp = new int[coinTypes.length + 1][amount + 1]; // DP table to store previously computed result
        // Iterate through each coin, order of the coins does not matter
        for (int i = 1; i <= coinTypes.length; i++) {
            int coinValue = coinTypes[i - 1];
            dp[i][0] = 1; // There is always 1 way to make 0 sum for every coin by not including that coin
            // Compute all possible ways to calculate amount of J using the coins selected so far
            for (int j = 1; j <= amount; j++) {
                if (j < coinValue) {
                    // If Amount of J is less than the current coin, then including ith coin, there will be at least
                    // as many ways as at (i-1)the coin to make a sum of J
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // If J is more than or equal to the current coin then also add the number of ways to calculate the
                    // remaining amount = (j - current coin) by picking it up from DP
                    int remainingAmount = j - coinValue;
                    dp[i][j] = dp[i - 1][j] + dp[i][remainingAmount];
                }
            }
        }
        return dp[coinTypes.length][amount];
    }
}
