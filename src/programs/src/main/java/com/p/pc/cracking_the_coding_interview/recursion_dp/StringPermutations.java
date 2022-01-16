package com.p.pc.cracking_the_coding_interview.recursion_dp;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This problem constitutes two problems. <br/>
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
     * Time Complexity: O(n * n!) there are n! permutations and it requires O(n) time for string concatenation and substring.
     */
    private void findAllPermutationsForUniqueCharsHelper(String prefix, String remaining, List<String> result) {
        if (remaining.length() == 0) {
            result.add(prefix);
            return;
        }
        for (int i = 0; i < remaining.length(); i++) {
            char c = remaining.charAt(i);
            String suffix = remaining.substring(0, i) + remaining.substring(i + 1);
            findAllPermutationsForUniqueCharsHelper(prefix + c, suffix, result);
        }
    }

    public List<String> findAllPermutationsForNonUniqueChars(String str) {
        List<String> result = new ArrayList<>(factorial(str.length()));
        Map<Character, Long> charCountMap = str.chars().mapToObj(i -> (char) i)
                .collect(Collectors.groupingBy(i -> i, Collectors.counting()));
        findAllPermutationsForNonUniqueCharsHelper("", charCountMap, str.length(), result);
        return result;
    }

    /**
     * Tracking of remaining is required only for the base case and to make program simple, otherwise,
     * count of all chars in the map == zero will also result into the base case
     */
    private void findAllPermutationsForNonUniqueCharsHelper(String prefix, Map<Character, Long> charCountMap,
                                                            int remaining, List<String> result) {
        // Base case, alternatively charCountMap.values().stream().reduce(0, (a, b) -> a + b) == 0 will also work
        if (remaining == 0) {
            result.add(prefix);
            return;
        } else {
            // For each character, pick the character, reduce the count and then recursively find all permutations.
            // Later, restore the count and then work on another character.
            for (Character c : charCountMap.keySet()) {
                Long count = charCountMap.get(c);
                if (count != 0) {
                    charCountMap.put(c, count - 1); // Reduce the count
                    findAllPermutationsForNonUniqueCharsHelper(prefix + c, charCountMap, remaining - 1, result);
                    charCountMap.put(c, count); // Restore the count
                }
            }
        }
    }

    int factorial(int n) {
        int temp = 1;
        for (int i = 1; i <= n; i++) {
            temp = temp * i;
        }
        return temp;
    }
}
