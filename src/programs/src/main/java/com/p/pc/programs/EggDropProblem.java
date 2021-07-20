package com.p.pc.programs;

import java.util.Arrays;

/**
 * You are given k identical eggs and you have access to a building with n floors labeled from 1 to n.
 * You know that there exists a floor f where 0 <= f <= n such that any egg dropped at a floor higher than f will break, and any egg dropped at or below floor f will not break.
 * Each move, you may take an unbroken egg and drop it from any floor x (where 1 <= x <= n). If the egg breaks, you can no longer use it. However, if the egg does not break, you may reuse it in future moves.
 * Return the minimum number of moves that you need to determine with certainty what the value of f is.
 *
 * For every floor x and e given eggs there are two options 1. Egg will break 2. Egg will not break
 * For scenario 1: Try for (x-1) floor and (e-1) eggs
 * For scenario 2: Try for (n-x) floor and (e) eggs
 */
public class EggDropProblem {
    public static void main(String[] args) {
        System.out.println("Minimum attempts: " + new EggDropProblem().solveUsingDp(4, 2));
    }

    public int solveUsingDp(int floors, int eggs) {
        final int results[][] = new int[floors + 1][eggs + 1];
        for (int i = 0; i < floors + 1; i++) {
            results[i][1] = i;
        }
        for (int i = 0; i < eggs + 1; i++) {
            results[1][i] = 1;
        }
        for (int i = 2; i < floors + 1; i++) {
            for (int j = 2; j < eggs + 1; j++) {
                results[i][j] = Integer.MAX_VALUE;
                for (int x = 1; x < i; x++) {
                    final int brokenEggResult = results[x - 1][j - 1];
                    final int EggSurvivedResult = results[i - x][j];
                    int temp = Math.max(brokenEggResult, EggSurvivedResult) + 1;
                    // use the minimum of the newly calculated value vs previous value
                    if (temp < results[i][j]) {
                        results[i][j] = temp;
                    }
                }
            }
        }
        for (final int[] a : results) {
            System.out.println(Arrays.toString(a));
        }
        return results[floors][eggs];
    }
}
