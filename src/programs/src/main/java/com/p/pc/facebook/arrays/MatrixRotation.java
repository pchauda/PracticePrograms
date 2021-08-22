package com.p.pc.facebook.arrays;

import com.sun.security.jgss.GSSUtil;

/**
 * Rotate a matrix clockwise or anti-clockwise by 90 degree.
 * Any N x M matrix can be rotated in either direction very easily using a new array of size M x N as rows will be transformed into columns and vice-versa.
 *
 * In place rotation can only be done for N x N (square matrix).
 *
 * Approach for in-place rotation of N x N matrix:
 *  Any matrix of N cardinality will have floor (N/2) perfect squares to be rotated. For example, 3 x 3 matrix will have 1 square to be rotated
 *  and 4 x 4 matrix will have 2 squares to be rotated. Lets have a counter i = 0 to N/2 -1 for these squares
 *
 *  Within each perfect squares there will be (N - 2 x i) elements to be rotate. Lets have a counter j = i to N - 2i - 1 for these elements.
 *  For clock wise rotation, for every inner loop, a rotation for element (i, j) will look like below:
 *  (i, j) -> (j, N - 1 - i) -> (N - 1 - i, N - 1 - j) -> (N - 1 - j, i) -> (i, j)
 *
 *  For anti clock wise the arrow will be reversed.
 *  Follow above approach to perform in place rotation.
 */
public class MatrixRotation {
    public static void main(String[] args) {
        // 5 x 5 matrix
        int[][] square55 = new int[][] {
                {1, 2, 3, 4, 5},
                {2, 3, 4, 5, 1},
                {3, 4, 5, 1, 2},
                {4, 5, 1, 2, 3},
                {5, 1, 2, 3, 4}
        };
        System.out.println("Original square matrix:");
        String s = "sasasdsad";
        s.contains("as");
        printMatrix(square55);
        // 5 x 7 matrix
        int[][] rect57 = new int[][] {
                {1, 2, 3, 4, 5, 6, 7},
                {2, 3, 4, 5, 6, 7, 1},
                {3, 4, 5, 6, 7, 1, 2},
                {4, 5, 6, 7, 1, 2, 3},
                {5, 6, 7, 1, 2, 3, 4}
        };
        System.out.println("Original rectangle matrix:");
        printMatrix(rect57);

        int[][] rotated1 = rotateMatrixClockWise(square55);
        System.out.println("Clockwise Rotated square matrix:");
        printMatrix(rotated1);

        int[][] rotated2 = rotateMatrixClockWise(rect57);
        System.out.println("Clockwise Rotated rectangle matrix:");
        printMatrix(rotated2);

        int[][] rotated3 = rotateMatrixAntiClockWise(square55);
        System.out.println("Anti-Clockwise Rotated square matrix:");
        printMatrix(rotated3);

        int[][] rotated4 = rotateMatrixAntiClockWise(rect57);
        System.out.println("Anti-Clockwise Rotated rectangle matrix:");
        printMatrix(rotated4);

        System.out.println("Clockwise in-place rotation:");
        rotateSquareMatrixInPlaceClockWise(square55);
        printMatrix(square55);

        System.out.println("Anti-Clockwise in-place rotation (result will be original matrix):");
        rotateSquareMatrixInPlaceAntiClockWise(square55);
        printMatrix(square55);
    }

    private static void printMatrix(int[][] mat) {
        int x = mat.length;
        int y = mat[0].length;
        for(int i=0; i < x; i++) {
            for(int j=0; j < y; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }
    // After rotation row becomes column and column becomes row
    private static int[][] rotateMatrixClockWise(int[][] mat) {
        int x = mat.length;
        int y = mat[0].length;
        int[][] retVal = new int[y][x];
        for(int i=0; i<x; i++) {
            for(int j=0; j<y; j++) {
                retVal[j][x - 1 - i] = mat[i][j];
            }
        }
        return retVal;
    }
    // After rotation row becomes column and column becomes row
    private static int[][] rotateMatrixAntiClockWise(int[][] mat) {
        int x = mat.length;
        int y = mat[0].length;
        int[][] retVal = new int[y][x];
        for(int i=0; i<x; i++) {
            for(int j=0; j<y; j++) {
                retVal[y - 1 - j][i] = mat[i][j];
            }
        }
        return retVal;
    }

    // using the approach mentioned in the class description
    private static void rotateSquareMatrixInPlaceClockWise(int[][] mat) {
        int N = mat.length;
        // For each perfect square
        for(int i=0; i < N/2; i++) {
            // For each element within that perfect square
            for(int j=i; j < N - 1 - 2 * i; j++) {
                // Perform rotation for 4 elements together
                int temp = mat[i][j];
                mat[i][j] = mat[N-1-j][i];
                mat[N-1-j][i] = mat[N-1-i][N-1-j];
                mat[N-1-i][N-1-j] = mat[j][N-1-i];
                mat[j][N-1-i] = temp;
            }
        }
    }
    // using the approach mentioned in the class description
    private static void rotateSquareMatrixInPlaceAntiClockWise(int[][] mat) {
        int N = mat.length;
        // For each perfect square
        for(int i=0; i < N/2; i++) {
            // For each element within that perfect square
            for(int j=i; j < N - 1 - 2 * i; j++) {
                // Perform rotation for 4 elements together
                int temp = mat[i][j];
                mat[i][j] = mat[j][N-1-i];
                mat[j][N-1-i] = mat[N-1-i][N-1-j];
                mat[N-1-i][N-1-j] = mat[N-1-j][i];
                mat[N-1-j][i] = temp;
            }
        }
    }
}
