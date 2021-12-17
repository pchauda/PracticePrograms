package com.p.pc.cracking_the_coding_interview.moderate_problems;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Design an algorithm to find the smallest K numbers in an array.
 *
 * Approach:
 *  Use Max heap (largest at the top) to store first K elements from the given array. For next elements of the array,
 *  if element is smaller than the root or top of the heap then add the element to the heap and remove top.
 *  By doing this, ultimately all larger elements will be removed from the heap and we will end up having a heap with K elements.
 */
public class SmallestKNumbers {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(findKSmallestNumbers(new int[] {10, 12, 15, 1, 10, 4, 13, 4}, 3)));
        System.out.println(Arrays.toString(findKSmallestNumbers(new int[] {1, 4, 10, 3, 9, 7, 2}, 4)));
    }

    private static int[] findKSmallestNumbers(int[] arr, int K) {
        if(K <= 0 || K > arr.length) throw new IllegalArgumentException("Invalid arguments");

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(K, (a, b) -> b - a); // Reverse order comparator
        // create max heap with K elements only
        for(int e : arr) {
            if(maxHeap.size() < K) {
                maxHeap.add(e);
            } else if(e < maxHeap.peek()) { // if element is less than top element, remove top and add element
                maxHeap.poll(); // these operations can be in any order
                maxHeap.add(e);
            }
        }

        int[] retVal = new int[K];
        for(int i = retVal.length - 1; i >= 0; i--) {
            retVal[i] = maxHeap.poll();
        }
        return retVal;
    }
}
