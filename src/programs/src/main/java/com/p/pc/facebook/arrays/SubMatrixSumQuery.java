package com.p.pc.facebook.arrays;

/**
 * <b>Problem Statement</b><br/>
 * Given a 2D matrix matrix, handle multiple queries of the following type: <br/>
 * Calculate the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and
 * lower right corner (row2, col2).
 * <p>
 * Implement the NumMatrix class such that:
 * NumMatrix(int[][] matrix) Initializes the object with the integer matrix matrix.
 * int sumRegion(int row1, int col1, int row2, int col2) Returns the sum of the elements of matrix inside the rectangle
 * defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 * <p>
 * <b>Approach</b>
 * Since goal here is to optimize the query performance, data needs to be cached at the time class instantiation. This
 * can be achieved using DP.
 * <p>
 * <b>Companies: Facebook</b>
 */
public class SubMatrixSumQuery {
    final int[][] matrix;
    // cumulative sum from origin
    int[][] dp;

    public SubMatrixSumQuery(int[][] matrix) {
        this.matrix = matrix;
        this.dp = new int[matrix.length + 1][matrix[0].length + 1];
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                dp[r + 1][c + 1] = dp[r][c + 1] + dp[r + 1][c] + matrix[r][c] - dp[r][c]; // subtract dp[r][c] as it was counted twice
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 2, 4, 3},
                {3, 1, 2, 3},
                {0, 1, -1, 1},
                {0, 1, 3, 2}
        };

        SubMatrixSumQuery obj = new SubMatrixSumQuery(matrix);
        System.out.println(obj.sumRegion(0, 0, 1, 1)); // Output: 7
        System.out.println(obj.sumRegion(2, 1, 3, 2)); // Output: 4
        System.out.println(obj.sumRegionOptimized(0, 0, 1, 1)); // Output: 7
        System.out.println(obj.sumRegionOptimized(2, 1, 3, 2)); // Output: 4
    }

    int sumRegion(int row1, int col1, int row2, int col2) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int sum = 0;
        for (int r = row1; r <= row2 && r < rows; r++) {
            for (int c = col1; c <= col2 && c < cols; c++) {
                sum += matrix[r][c];
            }
        }
        return sum;
    }

    // Sum(ABCD)= Sum(OD) − Sum(OB) − Sum(OC) + Sum(OA)
    // Time Complexity = For Query: O(1), DP construction time = O(mn), Space: O(mn)
    int sumRegionOptimized(int row1, int col1, int row2, int col2) {
        return dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - dp[row2 + 1][col1] + dp[row1][col1];
    }
}

