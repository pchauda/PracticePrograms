package com.p.pc.programs;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *  Find path having max sum from start (0,0) to end (m,n) where you can either go right or down.
 *     -5   9   -2    6
 *     -2   10   4    9
 *     -9  -5   -4    5
 *    -10  16   10   -6
 *
 *  In the above scenario the path will be: -5 -> 9 -> 10 -> -5 -> 16 -> 10 -> -6 (total 29)
 */
public class MaxPathIn2DArray {
    public static void main(String[] args) {
        int[][] arr = new int[][] {
                {-5,   9, -2,  6},
                {-2,  10,  4,  9},
                {-9,  -5, -4,  5},
                {-10, 16, 10, -6}
        };
        System.out.println("From End: " + findMaxPath(arr, arr.length -1, arr[0].length - 1));

        System.out.println("Optimized: " + findMaxPathOptimized(arr));
    }

    static int findMaxPathOptimized(int[][] arr) {
        // Kadane's algo
        int[][] maxSumAtEachPosition = new int[arr.length][arr[0].length]; //assuming balanced matrix

        for(int i=0; i < arr.length; i++) {
            for(int j=0; j < arr[i].length; j++) {
                if(i == 0 && j == 0)
                    maxSumAtEachPosition[i][j] = arr[i][j];
                else {
                    int left = (j - 1) < 0 ? Integer.MIN_VALUE : maxSumAtEachPosition[i][j-1];
                    int up = (i - 1) < 0 ? Integer.MIN_VALUE : maxSumAtEachPosition[i-1][j];
                    maxSumAtEachPosition[i][j] = arr[i][j] + Math.max(left, up);
                }
               // System.out.print(maxSumAtEachPosition[i][j] + " ");
            }
            // System.out.println();
        }
        System.out.println("Max sum within optimized: " + maxSumAtEachPosition[arr.length - 1][arr[0].length - 1]);

        LinkedList<Integer> path = new LinkedList<>();
        int row = maxSumAtEachPosition.length - 1;
        int column = maxSumAtEachPosition[0].length - 1;

        while(row > 0 || column > 0) {
            path.addFirst(arr[row][column]);
            int left = (column - 1) < 0 ? Integer.MIN_VALUE : maxSumAtEachPosition[row][column - 1];
            int up = (row - 1) < 0 ? Integer.MIN_VALUE : maxSumAtEachPosition[row - 1][column];

            if ((left > up)) {
                column = (column - 1) < 0 ? 0 : column - 1;
            } else {
                row = (row - 1) < 0 ? 0 : row - 1;
            }
        }
        path.addFirst(arr[0][0]);
        System.out.println("Path:" + path);
        return maxSumAtEachPosition[arr.length - 1][arr[0].length - 1];
    }

    static int findMaxPath(int[][] arr, int x, int y) {
        if(x == 0 && y == 0)
            return arr[0][0];
        else if(x == 0)
            return arr[x][y] + findMaxPath(arr, x, y -1);
        else if(y == 0)
            return arr[x][y] + findMaxPath(arr, x-1, y);
        return arr[x][y] + Math.max(findMaxPath(arr, x-1, y), findMaxPath(arr, x, y-1));
    }
}
