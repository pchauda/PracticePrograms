package com.p.pc.cracking_the_coding_interview.arrays_strings;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Given a string, find out if it is a permutation of palindrome.</p>
 *
 * Approach: <br/>
 *  For palindrome, all characters must appear even number of times except one character i.e. if count of characters
 *  appearing odd number of times is more than 1 then given string is not a permutation of a palindrome.
 */
public class PalindromePermutation {
    public static void main(String[] args) {
        String str = "rats evil not tno vile tars";
        System.out.println("Given string: \" " + str + " \"" + " is a permutation of palindrome? " + checkIfPalindromeOfPermutation(str));
    }
    // Time complexity = O(n)
    private static boolean checkIfPalindromeOfPermutation(String str) {
        if(str.length() <= 1) return true;
        int countOdd = 0;
        Map<Character, Integer> charFreq = new HashMap<>();
        for(char c: str.toCharArray()) {
            charFreq.put(c, charFreq.getOrDefault(c, 0) + 1);
            if(charFreq.get(c) % 2 == 0) {
                countOdd--;
            } else countOdd++;
        }
        return countOdd <= 1 ? true : false;
    }
}
