package com.p.pc.facebook;

/**
 * Find key in a sorted but rotated array. Degree of rotation is not known.
 *
 * Approach: Complexity: O(log n)
 *
 * Even with the rotation, the array can always be split into two sorted arrays.
 * Using this property, we can find the element.
 * From the mid-element do the following:
 * 1.Find in the left array if left array is sorted and key lies between start and mid
 * 2.Find in the right array if right array is sorted and key lied between mid and end
 * If above two cases are false, that means both sides are not sorted so that means either
 * 1. end is lower than the mid OR
 * 2. start is higher
 * So, if end is lower than key will be from mid to end
 * if start is higher than key will be from start to mid.
 */
public class FindKeyInSortedRotatedArray {
    public static void main(String[] args) {
        int[] arr = new int[] {21, 22, 9, 10, 12, 13, 14, 15};
        binarySearch(arr, 9);
        binarySearch(arr, 21);
    }

    private static void binarySearch(int[] arr, int key) {
        System.out.println("Index for key: " + key + ", is: " + binarySearchModified(arr, 0, arr.length -1, key));
    }

    static int binarySearchModified(int[] arr, int low, int high, int key) {
        if(low > high) return -1;

        int mid = (low + high) / 2;
        if(arr[mid] == key) return mid;

        if(arr[low] <= arr[mid]  && (key >= arr[low] && key <= arr[mid]))
            /* this means left array is sorted */ /* key lies within the range */
            return binarySearchModified(arr, low, mid - 1, key);
        else if(arr[mid] <= arr[high] && (key >= arr[mid] && key <= arr[high]))
            /* this means right array is sorted */ /* key lies within the range */
            return binarySearchModified(arr, mid + 1, high, key);
        /* If above two cases are not true, then that means either side of the array is not sorted and search in that side */
        else if(arr[low] >= arr[mid])
            return binarySearchModified(arr, low, mid - 1, key);
        else if(arr[high] <= arr[mid])
            return binarySearchModified(arr, mid + 1, high, key);
        return -1;
    }
}
