package com.p.pc.cracking_the_coding_interview.moderate_problems;

/**
 * <p>A popular masseuse receives a sequence of back-to-back appointment requests and is debating which ones to accept.
 * She needs a 15-minute break between appointments, and therefore she cannot accept any adjacent requests.
 * Given a sequence of back-to-back appointment requests (all multiples of 15 minutes, none overlap, and none can be moved),
 * find the optimal (the highest total booked minutes) set the masseuse can honor. Return the number of minutes.
 *
 * Basically, back to back appointments can not be taken as a break is required in between.
 * So if chosen first, then second can't be chosen and so on. <br/>
 *
 * EXAMPLE <br/>
 * Input: {30, 15, 60, 75, 45, 15, 15, 45} <br/>
 * Output: 180 minutes ({30, 60, 45, 45})
 *</p>
 * Approach: <br/>
 * 1. Recursive approach: Select one appointment and recursively select other appointments. Ignore given appointment
 * and select others recursively. Find the best out of the two.
 * Time Complexity: O(2^n) Space Complexity: O(n) due to call stack
 * With memoization the time complexity can be greatly reduced to O(n)
 *
 * 2. Iterative approach: If we notice closely, we only need to memorize the index + 1 and index + 2 for every index.
 * As once, you are past +2 then you don't need the older values again. Now to convert the recursive approach to iterative,
 * we can start from the end of the array and try to compute the best coming back to the beginning of the array. *
 */
public class TheMasseuseProblem {
    private static TheMasseuseProblem obj = new TheMasseuseProblem();

    public static void main(String[] args) {
        int[] appointmentRequests = new int[] {30, 15, 60, 75, 45, 15, 15, 45};
        System.out.println("Max time: " + obj.maxTimeHonoredRecursive(appointmentRequests, 0)); // 180
        System.out.println("Max time (optimal): " + obj.maxTimeHonoredOptimal(appointmentRequests)); // 180
        System.out.println("Max time (iterative): " + obj.maxTimeHonoredIterative(appointmentRequests)); // 180
    }

    // Recursive approach, complexity: O(2 ^ n)
    private int maxTimeHonoredRecursive(int[] requests, int index) {
        // base case
        if(index >= requests.length) return 0;
        // include the given request and check recursively
        int included = requests[index] + maxTimeHonoredRecursive(requests, index + 2);
        // do not include the given request and check recursively
        int notIncluded = maxTimeHonoredRecursive(requests, index + 1);
        return Math.max(included, notIncluded);
    }
    // Recursive approach with memoization, complexity: O(n)
    private int maxTimeHonoredOptimal(int[] requests) {
        int memo[] = new int[requests.length];
        return maxTimeHonoredOptimal(requests, 0, memo);
    }
    // As soon we calculate the best for a given index, store it for re-use purposes.
    private int maxTimeHonoredOptimal(int[] requests, int index, int[] memo) {
        if(index >= requests.length) return 0;
        if(memo[index] == 0) { // If best is not calculated for the given index
            // include the given request and check recursively
            int included = requests[index] + maxTimeHonoredRecursive(requests, index + 2);
            // do not include the given request and check recursively
            int notIncluded = maxTimeHonoredRecursive(requests, index + 1);
            memo[index] = Math.max(included, notIncluded);
        }
        return memo[index];
    }

    /**
     * For any given index (n), either select best(n+1) or select n + best(n+2) to get the best value. Using this approach,
     * we just need two integers to hold the best values at n + 1 and n + 2 index. Starting from the last index and coming back
     * to the first element, this approach can be applied iteratively to get the best solution.
     */
    private int maxTimeHonoredIterative(int[] appointmentRequests) {
        int oneAway = 0; // basically at end + 1 position to start with
        int twoAway = 0; // basically at end + 2 position to start with
        for(int i=appointmentRequests.length -1; i >= 0; i--) {
            int bestWith = appointmentRequests[i] + twoAway;
            int bestWithout = oneAway;

            int currMax = Math.max(bestWith, bestWithout);
            // update twoAway and oneAway
            twoAway = oneAway;
            oneAway = currMax;
        }
        return oneAway;
    }

}
