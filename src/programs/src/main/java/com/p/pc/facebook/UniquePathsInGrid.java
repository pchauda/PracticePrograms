package com.p.pc.facebook;

public class UniquePathsInGrid {
    public static void main(String[] args) {
        int gridRows = 15;
        int gridCols = 17;
        long startNano = System.nanoTime();
        int paths = findAllPathsRecursive(gridRows, gridCols);
        long endNano = System.nanoTime();
        System.out.println("Total paths from start to end: " + paths + " , took (ms): " + (endNano - startNano) / 1000000.0);
        startNano = System.nanoTime();
        paths = uniquePaths(gridRows, gridCols);
        endNano = System.nanoTime();
        System.out.println("Total paths from start to end (DP): " + paths + " , took (ms): " + (endNano - startNano) / 1000000.0);
    }

    /**
     * Recursive implementation without memoization.
     * Complexity = O(2^n)
     */
    private static int findAllPathsRecursive(int x, int y) {
        if (x == 1 && y == 1) return 1;
        if (x == 1)
            return findAllPathsRecursive(x, y - 1);
        else if (y == 1)
            return findAllPathsRecursive(x - 1, y);
        else
            return findAllPathsRecursive(x - 1, y) + findAllPathsRecursive(x, y - 1);
    }

    /**
     * Using dynamic programming technique
     * Complexity: O(m * n)
     */
    private static int uniquePaths(int m, int n) {
        int[][] paths = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    paths[i][j] = 1;
                } else
                    paths[i][j] = paths[i - 1][j] + paths[i][j - 1];
            }
        }
        return paths[m - 1][n - 1];
    }
}
