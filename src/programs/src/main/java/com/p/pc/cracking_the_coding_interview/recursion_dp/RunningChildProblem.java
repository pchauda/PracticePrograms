package com.p.pc.cracking_the_coding_interview.recursion_dp;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * <p>Problem Statement:
 * A child is running up a staircase with n steps and can hop either 1 step, 2 steps, or 3
 * steps at a time. Implement a method to count how many possible ways the child can run up the
 * stairs.</p>
 * <p>
 * Approach: <br/>
 * Use fibonacci approach to solve this problem using recursion with memoization.
 * nth step can be reach by = Using 1 step from (n - 1) step OR Using 2 steps from (n - 2) step OR Using 3 steps from (n - 3) step
 * i.e.
 * waysToReach(n) = waysToReach(n-1) + waysToReach(n-2) + waysToReach(n-3)
 * <p>
 * Base case, if n = 0 (no stairs) then 1 way to reach n. if n < 0 then 0 ways to reach n.
 */
public class RunningChildProblem {
    public static void main(String[] args) {
        int stairs = 20;
        BigInteger waysToClimb = waysToClimbOptimized(stairs);
        System.out.println(waysToClimb); // Output: 121415
    }

    private static BigInteger waysToClimbOptimized(int n) {
        BigInteger[] memo = new BigInteger[n + 1];
        Arrays.fill(memo, BigInteger.valueOf(-1));
        return waysToClimbOptimizedWithMemo(n, memo);
    }

    // Solving the problem using Dynamic Programming. Very optimized solution, time complexity = O(n)
    private static BigInteger waysToClimbOptimizedWithMemo(int n, BigInteger[] memo) {
        if (n < 0)
            return BigInteger.valueOf(0); // Return 0
        if (n == 0)
            return BigInteger.valueOf(1); // Return 1, if there are no steps then also 1 step is required
        if (memo[n] != BigInteger.valueOf(-1))
            return memo[n]; // Return previously computed value if available
        // Compute the value and store it in the array before returning it
        memo[n] = waysToClimbOptimizedWithMemo(n - 1, memo).add(waysToClimbOptimizedWithMemo(n - 2, memo)).add(waysToClimbOptimizedWithMemo(n - 3, memo));
        return memo[n];
    }

    // Un-optimized recursive algorithm, time complexity = O(3^n)
    private static BigInteger waysToClimb(int n) {
        if (n < 0) return BigInteger.valueOf(0);
        if (n == 0) return BigInteger.valueOf(1);
        return waysToClimb(n - 1).add(waysToClimb(n - 2)).add(waysToClimb(n - 3));
    }
}
