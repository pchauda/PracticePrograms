package com.p.pc.cracking_the_coding_interview.recursion_dp;

/**
 * <p>A magic index in an array A[ 1 .•. n-1] is defined to be an index such that A[i] = i.
 * Given a sorted array of distinct/non-distinct integers, write a method to find the magic index, if one exists, in
 * array A.</p>
 * <p>
 * Approach: <br/>
 * Use binary search to reduce the complexity from O(n) to O(log n)
 */
public class FindMagicIndex {
    private static FindMagicIndex obj = new FindMagicIndex();

    public static void main(String[] args) {
        int[] arr = new int[]{-3, -1, 0, 2, 3, 4, 6, 8};
        System.out.println("Magic index for the given array is: " + obj.findMagicIndexForArrayWithDistinctValues(arr));

        int[] arrNonDistinct = new int[]{-1, 2, 3, 4, 5, 5, 8, 9};
        System.out.println("Magic index for the given array is: " + obj.findMagicIndexForArrayWithNonDistinctValues(arrNonDistinct));
    }

    int findMagicIndexForArrayWithDistinctValues(int[] arr) {
        return findMagicIndex(arr, 0, arr.length - 1);
    }

    private int findMagicIndex(int[] arr, int start, int end) {
        if (start <= end) {
            int mid = (start + end) / 2;
            if (arr[mid] == mid) return mid;
            if (arr[mid] < mid) {
                return findMagicIndex(arr, mid + 1, end);
            } else {
                return findMagicIndex(arr, start, mid - 1);
            }
        }
        return -1;
    }

    int findMagicIndexForArrayWithNonDistinctValues(int[] arr) {
        return findMagicIndexNonDistinct(arr, 0, arr.length - 1);
    }

    private int findMagicIndexNonDistinct(int[] arr, int start, int end) {
        if (start <= end) {
            int mid = (start + end) / 2;
            int midVal = arr[mid];

            if (mid == midVal) return mid;
            int leftSearch = findMagicIndexNonDistinct(arr, start, Math.min(mid - 1, midVal));
            if (leftSearch != -1) return leftSearch;

            return findMagicIndexNonDistinct(arr, Math.max(mid + 1, midVal), end);

        }
        return -1;
    }
}
