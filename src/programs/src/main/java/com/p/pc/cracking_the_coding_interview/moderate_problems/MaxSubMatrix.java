package com.p.pc.cracking_the_coding_interview.moderate_problems;

import java.util.StringJoiner;

/**
 * Given an NxN matrix of positive and negative numbers, write code to find the sub-matrix with the largest possible sum.
 */
public class MaxSubMatrix {
    private static MaxSubMatrix obj = new MaxSubMatrix();

    public static void main(String[] args) {
        int[][] matrix = new int[][] {
                { 10, -2, -4, -1,  4},
                {  0, -3,  6,  7, -2},
                { -6,  9,  8,  4, -4},
                {  2, -1, -4, -5, -6}

        };
        System.out.println("Original Matrix:");
        obj.printMatrix(matrix);
        SubMatrix sb = obj.findSubMatrixWithMaxSum(matrix);
        System.out.println("Sub Matrix:");
        sb.printSubMatrix(matrix);
        System.out.println("Max sum = " + sb.calculateSubMatrixSum(matrix));
    }

    private SubMatrix findSubMatrixWithMaxSum(int[][] matrix) {
        int rowCount = matrix.length, colCount = matrix[0].length;
        SubMatrix best = null;
        // Loop for Row Start
        for(int rowStart = 0; rowStart < rowCount; rowStart++) {
            // array to hold column wise sum of rows from rowStart to rowEnd
            int[] columnWiseSum = new int[colCount];
            // Loop for Row End
            for(int rowEnd = rowStart; rowEnd < rowCount; rowEnd++) {
                // Iterate over all columns, compute column-wise sum and find the best range using Kadane's algorithm
                for(int col = 0; col < colCount; col++) {
                    columnWiseSum[col] += matrix[rowEnd][col]; // collapse the column wise sum using rowEnd
                }
                Range bestRange = findBestSubArray(columnWiseSum); // find the best range so far using Kadane's algo
                if(best == null || best.sum < bestRange.sum) {
                    best = new SubMatrix(rowStart, rowEnd, bestRange.start, bestRange.end, bestRange.sum);
                }
            }
        }
        return best;
    }

    private Range findBestSubArray(int[] columnWiseSum) {
        Range best = null;
        int start = 0; int sum = 0;
        for(int i=0; i<columnWiseSum.length; i++) {
            sum += columnWiseSum[i];
            if(best == null || sum > best.sum) {
                best = new Range(start, i, sum);
            }

            if(sum < 0) {
                start = i + 1;
                sum = 0;
            }
        }
        return best;
    }

    static class Range {
        int start, end, sum;
        public Range(int start, int end, int sum) {
            this.start = start;
            this.end = end;
            this.sum = sum;
        }
    }

    static class SubMatrix {
        int rowStart, rowEnd, colStart, colEnd, sum;

        public SubMatrix(int rs, int re, int cs, int ce, int sum) {
            this.rowStart = rs;
            this.rowEnd = re;
            this.colStart = cs;
            this.colEnd = ce;
            this.sum = sum;
        }

        public int calculateSubMatrixSum(int[][] matrix) {
            int sum = 0;
            for(int i = rowStart; i <= rowEnd; i++) {
                for(int j = colStart; j <= colEnd; j++) {
                    sum += matrix[i][j];
                }
            }
            return sum;
        }

        public void printSubMatrix(int[][] matrix) {
            for(int i = rowStart; i <= rowEnd; i++) {
                StringJoiner join = new StringJoiner(",", "[", "]");
                for(int j = colStart; j <= colEnd; j++) {
                    join.add(Integer.toString(matrix[i][j]));
                }
                System.out.println(join);
            }
        }
    }

    public void printMatrix(int[][] matrix) {
        for(int i = 0; i < matrix.length; i++) {
            StringJoiner join = new StringJoiner(",", "[", "]");
            for(int j = 0; j < matrix[0].length; j++) {
                join.add(Integer.toString(matrix[i][j]));
            }
            System.out.println(join);
        }
    }

}
