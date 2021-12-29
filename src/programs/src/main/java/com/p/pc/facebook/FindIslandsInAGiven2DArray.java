package com.p.pc.facebook;

/**
 * Find total numbers of islands in a given matrix of 0 and 1. Where 0 represents water and 1 represents land.
 * All connected 1's will represent one island. Connections can be considered diagonal as well.
 *
 * Approach:
 *  Idea here to follow the algorithm of connected components search in a graph. The only variation is to find out the
 *  adjacent nodes and then perform DFS recursively. Every node will have max of 8 adjacent nodes that can we reached
 *  out by -1, 0, 1 for rows and -1, 0, 1 for columns.
 */
public class FindIslandsInAGiven2DArray {
    public static void main(String[] args) {
        int[][] arr = new int[][] {
                {1, 0, 1, 0, 0},
                {1, 0, 0, 1, 0},
                {0, 0, 1, 0, 0},
                {1, 0, 0, 0, 1}
        };
        System.out.println("Total islands: " + findIslands(arr)); // Output: 4
    }

    private static int findIslands(int[][] arr) {
        boolean[][] visited = new boolean[arr.length][arr[0].length];
        int count = 0;
        for(int r = 0; r < arr.length; r++) {
            for(int c=0; c < arr[0].length; c++) {
                if(!visited[r][c] && arr[r][c] == 1) {
                    performDFS(arr, r, c, visited);
                    count++;
                }
            }
        }
        return count;
    }

    private static void performDFS(int[][] arr, int r, int c, boolean[][] visited) {
        visited[r][c] = true;
        // neighbours represented by row and column
        int[] adjRows = new int[] {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] adjCols = new int[] {-1, 0, 1, -1, 1, -1, 0 ,1};

        for(int k=0; k<8; k++) {
            int r1 = r + adjRows[k];
            int c1 = c + adjCols[k];
            if(ifValid(r1, c1, arr.length, arr[0].length) && !visited[r1][c1] && arr[r1][c1] == 1) {
                performDFS(arr, r1, c1, visited);
            }
        }
    }

    private static boolean ifValid(int r1, int c1, int rows, int cols) {
        if(r1 < 0 || c1 < 0 || r1 >= rows || c1 >= cols) return false;
        return true;
    }
}
