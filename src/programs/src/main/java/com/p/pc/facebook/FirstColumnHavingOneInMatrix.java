package com.p.pc.facebook;

/**
 * Given a matrix having only 0's and 1's in all rows, where each row is sorted. Find out the first column having
 * value 1 in it irrespective of the row.
 * <p>
 * Approach:
 * Since each row is sorted, that means if there is 0 at the end then there won't be any 1 before. And if there is a 1
 * at the end then there is a possibility of having 1 before as well.
 * <p>
 * Hence, we can start from top right element and move down the row if zero and move left in the same row for 1. Keep
 * track of the column having last 1 encountered.
 */
public class FirstColumnHavingOneInMatrix {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 0, 0},
                {0, 0, 1, 1, 1},
                {0, 0, 0, 1, 1}
        };
        System.out.println("First column having 1 is : " + findFirstColumnWithOne(matrix)); // Output: 2
    }

    private static int findFirstColumnWithOne(int[][] matrix) {
        int lowestColumn = -1;
        // start from the top right corner
        int i = 0, j = matrix[0].length - 1;
        while (i < matrix.length && j >= 0) {
            if (matrix[i][j] == 0) {
                i++;
            } else {
                lowestColumn = j--;
            }
        }
        return lowestColumn;
    }
}
