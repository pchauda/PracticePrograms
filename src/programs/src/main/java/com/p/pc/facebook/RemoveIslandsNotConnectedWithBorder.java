package com.p.pc.facebook;

import java.util.Arrays;
import java.util.StringJoiner;

/**
 * Given a 2D matrix where 1 represents land and 0 represents water. Remove all the islands that are not connected
 * to the border. Do not consider diagonals for connections.
 * Example:
 * Input:
 *     0   0   0   0   1   0
 *     0   1   0   1   0   1
 *     1   1   0   0   0   1
 *     0   1   0   1   1   1
 *     0   0   1   0   0   0
 * Output:
 *     0   0   0   0   1   0
 *     0   1   0   0   0   1
 *     1   1   0   0   0   1
 *     0   1   0   1   1   1
 *     0   0   1   0   0   0
 */
public class RemoveIslandsNotConnectedWithBorder {
    private static RemoveIslandsNotConnectedWithBorder obj = new RemoveIslandsNotConnectedWithBorder();
    public static void main(String[] args) {
        int[][] matrix = new int[][] {
                {0, 0, 0, 0, 1, 0},
                {0, 1, 0, 1, 0, 1},
                {1, 0, 0, 1, 0, 1},
                {0, 1, 0, 1, 1, 1},
                {0, 0, 1, 0, 0, 0}
        };
        System.out.println("Input matrix");
        obj.printMatrix(matrix);
        obj.removeIslands(matrix);
        System.out.println("Output matrix");
        obj.printMatrix(matrix);
    }

    private void removeIslands(int[][] matrix) {
        if(matrix == null) return;
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];
        for(int i=0; i < rows; i++) {
            for(int j=0; j<cols; j++) {
                if(!visited[i][j] && matrix[i][j] == 1) {
                    performDFS(i, j, matrix, visited, false);
                }
            }
        }
    }

    private boolean performDFS(int i, int j, int[][] matrix, boolean[][] visited, boolean isConnectedWithBorder) {
        if(!isConnectedWithBorder && checkBorder(i, j, matrix))
            isConnectedWithBorder = true;
        visited[i][j] = true;
        int[] rowDelta = new int[] {-1, 0, 0, 1};
        int[] colDelta = new int[] {0, -1, 1, 0};

        for(int k = 0; k < rowDelta.length; k++) {
            int r1 = i + rowDelta[k];
            int c1 = j + colDelta[k];
            if(isValid(r1, c1, matrix) && !visited[r1][c1] && matrix[r1][c1] == 1) {
                isConnectedWithBorder = performDFS(r1, c1, matrix, visited, isConnectedWithBorder);
            }
        }
        if(!isConnectedWithBorder)
            matrix[i][j] = 0;
        return isConnectedWithBorder;
    }

    private boolean isValid(int r1, int c1, int[][] matrix) {
        int rows = matrix.length; int cols = matrix[0].length;
        if(r1 >= 0 && r1 < rows && c1 >= 0 && c1 < cols) return true; else return false;
    }

    private boolean checkBorder(int i, int j, int[][] matrix) {
        int rows = matrix.length; int cols = matrix[0].length;
        if(i == 0 || i == rows - 1 || j == 0 || j == cols - 1) return true;
        else return false;
    }

    private void printMatrix(int[][] matrix) {
        for(int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}
