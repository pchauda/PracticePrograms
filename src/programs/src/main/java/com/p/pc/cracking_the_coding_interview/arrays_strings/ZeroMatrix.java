package com.p.pc.cracking_the_coding_interview.arrays_strings;

/**
 * Write an algorithm such that if an element in an MxN matrix is 0, its entire row and
 * column are set to 0. Only do this operation for the original 0s in the matrix.
 */
public class ZeroMatrix {
    public static void main(String[] args) {
        int[][] mat = new int[][] {
                {1, 2, 3, 4, 0},
                {2, 1, 0, 2, 1},
                {1, 2, 5, 1, 2},
                {3, 5, 1, 2, 3}
        };
        System.out.println("Original matrix:");
        printMatrix(mat);
        zerofyMatrixSimple(mat);
        System.out.println("Zeroed matrix:");
        printMatrix(mat); // Output should be below matrix
        /**
         *         int[][] output = new int[][] {
         *                 {0, 0, 0, 0, 0},
         *                 {2, 1, 0, 2, 0},
         *                 {1, 2, 0, 1, 0},
         *                 {0, 0, 0, 0, 0}
         *         };
         */
    }

    /**
     * Approach here is to keep track of the existing zeroes so that we are not making entire matrix as zero.
     * In the below method, we can keep track of the zeros using the first row and first column. However, also record
     * the fact if there are zeros in the first row and column already.
     *
     * @param mat
     */
    static void zerofyMatrix(int[][] mat) {
        int rows = mat.length;
        int columns = mat[0].length;

        boolean rowHasZero = false;
        boolean colHasZero = false;
        // check if first row has any zero
        for(int j=0; j < columns; j++) {
            if(mat[0][j] == 0) {
                rowHasZero = true;
                break;
            }
        }
        // check if first column has any zero
        for(int i=0; i < rows; i++) {
            if(mat[i][0] == 0) {
                colHasZero = true;
                break;
            }
        }
        // now iterate through the matrix from second row and column and if mat[i][j] == 0 then set the corresponding
        // first row and column as zero to keep track of zeroes in a given row and column that will be used later to
        // zerofy the matrix
        for(int i=1; i<rows; i++) {
            for(int j=1; j<columns; j++) {
                if(mat[i][j] == 0) {
                    mat[i][0] = 0; // record zero for row
                    mat[0][j] = 0; // record zero for column
                }
            }
        }
        // zerofy the rows using the first column
        for(int i=1; i<rows; i++) {
            if(mat[i][0] == 0) setZeroesInRows(mat, i);
        }
        // zerofy the columns using the first row
        for(int j=1; j<columns; j++) {
            if(mat[0][j] == 0) setZeroesInColumns(mat, j);
        }
        // zerofy first row and column if it had zeroes originally
        if(rowHasZero) setZeroesInRows(mat, 0);
        if(colHasZero) setZeroesInColumns(mat, 0);
    }

    /**
     * This is a simpler approach using a separate array for row and matrix to zerofy the matrix. However, matrix need
     * to be scanned twice for this, and it uses O(m+n) temporary space.
     * @param mat
     */
    private static void zerofyMatrixSimple(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;
        // Below arrays will be used to store the information of which rows and cols have zeros across all matrix
        boolean[] rowsWithZero = new boolean[rows];
        boolean[] colsWithZero = new boolean[cols];
        for(int i=0; i<rows; i++) {
            for(int j=0; j<cols; j++) {
                if(mat[i][j] == 0) {
                    rowsWithZero[i] = true;
                    colsWithZero[j] = true;
                }
            }
        }
        // zerofy the rows using rowsWithZero array
        for(int i=0; i<rows; i++) {
            if(rowsWithZero[i]) setZeroesInRows(mat, i);
        }

        // zerofy the cols using colsWithZero array
        for(int j=0; j<cols; j++) {
            if(colsWithZero[j]) setZeroesInColumns(mat, j);
        }
    }

    private static void setZeroesInColumns(int[][] mat, int column) {
        for(int i=0; i<mat.length; i++) {
            mat[i][column] = 0;
        }
    }

    private static void setZeroesInRows(int[][] mat, int row) {
        for(int j=0; j<mat[0].length; j++) {
            mat[row][j] = 0;
        }
    }


    private static void printMatrix(int[][] mat) {
        int x = mat.length;
        int y = mat[0].length;
        for(int i=0; i < x; i++) {
            for(int j=0; j < y; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }
}
