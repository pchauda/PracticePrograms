package com.p.pc.cracking_the_coding_interview.java;
import java.util.ArrayList;
import java.util.List;

import static com.p.pc.cracking_the_coding_interview.java.TicTacWin.TicTacToe.*;
/**
 * Design an algorithm to figure out if someone has won a game of tic-tac-toe.
 *
 * Approach:
 *  Idea here is to check all rows and columns including both diagonals from the start points to end and if it all matches then return the winner.
 *  To make the code flexible for N x N board, first define conditions with initial positions to check the board and then increment the conditions
 *  to check further positions.
 *
 *  Time complexity = O(n*n) in worst case as all squares need to be checked for the winner.
 */
public class TicTacWin {

    public static void main(String[] args) {
        TicTacToe[][] board = new TicTacToe[][] {
                {Empty, Cross, Cross},
                {Empty, Cross, Circle},
                {Cross, Circle, Circle}
        };
        System.out.println(findWinner(board));
    }

    private static TicTacToe findWinner(TicTacToe[][] board) {
        // check for square board
        if(board.length != board[0].length) throw new IllegalArgumentException("Incorrect board");
        // define initial conditions to check for winner
        List<CheckCondition> conditions = new ArrayList<>();
        for(int i = 0; i < board.length; i++) {
            conditions.add(new CheckCondition(0, i, 1, 0)); // check for columns
            conditions.add(new CheckCondition(i, 0, 0, 1)); // check for rows
        }
        // add conditions for diagonal
        conditions.add(new CheckCondition(0, 0, 1, 1));
        conditions.add(new CheckCondition(0, board.length - 1, 1, -1));

        // For each condition check for the winner
        for(CheckCondition condition : conditions) {
            TicTacToe winner = findWinner(board, condition);
            if(winner != Empty) {
                return winner;
            }
        }
        return Empty;
    }

    private static TicTacToe findWinner(TicTacToe[][] board, CheckCondition condition) {
        TicTacToe initialValue = board[condition.row][condition.col];
        while(condition.inbound(board.length, board[0].length)) {
            if(board[condition.row][condition.col] != initialValue) return Empty;
            condition.increment();
        }
        return initialValue;
    }

    static class CheckCondition {
        int row, col;
        int rowIncrement, colIncrement;

        CheckCondition(int r, int c, int ri, int ci) {
            this.row = r;
            this.col = c;
            this.rowIncrement = ri;
            this.colIncrement = ci;
        }

        void increment() {
            row += rowIncrement;
            col += colIncrement;
        }

        boolean inbound(int rows, int cols) {
            return row >= 0 && row < rows && col >= 0 && col < cols;
        }
    }

    enum TicTacToe {
        Empty, Cross, Circle
    }

}
