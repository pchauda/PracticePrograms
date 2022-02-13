package com.p.pc.geekforgeeks.graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * <p>Given a snake and ladder board, find the minimum number of dice throws required to reach the destination or last cell
 * from source or 1st cell. The player has total control over outcome of dice throw and wants to find out minimum number
 * of throws required to reach last cell.
 * If the player reaches a cell which is base of a ladder, the player has to climb up that ladder and if reaches a cell
 * that is mouth of the snake, then the player has to go down to the tail of snake without a dice throw.</p>
 * <p>
 * Approach: <br/>
 * Board can be represented as an array where a[i] = -1. Snake and ladder both can be represented in a way such that
 * a[i] will have the final index position.
 * Since a dice throw can result into anything from 1 to 6, upto next 6 cells can be reached from a given cell and all
 * these cells needs to be considered recursively.
 * This problem can be considered as a graph problem, where each vertex has 6 adjacent vertices at 1 distance away (1
 * dice throw away). Now, the problem can be seen as finding the shortest path from start to end using BFS.
 */
public class SnakeAndLadderMinDiceThrowsToReachEnd {
    private static SnakeAndLadderMinDiceThrowsToReachEnd obj = new SnakeAndLadderMinDiceThrowsToReachEnd();

    public static void main(String[] args) {
        int[] board = new int[100]; // 100 cells in the board, board[0] is first cell and board[99] is the last cell.
        Arrays.fill(board, -1);
        // ladders
        board[2] = 51; // cell 3 has a ladder that will move the player up to cell 21 and so on
        board[10] = 41;
        board[23] = 76;
        board[39] = 64;
        board[56] = 82;
        board[64] = 78;

        // snakes
        board[98] = 75; // cell 98 has a snake that will move the player down to cell 75 and so on
        board[91] = 54;
        board[66] = 52;
        board[51] = 22;
        board[35] = 10;

        int minDiceThrows = calculateMinDiceThrows(board);
        System.out.println("Minimum dice thrown: " + minDiceThrows); // Output: 5

    }

    private static int calculateMinDiceThrows(int[] board) {
        int[] visited = new int[100]; // keep track of visited cells
        Queue<Entry> queue = new ArrayDeque<>();
        Entry entry = new Entry(0, 0);
        queue.add(entry);
        visited[0] = 1;
        while(!queue.isEmpty()) {
            entry = queue.poll();
            int currCell = entry.cell;
            if(currCell == board.length - 1) // if end is reached then break
                break;
            for(int i=1 + currCell; i <= 6 + currCell && i < board.length; i++) {
                if(visited[i] != 1) {
                    visited[i] = 1;
                    Entry next = new Entry();
                    next.diceThrowCount = entry.diceThrowCount + 1;
                    next.cell = (board[i] == -1) ? i : board[i]; // if ladder or snake is found then move to that cell
                    queue.add(next);
                }
            }
        }
        return entry.diceThrowCount;
    }

    static class Entry {
        int cell;
        int diceThrowCount;

        Entry() {}

        Entry(int c, int d) {
            this.cell = c;
            this.diceThrowCount = d;
        }

        @Override
        public String toString() {
            return "cell=" + cell + ", diceThrowCount=" + diceThrowCount;
        }
    }
}
