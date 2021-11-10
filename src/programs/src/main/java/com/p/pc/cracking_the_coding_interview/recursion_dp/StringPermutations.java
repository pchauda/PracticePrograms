package com.p.pc.cracking_the_coding_interview.recursion_dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This problem constitutes two problems.
 * 1. Find all unique permutations of a string having no duplicate characters
 * 2. Find all unique permutations of a string having duplicate characters
 */
public class StringPermutations {
    private static StringPermutations obj = new StringPermutations();
    public static void main(String[] args) {
        String str = "prin";
        List<String> allPermutations = obj.findAllPermutationsForUniqueChars(str);
        System.out.println("Total Permutations : " + allPermutations.size());
        System.out.println(allPermutations);
        String nonUnique = "aab";
        allPermutations = obj.findAllPermutationsForNonUniqueChars(nonUnique);
        System.out.println("Total Permutations : " + allPermutations.size());
        System.out.println(allPermutations);
    }

    public List<String> findAllPermutationsForUniqueChars(String str) {
        List<String> result = new ArrayList<>(factorial(str.length()));
        findAllPermutationsForUniqueCharsHelper("", str, result);
        return result;
    }
    /**
     * Total n! strings will be created. If String is of length n then there will be at least n levels in the recursion tree to reach the base case.
     * For each scenario, string concatenation will take O(n) time, hence total time complexity will be = O( n * n * n!) = O(n^2 * n!)
     */
    private void findAllPermutationsForUniqueCharsHelper(String prefix, String remaining, List<String> result) {
        if(remaining.length() == 0) {
            result.add(prefix);
            return;
        }
        for(int i=0; i < remaining.length(); i++) {
            char c = remaining.charAt(i);
            String suffix = remaining.substring(0, i) + remaining.substring(i+1);
            findAllPermutationsForUniqueCharsHelper(prefix + c, suffix, result);
        }
    }

    public List<String> findAllPermutationsForNonUniqueChars(String str) {
        List<String> result = new ArrayList<>(factorial(str.length()));
        Map<Character, Integer> charCountMap = new HashMap<>();
        for(Character c: str.toCharArray()) {
            charCountMap.compute(c, (k, v) -> v == null ? 1 : v + 1);
        }
        findAllPermutationsForNonUniqueCharsHelper("", charCountMap,  str.length(), result);
        return result;
    }

    /**
     * Tracking of remaining is required only for the base case and to make program simple, otherwise,
     * count of all chars in the map == zero will also result into the base case
     */
    private void findAllPermutationsForNonUniqueCharsHelper(String prefix, Map<Character, Integer> charCountMap, int remaining, List<String> result) {
        if(remaining == 0) { // Base case, alternatively charCountMap.values().stream().reduce(0, (a, b) -> a + b) == 0 will also work
            result.add(prefix);
            return;
        } else {
            // For each character, pick the character, reduce the count and then recursively find all permutations.
            // Later, restore the count and then work on another character.
            for(Character c : charCountMap.keySet()) {
                Integer count = charCountMap.get(c);
                if(count != 0) {
                    charCountMap.put(c, count - 1); // Reduce the count
                    findAllPermutationsForNonUniqueCharsHelper(prefix + c, charCountMap, remaining - 1, result);
                    charCountMap.put(c, count); // Restore the count
                }
            }
        }
    }

    int factorial(int n) {
        int temp = 1;
        for(int i=1; i <= n; i++) {
            temp = temp * i;
        }
        return temp;
    }
}
