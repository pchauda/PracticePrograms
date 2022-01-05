package com.p.pc.cracking_the_coding_interview.moderate_problems;

import java.util.*;

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
 * Approach:
 *  Alternate approach implementation where only keep track of white squares and always maintain topLeft and bottomRight
 *  corner of the grid. This approach is better than the previous approach using matrix as it keeps the grid size
 *  minimum and no need to shift the elements.
 */
public class AntMovementOnGrid2 {
    static final AntMovementOnGrid2 obj = new AntMovementOnGrid2();

    public static void main(String[] args) {
        int K = 100;
        Board board = new Board(new Ant[]{
                new Ant(1, Orientation.Right),
                new Ant(2, Orientation.Right),
                new Ant(3, Orientation.Right)});
        obj.printKMoves(K, board);
    }

    private void printKMoves(int k, Board board) {
        while(k-- > 0) {
            board.moveAnts();
        }
        board.printGrid();
    }

    static class Board {
        Ant[] ants;
        Set<Position> whites = new HashSet<>(); // Keep track of white positions
        // board dimensions represented as topLeft and bottomRight corner
        Position topLeft = new Position(0, 0);
        Position bottomRight = new Position(0, 0);

        Board(Ant[] ants) {
            this.ants = ants;
        }

        private void moveAnts() {
            for(Ant ant : ants) {
                moveAnt(ant);
            }
            // adjust board dimensions based on all ants positions
            adjustBoardDimensions();
        }

        private void moveAnt(Ant ant) {
            ant.turn(isWhite(ant.position));
            flip(ant.position);
            ant.move();
        }

        private void adjustBoardDimensions() {
            int antMinRow = 0, antMaxRow = 0, antMinCol = 0, antMaxCol = 0;
            for(Ant ant : ants) {
                antMinRow = Math.min(antMinRow, ant.position.row);
                antMaxRow = Math.max(antMaxRow, ant.position.row);
                antMinCol = Math.min(antMinCol, ant.position.col);
                antMaxCol = Math.max(antMaxCol, ant.position.col);
            }

            // adjust top left corner
            topLeft.row = Math.min(topLeft.row, antMinRow);
            topLeft.col = Math.min(topLeft.col, antMinCol);
            // adjust bottom right corner
            bottomRight.row = Math.max(bottomRight.row, antMaxRow);
            bottomRight.col = Math.max(bottomRight.col, antMaxCol);
        }

        boolean isWhite(Position p) {
            return whites.contains(p);
        }

        boolean isWhite(int r, int c) {
            return whites.contains(new Position(r, c));
        }

        private void flip(Position position) {
            if(whites.contains(position)) {
                whites.remove(position);
            } else {
                whites.add(position.clone());
            }
        }
        public void printGrid() {
            int rMin = topLeft.row;
            int rMax = bottomRight.row;
            int cMin = topLeft.col;
            int cMax = bottomRight.col;
            StringBuilder sb = new StringBuilder();
            for(int r=rMin; r<=rMax; r++) {
                for(int c=cMin; c<=cMax; c++) {
                    Ant ant = checkAnt(r, c);
                    if(ant != null) {
                        sb.append(ant.antString());
                    } else if(isWhite(r, c)) {
                        sb.append("x");
                    } else sb.append("_");
                }
                sb.append("\n");
            }
            System.out.println(sb);
        }
        Ant checkAnt(int r, int c) {
            for(Ant ant : ants) {
                if(ant.position.row == r && ant.position.col == c) return ant;
            }
            return null;
        }
    }

    static class Ant {
        Orientation orientation;
        Position position;
        int index;
        Ant(int index, Orientation orientation) {
            this.index = index;
            this.orientation = orientation;
            this.position = new Position(0, 0);
        }
        Ant() {
            this.index = 0;
            this.orientation = Orientation.Right;
            this.position = new Position(0, 0);
        }
        Ant(Position p) {
            this.index = 0;
            this.orientation = Orientation.Right;
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
        String antString() {return Integer.toString(index);};
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return row == position.row && col == position.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }

        public Position clone() {return new Position(row, col);};
    }
}
