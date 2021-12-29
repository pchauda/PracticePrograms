package com.p.pc.facebook;

import java.util.*;

/**
 * Find all possible sub sets of a given integer array.
 * Total 2^n combination for array of length n.
 */
public class AllPossibleSubSetsForIntArray {

    public static void main(String[] args) {
        int[] arr = new int[]{1,4,8,10};
        System.out.println(getAllPossibleSubsets(arr));
        List<List<Integer>> result = new LinkedList<>();
        subsetsUsingBacktrack(arr, 0, new LinkedList<>(), result);
        System.out.println(result);

    }

    static List<Set<Integer>> getAllPossibleSubsets(int[] arr) {
        List<Set<Integer>> retVal = new ArrayList<>();
        int totalCases = (int) Math.pow(2, arr.length);
        for(int i=0; i < totalCases; i++) {
            Set<Integer> subset = new HashSet<>();
            for(int j=0; j < arr.length; j++) {
                // Left shift 1 by j. 1 << 0 == 1, 1 << 1 == 2 (10), 1 << 2 == 4 (100)
                // Effectively, create binary representation of array index and perform AND operation with i to select the elements in the subset
                if((i & (1 << j)) > 0) {
                    subset.add(arr[j]);
                }
            }
            retVal.add(subset);
        }
        return retVal;
    }

    //Recursively traverse the array, there are two situations for each element: 1. Select the element 2. Not select the element
    public static void subsetsUsingBacktrack(int[] nums, int current, LinkedList<Integer> track, List<List<Integer>> result) {
        //All elements have been traversed
        if (current == nums.length) {
            result.add(new LinkedList<>(track)); // clone and add to the result
            return;
        }
        //Select the current element and then recursively work on the next element
        track.add(nums[current]);
        subsetsUsingBacktrack(nums, current + 1, track, result);
        //Remove the selected element and then recursively work on the next element
        track.removeLast();
        subsetsUsingBacktrack(nums, current + 1, track, result);
    }
}
