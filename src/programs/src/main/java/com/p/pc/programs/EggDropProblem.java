package com.p.pc.programs;

import static java.lang.Math.max;

/**
 * You are given k identical eggs, and you have access to a building with n floors labeled from 1 to n.
 * You know that there exists a floor f where 0 <= f <= n such that any egg dropped at a floor higher than f will break,
 * and any egg dropped at or below floor f will not break.
 * Each move, you may take an unbroken egg and drop it from any floor x (where 1 <= x <= n). If the egg breaks, you can
 * no longer use it. However, if the egg does not break, you may reuse it in future moves.
 * Return the minimum number of moves that you need to determine with certainty what the value of f is.
 *
 * For every floor x, and e given eggs there are two options 1. Egg will break, 2. Egg will not break
 * For scenario 1: Try for (x-1) floor and (e-1) eggs
 * For scenario 2: Try for (n-x) floor and (e) eggs
 *
 * Time complexity = O(e * f^2) where e = number of eggs, f = number of floors
 * Space complexity = O(e * f) due to two-dimensional array
 */
public class EggDropProblem {
    private static EggDropProblem obj = new EggDropProblem();

    public static void main(String[] args) {
        System.out.println("Minimum attempts: " + obj.eggDrop(2, 10)); // Output: 4
        System.out.println("Minimum attempts (DP): " + obj.eggDropUsingDp(2, 10)); // Output: 4
    }

    // Recursive solution, Time complexity = Exponential = O(2^(e * f)), needs improvement for higher number of floors
    public int eggDrop(int eggs, int floors) {
        // base cases for eggs and floors
        if(floors == 0 || floors == 1) return floors;
        if(eggs == 1) return floors;
        // Try from each floor and calculate the worst case for each floor and then pick the minimum value across all floors
        int result = Integer.MAX_VALUE;
        for(int i = 1; i <= floors; i++) {
            int eggBrokenScenario = eggDrop(eggs - 1, i - 1);
            int eggNotBrokenScenario = eggDrop(eggs, floors - i);
            int temp = 1 + Math.max(eggBrokenScenario, eggNotBrokenScenario); // 1 is added for current attempt
            if(temp < result) {
                result = temp;
            }
        }
        return result;
    }

    public int eggDropUsingDp(int eggs, int floors) {
        // 2D table where entry eggFloor[i][j] will represent minimum number of trials needed for i eggs and j floors.
        int eggFloor[][] = new int[eggs + 1][floors + 1];
        // base cases for eggs
        for (int i = 1; i <= eggs; i++) {
            eggFloor[i][0] = 0; // 0 attempts required for 0 floor
            eggFloor[i][1] = 1; // 1 attempt required for 1 floor
        }
        // base cases for floors
        for (int j = 1; j <= floors; j++)
            eggFloor[1][j] = j; // always need j attempts for 1 egg
        // given total eggs and floors, calculate minimum attempts required using optimal substructure property
        for (int i = 2; i <= eggs; i++) {
            for (int j = 2; j <= floors; j++) {
                eggFloor[i][j] = Integer.MAX_VALUE;
                // for each floor from 1 to j, calculate the minimum attempts required
                for (int x = 1; x <= j; x++) {
                    int brokenEggResult = eggFloor[i - 1][x - 1];
                    int eggSurvivalResult = eggFloor[i][j - x];
                    int tmp = 1 + max(brokenEggResult, eggSurvivalResult); // 1 is added for current attempt
                    if (tmp < eggFloor[i][j])
                        eggFloor[i][j] = tmp;
                }
            }
        }
        // eggFloor[eggs][floors] holds the result
        return eggFloor[eggs][floors];
    }
}
