package com.p.pc.leetcode;

/**
 * Given an Android 3x3 key lock screen and two integers m and n, where 1 ≤ m ≤ n ≤ 9, count the total number of unlock
 * patterns of the Android lock screen, which consist of minimum of m keys and maximum n keys. <br/>
 * <b>Rules for a valid pattern:</b> <br/>
 * 1. Each pattern must connect at least m keys and at most n keys. <br/>
 * 2. All the keys must be distinct. <br/>
 * 3. If the line connecting two consecutive keys in the pattern passes through any other keys, the other keys must have
 * previously selected in the pattern. No jumps through non-selected key is allowed. <br/>
 * 4. The order of keys used matters. <br/>
 *
 * Approach: <br/>
 * Maintain a matrix representing invalid numbers on the path between the two selected keys. Apply DFS to find the next
 * valid key in the range of m and n.
 * In each case, we use DFS to count the number of valid paths from the current number (1–9)to the remaining numbers.
 * To optimize, calling DFS less than 9 times, we can use the symmetry of the 3 by 3 matrix. If we start
 * from 1 or 3 or 7 or 9, the valid paths number should be the same. If we start from 2 or 4 or 6 or 8, the valid paths
 * number are also the same. Start from 5 is the third case. So the total is :
 * return value of DFS (start from 1) * 4 +
 * return value of DFS (start from 2) * 4 +
 * return value of DFS (start from 5)
 */
public class PhoneLockPattern {

    public static void main(String[] args) {
        System.out.println("Total unlock patterns: " + countAllUnlockPatterns(1, 1)); // Output: 9
        System.out.println("Total unlock patterns: " + countAllUnlockPatterns(4, 6)); // Output: 34792
    }

    private static int countAllUnlockPatterns(int m, int n) {
        int res = 0;

        // matrix representing adjacent and distant keys. A value of 0 will represent the adjacent keys, and distance
        // keys will be represented by the key in between
        int[][] matrix = new int[10][10];
        // populate values for distant keys, by default adjacent keys will have 0
        matrix[1][3] = 2; matrix[3][1] = 2;
        matrix[1][7] = 4; matrix[7][1] = 4;
        matrix[3][9] = 6; matrix[9][3] = 6;
        matrix[7][9] = 8; matrix[9][7] = 8;
        matrix[1][9] = matrix[9][1] = matrix[3][7] = matrix[7][3] = matrix[2][8] = matrix[8][2] = matrix[4][6] = matrix[6][4] = 5;

        // perform optimized DFS considering the 3 x 3 symmetry as explained in the approach
        boolean[] visited = new boolean[10];
        for(int i = m; i <= n ; i++) {
            res += performDFS(matrix, visited, 1, i-1) * 4;
            res += performDFS(matrix, visited, 2, i-1) * 4;
            res += performDFS(matrix, visited, 5, i-1);
        }
        return res;
    }

    private static int performDFS(int[][] matrix, boolean[] visited, int curr, int remaining) {
        if(remaining < 0) return 0;
        if(remaining == 0) return 1;
        int res = 0;
        // mark current as visited
        visited[curr] = true;
        // perform DFS considering all available keys as adjacent
        for(int i = 1; i <= 9; i++) {
            // If next key is not visited and (if it is adjacent to the current key or in-between key is already visited)
            if (!visited[i] && (matrix[curr][i] == 0 || visited[matrix[curr][i]])) {
                res += performDFS(matrix, visited, i, remaining - 1);
            }
        }
        // un-visit the current for other DFS calls
        visited[curr] = false;
        return res;
    }
}
