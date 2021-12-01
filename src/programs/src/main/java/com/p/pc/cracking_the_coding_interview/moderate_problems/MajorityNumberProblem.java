package com.p.pc.cracking_the_coding_interview.moderate_problems;

/**
 * A majority element is an element that makes up more than half of the items (rounded down) in an array. Given a positive integers
 * array, find the majority element. If there is no majority element, return -1.
 *
 * Do this in O(N) time and 0(1) space.
 *
 * Input: 1 2 5 9 5 9 5 5 5
 * Output: 5
 *
 * Approach:
 *  Since we need to solve this problem in O(1) space, hence, we can't use a HashMap. Idea here will be to first find
 *  a potential candidate by iterating over the array once and then try to validate if the item found is indeed a majority element.
 *
 *  For every element visited in the array, select it as majority if no majority item selected yet (i.e. count of majority item = 0),
 *  if next element matches the previous element then increase the count else decrease the count. When count becomes zero
 *  then reset the majority element for next element.
 *
 *  Idea here is that if count becomes zero for a given majority element and still array is not
 *  finished then that means that element will never be able to meet the requirement of being majority element (makes
 *  up more than half of the items in the array) and hence can be ignored completely.
 */
public class MajorityNumberProblem {
    public static void main(String[] args) {
        // There are 7 elements, 1 comes 4 times and hence 1 is majority
        System.out.println(findMajorityNumber(new int[]{3, 3, 1, 1, 1, 2, 1}));
        // There are 6 elements, 1 only comes 3 times and hence not more than the half of the items in the array.
        System.out.println(findMajorityNumber(new int[]{3, 3, 1, 1, 1, 2}));
    }

    private static int findMajorityNumber(int[] arr) {
        int candidate = findMajorityCandidate(arr);
        return validateCandidate(arr, candidate);
    }

    private static int validateCandidate(int[] arr, int candidate) {
        int count = 0;
        for(int n : arr) {
            if(candidate == n) count++;
        }
        return count > arr.length / 2 ? candidate : -1;
    }

    private static int findMajorityCandidate(int[] arr) {
        if(arr == null || arr.length < 1) return -1;
        if(arr.length == 1) return arr[0];
        int majority = arr[0];
        int count = 1;

        for(int i=1; i<arr.length; i++) {
            // if count resets to zero then select a new majority element. Checking the count before and resetting the
            // majority will ensure count never goes to negative.
            if(count == 0)
                majority = arr[i];
            // increase the count if current element matches the majority else decrease the count
            if(arr[i] == majority) {
                count++;
            } else count--;
        }
        return majority;
    }
}
