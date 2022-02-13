package com.p.pc.facebook;

/**
 * <p>Find all unique paths in a grid (M x N) from start (top left) to end (bottom right). Movement can only happen to
 * right or down from a given block.</p>
 * Example: <br/>
 * For 4 x 4 grid, total paths from top left to bottom right = 20
 *
 * Approach: <br/>
 *  Using DP, the complexity can be reduced to O(m * n). For base cases, if there is only 1 row or 1 column then only
 *  1 path will exist from start to end irrespective to number of columns or rows respectively. And for a given (i, j)
 *  the total paths can be calculated as
 *      Path(i, j) = Path(i-1, j) + Path(i, j-1)
 *  Using above approach the problem can be solved easily.
 *
 *  Recursive approach without memo will have exponential complexity, with memoization the performance will be comparable to DP.
 */
public class UniquePathsInGrid {
    public static void main(String[] args) {
        int gridRows = 20;
        int gridCols = 25;
        long startNano = System.nanoTime();
        int paths = findAllPathsRecursive(gridRows, gridCols, new int[gridRows + 1][gridCols + 1]);
        long endNano = System.nanoTime();
        System.out.println("Total paths from start to end: " + paths + " , took (ms): " + (endNano - startNano) / 1000000.0);
        startNano = System.nanoTime();
        paths = uniquePaths(gridRows, gridCols);
        endNano = System.nanoTime();
        System.out.println("Total paths from start to end (DP): " + paths + " , took (ms): " + (endNano - startNano) / 1000000.0);
    }

    /**
     * Recursive implementation with memoization.
     * Complexity = O(n^2) where n = max(x, y)
     */
    private static int findAllPathsRecursive(int x, int y, int[][] memo) {
        if (x == 1 && y == 1) return 1;
        if(memo[x][y] > 0) return memo[x][y];
        if (x == 1) {
            memo[x][y] = findAllPathsRecursive(1, y - 1, memo);
        } else if (y == 1) {
            memo[x][y] = findAllPathsRecursive(x - 1, 1, memo);
        } else {
            memo[x][y] = findAllPathsRecursive(x - 1, y, memo) + findAllPathsRecursive(x, y - 1, memo);
        }
        return memo[x][y];
    }

    /**
     * Using dynamic programming technique
     * Complexity: O(m * n)
     */
    private static int uniquePaths(int m, int n) {
        int[][] paths = new int[m+1][n+1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1 || j == 1) {
                    paths[i][j] = 1;
                } else
                    paths[i][j] = paths[i - 1][j] + paths[i][j - 1];
            }
        }
        return paths[m][n];
    }
}
