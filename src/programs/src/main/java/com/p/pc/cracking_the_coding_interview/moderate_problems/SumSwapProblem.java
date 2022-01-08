package com.p.pc.cracking_the_coding_interview.moderate_problems;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * <p>Given two arrays of integers, find a pair of values (one value from each array) that you
 * can swap to give the two arrays the same sum. <br/>
 * EXAMPLE: <br/>
 * Input: {4, 1, 2, 1, 1, 2} and {3, 6, 3, 3} <br/>
 * Output: {1, 3} or {4, 6}
 * </p>
 * Approach: <br/>
 * This problem can be optimally approached using Two sum problem approach. Basically, we need to find one element from
 * each array (a, b) such that by swapping those elements the sum of both arrays become equal, i.e. <br/>
 * sumA - a + b = sumB + a - b <br/>
 * a - b = (sumA - sumB) / 2 => target <br/>
 * RHS can be calculated easily and then for a given element in first array, we just need to find the corresponding
 * element in second array such that a - b = target value. <br/>
 * Also note that the sum difference should be an even number otherwise the swapping will never result into an equal sum.
 * <br/>
 * If the given arrays are sorted then this problem can be solved easily by just iterating over both array together.
 * If diff between first elements of both arrays is equal to targetDiff then return, if diff < target then move to the
 * next element in first array else move to the element in next array.
 */
public class SumSwapProblem {
    final static SumSwapProblem obj = new SumSwapProblem();

    public static void main(String[] args) {
        int[] a = new int[]{4, 1, 2, 1, 1, 2};
        int[] b = new int[]{3, 6, 3, 3};

        int[] result = obj.findPairToSwap(a, b);
        if (result == null)
            System.out.println("No such pair found");
        else
            System.out.println("Pair: " + Arrays.toString(result)); // [4, 6]

        result = obj.findPairToSwapUsingSort(a, b);
        System.out.printf("Pair using alternate approach: " + Arrays.toString(result)); // [1, 3]
    }

    private int[] findPairToSwap(int[] array1, int[] array2) {
        Integer target = calcTarget(array1, array2);
        if (target == null) return null;
        Set<Integer> array2Set = new HashSet<>();
        IntStream.of(array2).forEach(t -> array2Set.add(t));
        for (int i = 0; i < array1.length; i++) {
            int a = array1[i];
            int b = a - target; // a - b = target
            if (array2Set.contains(b)) {
                return new int[]{a, b};
            }
        }
        return null;
    }

    private int[] findPairToSwapUsingSort(int[] arr1, int[] arr2) {
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        Integer target = calcTarget(arr1, arr2);
        if (target == null) return null;
        int a = 0, b = 0;
        while (a < arr1.length && b < arr2.length) {
            int diff = arr1[a] - arr2[b];
            if (diff == target) return new int[]{arr1[a], arr2[b]};
            if (diff < target) { // diff is smaller hence move to next element in arr1 to increase the diff
                a++;
            } else b++;
        }
        return null;
    }

    Integer calcTarget(int[] a, int[] b) {
        int sumA = IntStream.of(a).sum();
        int sumB = IntStream.of(b).sum();
        return (sumA - sumB) % 2 != 0 ? null : (sumA - sumB) / 2;
    }
}
