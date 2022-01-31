package com.p.pc.facebook;

import java.util.Arrays;
import java.util.List;

/**
 * Given a sorted array but having duplicates, find the low and high index for a given key.
 *
 * Approach:
 * Perform modified binary search twice, once for low index and then for high index.
 */
public class FindLowHighIndexForGivenKey {

    public static void main(String[] args) {
        List<Integer> array = Arrays.asList(-2, -2, 2, 3, 3, 5, 6);
        int key = 3;
        int low = findLowIndex(array, key);
        int high = findHighIndex(array, key);
        System.out.println("Low Index of " + key + ": " + low);
        System.out.println("High Index of " + key + ": " + high);

        key = -2;
        low = findLowIndex(array, key);
        high = findHighIndex(array, key);
        System.out.println("Low Index of " + key + ": " + low);
        System.out.println("High Index of " + key + ": " + high);
    }

    static int findLowIndex (List<Integer> arr, int key) {
        int low = 0;
        int high = arr.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr.get(mid) >= key) high = mid - 1;
            else low = mid + 1;
        }
        if (low < arr.size() && arr.get(low) == key) {
            return low;
        }
        return -1;
    }

    static int findHighIndex(List<Integer> arr, int key) {
        int low = 0;
        int high = arr.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr.get(mid) <= key) low = mid + 1;
            else high = mid - 1;
        }
        if (high >= 0 && high < arr.size() && arr.get(high) == key) {
            return high;
        }
        return -1;
    }
}
