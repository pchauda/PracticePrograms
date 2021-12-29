package com.p.pc.hackerrank;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * In an alien language, surprisingly, they also use English lowercase letters, but possibly in a different order.
 * The order of the alphabet is some permutation of lowercase letters.
 *
 * Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if
 * the given words are sorted lexicographically in this alien language.
 *
 * Example 1:
 *
 * Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
 * Output: true
 * Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
 * Example 2:
 *
 * Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
 * Output: false
 * Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
 * Example 3:
 *
 * Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
 * Output: false
 * Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).
 *
 * Constraints:
 *
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 20
 * order.length == 26
 * All characters in words[i] and order are English lowercase letters.
 */
public class AlienDictionary {
    public static void main(String[] args) {
        String order = "worldabcefghijkmnpqstuvxyz";
        System.out.println(isAlienSorted(new String[]{"world", "word", "row"}, order));
        System.out.println(isAlienSorted(new String[]{"applepie", "apple"}, order));
        System.out.println(isAlienSorted(new String[]{"prince", "priya", "row"}, order));
    }

    /**
     * Compare each word with its next word character by character using the given alien order. Create a custom comparator
     *
     * @param words list of words
     * @param order alphabetical order for alien language
     * @return true if words are sorted in lexicographical order else return false
     */
    private static boolean isAlienSorted(String[] words, String order) {
        AlienLanguageComparator comparator = new AlienLanguageComparator(order);
        for(int i=0; i < words.length - 1; i++) {
            String current = words[i];
            String next = words[i+1];

            if(comparator.compare(current, next) > 0) return false;
        }
        return true;
    }

    static class AlienLanguageComparator implements Comparator<String> {
        private Map<Character, Integer> orderMap;

        AlienLanguageComparator(String order) {
            Map<Character, Integer> orderMap = new HashMap<>();
            for(int i=0; i<order.length(); i++) {
                orderMap.put(order.charAt(i), i);
            }
            this.orderMap = orderMap;
        }

        @Override
        public int compare(String s1, String s2) {
            char[] a1 = s1.toCharArray();
            char[] a2 = s2.toCharArray();

            int lmin = Math.min(a1.length, a2.length);
            int i = 0;
            while(i < lmin) {
                char c1 = a1[i];
                char c2 = a2[i];
                // if chars match then proceed to the next char else return the order diff
                if(c1 != c2) {
                    // return the order using the given orderMap for alien language
                    return orderMap.get(c1) - orderMap.get(c2);
                }
                i++;
            }
            // check for the length comparison as well
            return a1.length - a2.length;
        }
    }

}
