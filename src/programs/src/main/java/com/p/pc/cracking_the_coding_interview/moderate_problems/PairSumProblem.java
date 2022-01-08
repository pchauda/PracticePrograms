package com.p.pc.cracking_the_coding_interview.moderate_problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>Design an algorithm to find all pairs of integers within an array which sum to a specified value. Once an element is
 * used it can not be used again. Duplicate elements should be considered separate elements.</p>
 *
 * Approach: <br/>
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
        /** Output:
         * [a=5, b=2]
         * [a=3, b=4]
         * [a=6, b=1]
         * [a=-2, b=9]
         */
    }

    private static List<Pair> findPairs(int[] arr, int target) {
        List<Pair> result = new ArrayList<>();
        HashMap<Integer, Integer> numCountMap = new HashMap<>();
        for(int x : arr) {
            int y = target - x;
            if(numCountMap.getOrDefault(y, 0) > 0) {
                result.add(new Pair(y, x));
                numCountMap.put(y, numCountMap.getOrDefault(y,0) - 1); // using y from the map hence reduce its count
            } else {
                numCountMap.put(x, numCountMap.getOrDefault(x,0) + 1);; // add x to the map and increase its count
            }
        }
        return result;
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
