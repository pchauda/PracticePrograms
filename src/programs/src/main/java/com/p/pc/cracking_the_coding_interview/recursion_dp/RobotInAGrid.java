package com.p.pc.cracking_the_coding_interview.recursion_dp;

import java.util.ArrayList;
import java.util.List;

/**
 * Imagine a robot sitting on the upper-left-corner of grid with r rows and c columns.
 * The robot can only move in two directions, right and down, but certain cells are "off limits" such that
 * the robot cannot step on them. Design an algorithm to find a path for the robot from the top left to
 * the bottom right.
 */
public class RobotInAGrid {

    public static void main(String[] args) {
        // 0 means free, 1 means blocked, 4 x 6 grid
        int[][] grid = {
                {0, 0, 1, 0, 0, 0},
                {1, 0, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 1, 0}
        };
        List<Point> path = findPathOptimized(grid);
        System.out.println(path);
    }

    /**
     * Approach here is to start from the end and then recursively check left and up to see if a valid path can be
     * found. This algorithm can be optimized using Dynamic programming approach by storing the results of previous computations.
     */
    private static List<Point> findPathOptimized(int[][] grid) {
        List<Point> path = new ArrayList<>();
        int rows = grid.length;
        int cols = grid[0].length;
        List<Point> failedPoints = new ArrayList<>();
        if(findPath(grid, rows - 1, cols - 1, path, failedPoints)) {
            return path;
        }
        return null;
    }

    private static boolean findPath(int[][] grid, int rowIdx, int colIdx, List<Point> path, List<Point> failedPoints) {
        // base cases
        if(rowIdx < 0 || colIdx < 0 || grid[rowIdx][colIdx] == 1) return false;
        boolean isOrigin = (rowIdx == 0 && colIdx == 0);
        Point p = new Point(rowIdx, colIdx);

        // If point is already visited then return
        if(failedPoints.contains(p)) return false;

        if(isOrigin || findPath(grid, rowIdx - 1, colIdx, path, failedPoints)
                || findPath(grid, rowIdx, colIdx - 1, path, failedPoints)) {
            path.add(p);
            return true;
        }
        failedPoints.add(p);
        return false;
    }

    static class Point {
        int r, c;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "{r=" + r + ", c=" + c + '}';
        }
    }
}
