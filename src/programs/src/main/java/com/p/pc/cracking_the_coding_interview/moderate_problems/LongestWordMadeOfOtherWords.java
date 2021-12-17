package com.p.pc.cracking_the_coding_interview.moderate_problems;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given a list of words, write a program to find the longest word made of other words
 * in the list.
 *
 * Example: String[] words = new String[]{"i", "not", "rest", "int", "e", "te" , "in", "interesting", "ing"};
 * Result: interesting
 */
public class LongestWordMadeOfOtherWords {
    private static LongestWordMadeOfOtherWords obj = new LongestWordMadeOfOtherWords();

    public static void main(String[] args) {
        String[] words = new String[]{"i", "not", "rest", "int", "e", "te" , "in", "interesting", "nothing", "hing"};
        System.out.println(Arrays.toString(words));
        System.out.println("Longest word: " + obj.findLongestWordMadeOfOtherWords(words)); // nothing
    }

    private String findLongestWordMadeOfOtherWords(String[] words) {
        // Use Map instead of Set for memoization purpose
        Map<String, Boolean> wordMap = Arrays.stream(words).collect(Collectors.toMap(t -> t, t -> true));
        // sort the input array, complexity: O (n * log n)
        Arrays.sort(words, (a, b) -> b.length() - a.length());
        // For each word starting from the longest, check if it can be made using other words
        for(String word: words) {
            if(canBuildFromOtherWords(word, wordMap, true)) {
                return word;
            }
        }
        return null;
    }

    private boolean canBuildFromOtherWords(String word, Map<String, Boolean> wordMap, boolean original) {
        // If not the original word and if exists originally in the map or can be derived from other words then return true
        if(!original && wordMap.containsKey(word) && wordMap.get(word)) return true;
        // Split the string in left and right, and recursively check if right is made of other words or not
        for(int i=1; i<word.length(); i++) {
            String left = word.substring(0, i);
            String right = word.substring(i);
            if(wordMap.containsKey(left) && wordMap.get(left) && canBuildFromOtherWords(right, wordMap, false)) {
                return true;
            }
        }
        // Put the newly found words with their status in the map
        if(!original)
            wordMap.put(word, false);
        return false;
    }
}
