package com.p.pc.facebook;

/**
 * <p>Find the row with maximum number of 1's in a given matrix containing 0's and 1's and all rows are sorted.</p>
 */
public class RowWithMaxOneInMatrix {

    public static void main(String[] args) {
        int[][] mat = new int[][] {
                {0, 0, 0},
                {0, 0, 0},
                {1, 1, 1},
                {0, 0, 0}
        };
        System.out.println("Row index having maximum 1's is: " + findRowWithMaxOne(mat));
        System.out.println("Row index having maximum 1's is: " + findRowWithMaxOneOptimized(mat));
    }
    // Using binary search to find the first occurrence of 1 in every row. Total 1's will be (size - index of first 1)
    // Complexity: O(m log n)
    private static int findRowWithMaxOne(int[][] mat) {

        int maRowIndex = -1; int maxOnes = -1;
        int rowSize = mat[0].length;

        for(int i = 0; i < mat.length; i++) {
            int index = findIndex(mat[i], 0, rowSize-1);
            if(index != -1) {
                int tmpMaxOnes = rowSize - index;
                if(tmpMaxOnes > maxOnes) {
                    maxOnes = tmpMaxOnes;
                    maRowIndex = i;
                }
            }
        }
        return maRowIndex;
    }

    private static int findIndex(int[] arr, int start, int end) {
        if(start > end) return -1;
        int mid = (end + start) / 2;
        if (mid == 0) { // extra base condition to handle index 0 to avoid going to -1 index
            if(arr[mid] == 1) return mid; else return -1;
        }
        if(arr[mid] == 1 && arr[mid - 1] == 0)
            return mid;
        if(arr[mid] == 0)
            return findIndex(arr, mid + 1, end);
        else return findIndex(arr, start, mid - 1);
    }

    // Idea here is to find a row with first one, start from the end column. Then for the next row if value at previous index
    // is zero then ignore the row, else move to the left to find the min index and repeat.
    // Complexity: O( m + n)
    private static int findRowWithMaxOneOptimized(int[][] mat) {
        int rows = mat.length;
        int columns = mat[0].length;

        int i = 0, j = columns - 1, maxRowIndex = -1;

        while(i < rows && j >= 0) {
            if(mat[i][j] == 1) {
                maxRowIndex = i;
                j--;
            } else {
                i++;
            }
        }
        return maxRowIndex;
    }
}
