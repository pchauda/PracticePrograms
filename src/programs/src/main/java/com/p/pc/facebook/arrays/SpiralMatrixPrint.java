package com.p.pc.facebook.arrays;

/**
 * Generate and print spiral matrix (n x n) for a given n.
 *
 * Example:
 * Input: 3
 * Output:
 * 1   2   3
 * 8   9   4
 * 7   6   5
 *
 * Approach:
 *  This problem can be addressed using recursion or iteration. Using recursive approach - walk around the border and fill
 *  out the numbers with each iteration. Then, reduce the problem to a smaller square (reduced n by 2). Continue until you arrive at the base case of 1 or 0.
 *
 *  Another way to solve this problem is to greedily traverse in each direction until you must stop, then turn around
 *  and head in the other direction. The code below follows this approach and is generally the best way to solve this problem.
 */
public class SpiralMatrixPrint {
    public static void main(String[] args) {
        int n = 8;
        int[][] spiralMatrix = generateSpiralMatrix(n);
        printMatrix(spiralMatrix);
    }

    private static void printMatrix(int[][] spiralMatrix) {
        System.out.println("Matrix:");
        for(int i=0; i<spiralMatrix.length; i++) {
            for(int j=0; j<spiralMatrix[0].length; j++) {
                System.out.print(spiralMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int[][] generateSpiralMatrix(int n) {
        int[][] matrix = new int[n][n];
        int val = 0, row = 0, col = 0, total = n * n;
        // Greedily traverse in right direction starting from (0, 0) then turn clockwise whenever limit is reached.
        int[] rowDelta = new int[] {0, 1, 0, -1};
        int[] colDelta = new int[] {1, 0, -1, 0};
        int deltaIndex = 0; // initial direction to traverse in right direction

        while(val++ < total) {
            // assign value
            matrix[row][col] = val;
            // move
            row += rowDelta[deltaIndex];
            col += colDelta[deltaIndex];
            // check for limit (boundary check or matrix already populated), if reached then change direction i.e. increase delta index.
            if(invalid(matrix, row, col)) {
                // reverse the last move
                row -= rowDelta[deltaIndex];
                col -= colDelta[deltaIndex];
                // change direction bounded by the delta range
                deltaIndex = (deltaIndex + 1) % 4;
                // move again
                row += rowDelta[deltaIndex];
                col += colDelta[deltaIndex];
            }
        }
        return matrix;
    }

    private static boolean invalid(int[][] matrix, int row, int col) {
        return row < 0 || col < 0 || row >= matrix.length || col >= matrix[0].length || matrix[row][col] != 0;
    }
}
