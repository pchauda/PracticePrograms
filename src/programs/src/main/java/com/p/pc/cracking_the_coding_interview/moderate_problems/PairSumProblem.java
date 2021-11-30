package com.p.pc.cracking_the_coding_interview.moderate_problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Design an algorithm to find all pairs of integers within an array which sum to a specified value. Once an element is
 * used it can not be used again. Duplicate elements should be considered separate elements.
 *
 * Approach:
 *  Use a hashmap to keep the unpaired values, as soon as pair is found then remove/reduce it from the map.
 *
 *  Complexity = O(n)
 */
public class PairSumProblem {

    public static void main(String[] args) {
        int[] arr = new int[]{3, -1, 5, 2, -2, 6, 4, 10, -6, -4, -2, -2, 1, 0, 9, 12};
        int target = 7;
        List<Pair> pairs = findPairs(arr, target);
        pairs.stream().map(t -> t.toString()).forEach(System.out::println);
    }

    private static List<Pair> findPairs(int[] arr, int target) {
        List<Pair> result = new ArrayList<>();
        HashMap<Integer, Integer> unpairCount = new HashMap<>();
        for(int x : arr) {
            int y = target - x;
            if(unpairCount.getOrDefault(y, 0) > 0) {
                result.add(new Pair(x, y));
                adjustMap(unpairCount, y, -1); // using y from the map hence reduce its count
            } else {
                adjustMap(unpairCount, x, 1); // add x to the map and increase its count
            }
        }
        return result;
    }

    private static void adjustMap(HashMap<Integer, Integer> unpairCount, int x, int delta) {
        unpairCount.put(x, unpairCount.getOrDefault(x,0) + delta);
    }

    static class Pair {
        int a, b;
        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public String toString() {
            return "[a=" + a + ", b=" + b + "]";
        }
    }
}
