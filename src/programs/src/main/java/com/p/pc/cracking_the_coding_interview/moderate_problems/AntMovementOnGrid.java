package com.p.pc.cracking_the_coding_interview.moderate_problems;

import java.util.Arrays;

/**
 * <p>An ant is sitting on an infinite grid of white and black squares. It initially faces right.
 * At each step, it does the following:
 * (1) At a white square, flip the color of the square, turn 90 degrees right (clockwise), and move forward
 * one unit.
 * (2) At a black square, flip the color of the square, turn 90 degrees left (counter-clockwise), and move
 * forward one unit.
 * Write a program to simulate the first K moves that the ant makes and print the final board as a grid.
 * Note that you are not provided with the data structure to represent the grid. This is something you
 * must design yourself. The only input to your method is K. You should print the final grid and return
 * nothing. The method signature might be something like void printKMoves (int K).</p>
 *
 * Approach: <br/>
 *  Problem looks simple enough however the complexity lies with InfiniteGrid. Since the grid is infinite and needs to be printed,
 *  there is no point of printing the grid area that beyond the reach of the ant in K moves. Idea here is to start with
 *  a grid of single square and then grow the grid based on ant's latest position. If storing the grid as matrix, then
 *  shift the grid when row or column of ant's position becomes negative.
 */
public class AntMovementOnGrid {
    Grid grid = new Grid();
    public static void main(String[] args) {
        AntMovementOnGrid obj = new AntMovementOnGrid();
        int K = 100;
        obj.printKMoves(K);
    }

    private void printKMoves(int k) {
        while(k > 0) {
            grid.moveAnt();
            k--;
        }
        grid.printGrid();
    }

    static class Grid {
        boolean[][] grid; // false represents black, true represents white
        Ant ant = new Ant();
        Grid() {
            grid = new boolean[1][1]; // Initialize with size 1
        }
        void flip(Position p) {
            grid[p.row][p.col] = grid[p.row][p.col] ? false : true;
        }
        public void moveAnt() {
            boolean isWhite = grid[ant.position.row][ant.position.col];
            flip(ant.position);
            ant.turn(isWhite);
            ant.move();
            ensureFit(ant.position);
        }

        // Grow the grid based on the ant new position
        private void ensureFit(Position position) {
            int shiftRow = 0, shiftCol = 0;
            int rows = grid.length;
            int cols = grid[0].length;
            // If ant's position (either row or col) becomes negative then shift the grid
            shiftRow = position.row < 0 ? rows : 0;
            shiftCol = position.col < 0 ? cols : 0;
            // If ant's position is outside the current grid then grow the grid (twice)
            rows *= (position.row < 0 || position.row >= rows) ? 2 : 1;
            cols *= (position.col < 0 || position.col >= cols) ? 2 : 1;

            // grow the grid if required and copy over elements, adjust the ant position
            if(rows != grid.length || cols != grid[0].length) {
                boolean[][] newGrid = new boolean[rows][cols];
                copyWithShift(shiftRow, shiftCol, newGrid);
                ant.adjustPosition(shiftRow, shiftCol);
                grid = newGrid;
            }
        }

        public void copyWithShift(int shiftRow, int shiftCol, boolean[][] newGrid) {
            for(int r=0; r < grid.length; r++) {
                for(int c=0; c < grid[0].length; c++) {
                    newGrid[r + shiftRow][c + shiftCol] = grid[r][c];
                }
            }
        }

        void printGrid() {
            StringBuilder sb = new StringBuilder();
            for(int r=0; r < grid.length; r++) {
                for(int c=0; c < grid[0].length; c++) {
                    if(ant.position.row == r && ant.position.col == c) {
                        sb.append(ant.orientation.toString());
                    } else if(grid[r][c]) {
                        sb.append("x");
                    } else sb.append("_");
                }
                sb.append("\n");
            }
            sb.append("Ant's position: " + Arrays.toString(new int[]{ant.position.row, ant.position.col}) + " and orientation: " + ant.orientation + "\n");
            System.out.println(sb);
        }
    }

    static class Ant {
        Orientation orientation = Orientation.Right;
        Position position;
        Ant() {
            this.position = new Position(0, 0);
        }
        Ant(Position p) {
            this.position = p;
        }
        public void turn(boolean clockWise) {
            this.orientation = orientation.turn(clockWise);
        }
        public void move() {
            if(orientation == Orientation.Left) {
                position.col--;
            } else if(orientation == Orientation.Right) {
                position.col++;
            } else if(orientation == Orientation.Up) {
                position.row--;
            } else position.row++;
        }
        public void adjustPosition(int shiftRow, int shiftCol) {
            position.row += shiftRow;
            position.col += shiftCol;
        }
    }

    enum Orientation {
        Left, Right, Up, Down;
        public Orientation turn(boolean clockWise) {
            if(this == Left) {
                return clockWise ? Up : Down;
            } else if(this == Right) {
                return clockWise ? Down : Up;
            } else if(this == Up) {
                return clockWise ? Right : Left;
            } else return clockWise ? Left : Right;
        }

        @Override
        public String toString() {
            if(this == Left) return "\u2190"; // Left arrow sign
            else if(this == Up) return "\u2191"; // Up arrow sign
            else if(this == Right) return "\u2192"; // Right arrow sign
            else return "\u2193"; // Down arrow sign
        }
    }
    static class Position {
        int row, col;
        Position(int r, int c) {
            this.row = r;
            this.col = c;
        }
    }
}
