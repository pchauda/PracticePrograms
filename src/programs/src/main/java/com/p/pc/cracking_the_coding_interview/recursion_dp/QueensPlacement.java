package com.p.pc.cracking_the_coding_interview.recursion_dp;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * <p>Write an algorithm to print all ways of arranging eight queens on a 8x8 chess board so that none of them share
 * the same row, column, or diagonal. "diagonal" here means all diagonals, not just the two that bisect the board.</p>
 *
 * Approach: <br/>
 *   Use recursion with backtracking to place queens on the board. Start with the first row and first column and try
 *   to place a queen at this position and then move to the second row and try to place to a valid column recursively.
 *   If no solution found then backtrack and try to place in a different column.
 */
public class QueensPlacement {
    static int gridSize = 8; // 8 x 8 matrix
    static int queens = 8; // 8 queens to place

    public static void main(String[] args) {
        ArrayList<Integer[]> results = new ArrayList<>();
        placeQueens(0, new Integer[queens], results);
        System.out.println("Total ways: " + results.size()); // Output: 92
        results.stream().forEach(r -> {
            prettyPrint(r);
        });
    }
    /**
     * Recursively identify and places queen in its respective position using Back tracking algorithm.
     *
     * Time Complexity:
     *  1. for loop runs in O(n) time
     *  2. checkValid method runs in O(n) time
     *  3. checkValid is present in a loop and calls placeQueen recursively
     *  Hence total time complexity: T(n) = (n * n) + n * T(n-1) ~ O(n!) - Factorial
     *
     * @param row current row being processed
     * @param cols array representing column position of a queen in each row (represented by index)
     * @param result all possible combination of queen placements
     */
    private static void placeQueens(int row, Integer[] cols, ArrayList<Integer[]> result) {
        if(row == queens) {
            result.add(cols.clone());
            return;
        } else {
            for(int col = 0; col < gridSize; col++) {
                if(checkValid(cols, row, col)) {
                    cols[row] = col; // Place a queen at (row, col)
                    placeQueens(row + 1, cols, result); // Recursively try to place
                }
            }
        }
    }

    private static boolean checkValid(Integer[] cols, int row, int col) {
        for(int r = 0; r < row; r++) {
            int occupiedCol = cols[r];
            if(col == occupiedCol) return false; // already a queen in the same column
            // Check for diagonal intersection. If distance between row and column is the same then same diagonal
            int colDistance = Math.abs(col - occupiedCol);
            // Row distance and col distance is the same, no need to do absolute for rows as row will always be greater than r
            if(colDistance == (row - r)) return false;
        }
        return true;
    }

    private static void prettyPrint(Integer[] r) {
        System.out.println("Queen placements: " + Arrays.toString(r));
        System.out.println("Board with 8 queens:");
        char[][] board = new char[8][8];

        for(int i =0; i < r.length; i++) {
            Arrays.fill(board[i], '-');
            int col = r[i];
            board[i][col] = 'Q';
            System.out.println(Arrays.toString(board[i]));
        }
    }
}
